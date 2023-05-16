package com.br.service;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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
    public Optional<Course> findById(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        return courseRepository.findById(idCourse);
    }

    // Create Course
    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    // Update Course
    public Optional<Course> update(@NotNull @Positive Long idCourse,
                                   @Valid Course course) {
        return courseRepository.findById(idCourse)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    return courseRepository.save(courseFound);
                });
    }

    // Delete Course
    public boolean delete(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        return  courseRepository.findById(idCourse)
                .map(courseFound -> {
                    courseRepository.delete(courseFound);
                    return  true;
                })
                .orElse(false);
    }
}
