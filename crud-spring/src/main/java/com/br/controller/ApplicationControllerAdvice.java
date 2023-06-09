package com.br.controller;

import com.br.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe de controller de tratamento de excessoes de toda a aplicacao
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

    /**
     *  criado o método handleNotFoundException que irá tratar o retorno da excessao
     *  nesse caso da classe criada RecordNotFoundException
     *  @ExceptionHandler - indica a classe a ser tratada no exception
     *  @ResponseStatus - Indica qual status será retornado, nesse caso HttpStatus.NOT_FOUND (404)
     */
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return String.format("Error: [%s]", ex.getMessage());
    }
}
