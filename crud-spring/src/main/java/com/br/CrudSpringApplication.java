package com.br;

import com.br.enums.Category;
import com.br.model.Course;
import com.br.model.Lesson;
import com.br.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	// Exemplo para criacao de um Bean para simular um banco de dados
	// Apenas um teste pois a aplicacao ainda nao possui uma Base de Dados
	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

//			Course course1 = new Course();
//			course1.setName("Angular com Spring");
//			course1.setCategory(Category.FRONT_END);
//			course1.getLessons().add(
//					createLeassonMock("Introdução", "watch?v=111", course1)
//			);
//			course1.getLessons().add(
//					createLeassonMock("Angular", "watch?v=222", course1)
//			);
//
//			Course course2 = new Course();
//			course2.setName("Java com Spring Boot");
//			course2.setCategory(Category.BACK_END);
//			course2.getLessons().add(
//					createLeassonMock("Introdução", "watch?v=222", course2)
//			);
//
//			Course course3 = new Course();
//			course3.setName("Goolang" );
//			course3.setCategory(Category.FRONT_END);
//			course3.getLessons().add(
//					createLeassonMock("Introdução", "watch?v=333", course3)
//			);
//
//			List<Course> courses = Arrays.asList(course1, course2, course3);
//
//			courseRepository.saveAll(courses);

			for (int i = 0; i < 20; i++) {
				Course course = new Course();
				course.setName("Angular com Spring " + i);
				course.setCategory(Category.FRONT_END);
				course.getLessons().add(
						createLeassonMock("Introdução", "watch?v=111", course)
				);
				course.getLessons().add(
						createLeassonMock("Angular", "watch?v=222", course)
				);

				courseRepository.save(course);
			}
		};
	}

	private Lesson createLeassonMock(String name,
									 String youTubeUrl,
									 Course course) {
		Lesson lesson = new Lesson();
		lesson.setName(name);
		lesson.setYouTubeUrl(youTubeUrl);
		lesson.setCourse(course);
		return lesson;
	}

}
