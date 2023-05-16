package com.br.controller;

import com.br.model.Course;

import com.br.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<Course> list() {
        return courseService.list();
    }

    /**
     * Busca por curso especifico
     * Repassando via pachVariable no corpo da requisicao atraves do "/{idCourse}"
     */
    @GetMapping("/{idCourse}")
    public ResponseEntity<Course> findById(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        // Retorno da Api. Caso encontre o curso retorna o mesmo com ok (httpStatus 200)
        // Caso não encontre retorna notFound (httpStatus 404)
        return  courseService.findById(idCourse)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exemplo Metodo POST utilizando a anotacao ResponseStatus para retorno do HttpStatus.CREATED = 201
     * retornando o HttpStatus como anotacao ResponseStatus
     * @param course
     * @return
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        return courseService.create(course);
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
        return courseService.update(idCourse, course)
                .map(courseFound -> ResponseEntity.ok().body(courseFound))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Exemplo Metodo DELETE HardCore pois deleta realmente o registro da base de dados
     * utilizando a anotacao ResponseStatus para retorno do HttpStatus.NO_CONTENT = 204 ao qual nao retorna conteúdo
     * @param idCourse
     * @return
     */
    @DeleteMapping("/{idCourse}")
    public ResponseEntity<Void> delete(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        // Caso tenha conseguido deletar o registro retorna NO_CONTENT (http status 204 de sucesso)
        if (courseService.delete(idCourse)) {
           return  ResponseEntity.noContent().build();
        }
        // Caso não tenha conseguido deletar o registro retorna NOT_FOUND (http status 404 de sucesso)
        return ResponseEntity.notFound().build();
    }

}
