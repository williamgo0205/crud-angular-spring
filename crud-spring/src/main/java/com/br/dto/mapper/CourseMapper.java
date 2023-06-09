package com.br.dto.mapper;

import com.br.dto.CourseDTO;
import com.br.enums.Category;
import com.br.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    // Create DTO de CourseDTO recebendo os valores da entidade Course
    public CourseDTO toDTO(Course course) {
        // Caso Course seja nulo retorna o valor nulo evitando assim um nullPointerException
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                "Front-end"
        );
    }

    // Create Entity de Course recebendo os valores do DTO (CourseDTO)
    public Course toEntity(CourseDTO courseDTO) {
        // Caso CourseDTO seja nulo retorna o valor nulo evitando assim um nullPointerException
        if (courseDTO == null) {
            return null;
        }
        // Cria a instancia de Course validando que ele seja diferente de Nulo
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(Category.FRONT_END);
        course.setStatus("Ativo");
        return course;
    }
}
