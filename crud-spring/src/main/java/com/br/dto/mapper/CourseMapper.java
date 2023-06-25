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
                course.getCategory().getValue(),
                course.getLessons()
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
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    // Método de conversao para a Categoria
    public Category convertCategoryValue(String value) {
        if(value == null) {
            return  null;
        }
        // Expressao Switch (utilizada à partir do Java 17)
        // Nessa caso validando os cvalores de categoria para retornar no método
         return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}