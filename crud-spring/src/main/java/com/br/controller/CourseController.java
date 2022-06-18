package com.br.controller;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    // Com a anotacao @AllArgsConstructor nao e necessario enjetar as dependencias com o @Autowired
    // E nem criar o construtor visto que, essa anotacao do lombok ja realiza esse procedimento
    // public CourseController(CourseRepository courseRepository) {
    //    this.courseRepository = courseRepository;
    // }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }
}
