package com.br.dto.mapper;

import com.br.dto.CourseDTO;
import com.br.dto.LessonDTO;
import com.br.enums.Category;
import com.br.model.Course;
import com.br.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    // Create DTO de CourseDTO recebendo os valores da entidade Course
    public CourseDTO toDTO(Course course) {
        // Caso Course seja nulo retorna o valor nulo evitando assim um nullPointerException
        if (course == null) {
            return null;
        }
        // Criacao da lista de lessonDTO a partir da entidade Lesson
        List<LessonDTO> lessons = course.getLessons()
                .stream()
                .map(lessonn -> new LessonDTO(
                        lessonn.getId(),
                        lessonn.getName(),
                        lessonn.getYouTubeUrl())
                )
                .collect(Collectors.toList());

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessons
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

        //Montando o DTO dos curson através do courseDTO
        List<Lesson> lessons =  courseDTO.lessons()
            .stream()
            .map(lessonDTO -> {
                var lesson = new Lesson();
                lesson.setId(lessonDTO.id());
                lesson.setName(lessonDTO.name());
                lesson.setYouTubeUrl(lessonDTO.youtubeUrl());
                lesson.setCourse(course);
                return lesson;
            })
            .collect(Collectors.toList());

        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        course.setLessons(lessons);

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
