package com.br.controller;

import com.br.model.Course;
import com.br.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
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
    public Course findById(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        // Retorno da Api. Caso encontre o curso retorna o mesmo com ok (httpStatus 200)
        // Caso não encontre retorna notFound (httpStatus 404) na classe ApplicationControllerAdvice para o metodo handleNotFoundException
        return courseService.findById(idCourse);
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
    public Course update(@PathVariable("idCourse") @NotNull @Positive Long idCourse,
                         @RequestBody @Valid Course course) {
        // Busca o curso e caso seja valido atualiza os dados do curso com as informacoes
        // Caso nao encontre o curso retorna o ResponseEntity.notFound() na classe ApplicationControllerAdvice para o metodo handleNotFoundException
        return courseService.update(idCourse, course);
    }

    /**
     * Exemplo Metodo DELETE HardCore pois deleta realmente o registro da base de dados
     * utilizando a anotacao ResponseStatus para retorno do HttpStatus.NO_CONTENT = 204 ao qual nao retorna conteúdo
     * retornando o HttpStatus como anotacao ResponseStatus
     * @param idCourse
     * @return
     */
    @DeleteMapping("/{idCourse}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("idCourse") @NotNull @Positive Long idCourse) {
        // Caso tenha conseguido deletar o registro retorna NO_CONTENT (http status 204 de sucesso)
        // tratamento de excessao caso ocorra e feito na classe ApplicationControllerAdvice para o metodo handleNotFoundException
        courseService.delete(idCourse);
    }

}
