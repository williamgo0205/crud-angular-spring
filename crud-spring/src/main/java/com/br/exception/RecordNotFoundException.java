package com.br.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1942305967176481251L;

    // Criando uma exception que repassa o ID para saber qual o identificador que não foi encontrado
    public RecordNotFoundException(Long id) {
        super(String.format("Registro não encontrado com o id: [%s]", id));
    }
}