package com.br;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			Course course = new Course();
			course.setName("Angular com Spring");
			course.setCategory("Front-end");

			courseRepository.save(course);
		};
	}

}
