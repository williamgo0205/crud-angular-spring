package com.br.service;

import com.br.dto.CourseDTO;
import com.br.dto.CoursePageDTO;
import com.br.dto.mapper.CourseMapper;
import com.br.exception.RecordNotFoundException;
import com.br.model.Course;
import com.br.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    // Constructor CourseRepository with Service
    public CourseService(CourseRepository courseRepository,
                         CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // Find all Courses
//    public List<CourseDTO> list() {
//        return courseRepository.findAll()
//                .stream()
//                .map(courseMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    // Find all Courses with pagination
    public CoursePageDTO list(@PositiveOrZero int pageNumber,
                              @Positive @Max(100) int pageSize) {
        // Obtendoa quantidade de elementose páginas que existem para o cusro
        Page<Course> pageCourse =  courseRepository.findAll(PageRequest.of(pageNumber, pageSize));

        // Mapeando esses valorespara o CourseDTO
        List<CourseDTO> courses = pageCourse
                .get()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());

        // retirnando o objeto de paginação
        return new CoursePageDTO(courses, pageCourse.getTotalElements(), pageCourse.getTotalPages());
    }

    // Find by id Course
    // Caso nao encontre o curso devolve a excessao RecordNotFoundException
    public CourseDTO findById(@NotNull @Positive Long idCourse) {
        return courseRepository.findById(idCourse)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(idCourse));
    }

    // Create Course
    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    // Update Course
    // Faz o update do curso senao devolve a excessao RecordNotFoundException
    public CourseDTO update(@NotNull @Positive Long idCourse,
                            @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(idCourse)
                .map(courseFound -> {
                    Course course = courseMapper.toEntity(courseDTO);

                    courseFound.setName(courseDTO.name());
                    courseFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));

                    // Remove todos os cursos para adicionar manualmente uma aum a fim de obter as referencias corrertas
                    courseFound.getLessons().clear();
                    course.getLessons().forEach(courseFound.getLessons()::add);

                    return courseRepository.save(courseFound);
                })
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(idCourse));
    }

    // Delete Course
    // Encontrando o curso executa do delete senao, devolve uma excessao RecordNotFoundException
    public void delete(@NotNull @Positive Long idCourse) {
        courseRepository.delete(
                courseRepository.findById(idCourse).orElseThrow(() -> new RecordNotFoundException(idCourse)));
    }
}
