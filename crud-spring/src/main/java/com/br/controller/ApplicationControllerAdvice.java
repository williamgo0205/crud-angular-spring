package com.br.controller;

import com.br.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Classe de controller de tratamento de excessoes de toda a aplicacao
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

    /**
     * criado o método handleNotFoundException que irá tratar o retorno da excessao
     * nesse caso da classe criada RecordNotFoundException
     *
     * @ExceptionHandler - indica a classe a ser tratada no exception
     * @ResponseStatus - Indica qual status será retornado, nesse caso HttpStatus.NOT_FOUND (404)
     */
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return String.format("Error: %s", ex.getMessage());
    }

    /**
     * criado o método handleMethodArgumentNotValidException que irá tratar o retorno da excessao
     * nesse caso da classe MethodArgumentNotValidException
     *
     * @ExceptionHandler - indica a classe a ser tratada no exception
     * @ResponseStatus - Indica qual status será retornado, nesse caso HttpStatus.BAD_REQUEST (400)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + "\n");
    }

    /**
     * criado o método handleConstraintViolationException que irá tratar o retorno da excessao
     * nesse caso da classe ConstraintViolationException
     *
     * @ExceptionHandler - indica a classe a ser tratada no exception
     * @ResponseStatus - Indica qual status será retornado, nesse caso HttpStatus.BAD_REQUEST (400)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath() + " " + error.getMessage())
                .reduce("", (acc, error) -> acc + error + "\n");
    }

    /**
     * criado o método handleMethodArgumentTypeMismatchException que irá tratar o retorno da excessao
     * nesse caso da classe MethodArgumentTypeMismatchException
     *
     * @ExceptionHandler - indica a classe a ser tratada no exception
     * @ResponseStatus - Indica qual status será retornado, nesse caso HttpStatus.BAD_REQUEST (400)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex != null && ex.getRequiredType() != null) {
            String type = ex.getRequiredType().getName();
            // Utilizando a  String[] para obter o nome do campo de "java.lang.Long" para obrer apenas o "Long" através do typeName
            String[] typeParts = type.split("\\.");
            String typeName = typeParts[typeParts.length -1];

            return ex.getName() + " should be of type " + typeName;
        }
        return "Argument type not valid";
    }
}
