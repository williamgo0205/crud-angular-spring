package com.br.controller;

import com.br.model.Course;
import com.br.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    /**
     * Com a anotacao @AllArgsConstructor nao e necessario enjetar as dependencias com o @Autowired
     * E nem criar o construtor visto que, essa anotacao do lombok ja realiza esse procedimento
     */
    // public CourseController(CourseRepository courseRepository) {
    //    this.courseRepository = courseRepository;
    // }

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseRepository.findAll();
    }

    /**
     * Busca por curso especifico
     * Repassando via pachVariable no corpo da requisicao atraves do "/{idCourse}"
     */
    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> findById(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        // Retorno da Api. Caso encontre o curso retorna o mesmo com ok (httpStatus 200)
        // Caso não encontre retorna notFound (httpStatus 404)
        return  courseRepository.findById(idCourse)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exemplo Metodo POST utilizando a anotacao ResponseStatus para retorno do HttpStatus.CREATED = 201
     * retornando o HttpStatus dentro do return
     * @param course
     * @return
     */
    // @RequestMapping(method = RequestMethod.POST)
    // public ResponseEntity<Course> create(@RequestBody Course course) {
    //      //Retorna o ResponsyEntity com status code CREATED = 201
    //      return ResponseEntity
    //             .status(HttpStatus.CREATED)
    //             .body(courseRepository.save(course));
    // }

    /**
     * Exemplo Metodo POST utilizando a anotacao ResponseStatus para retorno do HttpStatus.CREATED = 201
     * retornando o HttpStatus como anotacao ResponseStatus
     * @param course
     * @return
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }

    /**
     * Exemplo Metodo PUT utilizando a anotacao ResponseStatus para retorno do HttpStatus.Ok = 200
     * @param course
     * @param idCourse
     * @return
     */
    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping("/{idCourse}")
    public ResponseEntity<Course> update(@PathVariable("idCourse") @NotNull @Positive Long idCourse,
                         @RequestBody @Valid Course course) {
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

    /**
     * Exemplo Metodo DELETE utilizando a anotacao ResponseStatus para retorno do HttpStatus.NO_CONTENT = 204 ao qual nao retorna conteúdo
     * @param idCourse
     * @return
     */
    @DeleteMapping("/{idCourse}")
    public ResponseEntity<Void> delete(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        return  courseRepository.findById(idCourse)
                .map(courseFound -> {
                    courseRepository.delete(courseFound);
                    return  ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
