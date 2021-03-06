package com.br.controller;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }


    /**
     * Exemplo Metodo POST utilizando a anotacao o retorno ReponseEntity para do HTTPStatus.CREATED = 201
     * @param course
     * @return
     */
    // @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    // public ResponseEntity<Course> create(@RequestBody Course course) {
    //      //Retorna o ResponsyEntity com status code CREATED = 201
    //      return ResponseEntity
    //             .status(HttpStatus.CREATED)
    //             .body(courseRepository.save(course));
    // }

    /**
     * Exemplo Metodo POST utilizando a anotacao ResponseStatus para retorno do HTTPStatus.CREATED = 201
     * @param course
     * @return
     */
    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
    }
}
