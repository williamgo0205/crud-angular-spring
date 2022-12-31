package com.br.controller;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Busca por curso especifico
    // Repassando via pachVariable no corpo da requisicao atraves do "/{idCourse}"
    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> findById(@PathVariable("idCourse") Long idCourse) {
        // Retorno da Api. Caso encontre o curso retorna o mesmo com ok (httpStatus 200)
        // Caso não encontre retorna notFound (httpStatus 404)
        return  courseRepository.findById(idCourse)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
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

    /**
     * Exemplo Metodo PUT utilizando a anotacao ResponseStatus para retorno do HTTPStatus.CREATED = 201
     * @param course
     * @return
     */
    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping("/{idCourse}")
    public ResponseEntity<Course> update(@PathVariable("idCourse") Long idCourse,
                         @RequestBody Course course) {
        // Busca o curso e caso seja valido atualiza os dados do curso com as informacoes
        // Caso nao encontre o curso retorna o ResponseEntity.notFound()
        return courseRepository.findById(idCourse)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    Course courseUpdate = courseRepository.save(courseFound);
                    return  ResponseEntity.ok().body(courseUpdate);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
