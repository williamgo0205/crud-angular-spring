package com.br;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	// Exemplo para criacao de um Bean para simular um banco de dados
	// Apenas um teste pois a aplicacao ainda nao possui uma Base de Dados
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course course1 = new Course();
			course1.setName("Angular com Spring");
			course1.setCategory("front-end");

			Course course2 = new Course();
			course2.setName("Java");
			course2.setCategory("back-end");

			List<Course> courses = Arrays.asList(course1, course2);

			courseRepository.saveAll(courses);
		};
	}

}
