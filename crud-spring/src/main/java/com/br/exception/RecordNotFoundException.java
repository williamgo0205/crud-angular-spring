package com.br.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1942305967176481251L;
    private static final String REGISTRY_NOT_FOUND_WITH_ID = "Registro não encontrado com o id: ";

    // Criando uma exception que repassa o ID para saber qual o identificador que não foi encontrado
    public RecordNotFoundException(Long id) {
        super(REGISTRY_NOT_FOUND_WITH_ID + id);
    }
}
