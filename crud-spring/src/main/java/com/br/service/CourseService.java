package com.br.service;

import com.br.exception.RecordNotFoundException;
import com.br.model.Course;
import com.br.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    // Constructor CourseRepository with Service
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Find all Courses
    public List<Course> list() {
        return courseRepository.findAll();
    }

    // Find by id Course
    // Caso nao encontre o curso devolve a excessao RecordNotFoundException
    public Course findById(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        return courseRepository.findById(idCourse)
                .orElseThrow(() -> new RecordNotFoundException(idCourse));
    }

    // Create Course
    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    // Update Course
    // Faz o update do curso senao devolve a excessao RecordNotFoundException
    public Course update(@NotNull @Positive Long idCourse,
                                   @Valid Course course) {
        return courseRepository.findById(idCourse)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    return courseRepository.save(courseFound);
                })
                .orElseThrow(() -> new RecordNotFoundException(idCourse));
    }

    // Delete Course
    // Encontrando o curso executa do delete senao, devolve uma excessao RecordNotFoundException
    public void delete(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        courseRepository.delete(
                courseRepository.findById(idCourse).orElseThrow(() -> new RecordNotFoundException(idCourse)));
    }
}
