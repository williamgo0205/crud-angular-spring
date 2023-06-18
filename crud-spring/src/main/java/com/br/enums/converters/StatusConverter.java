package com.br.enums.converters;

import com.br.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/*
* Classe utilizada para converter o ENUM Status em uma String
* @Converter - Aplica a conversao sempre que necessário de forma automática
*/
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
       if (status == null) {
           return null;
       }
       return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        // Faz um Stream buscando todos os valores dos status e compara com o valor passdo via parametro (value)
        // Se for o mesmo retorna o primeiro valor encontrado (findFirst)
        // lança uma exception de argumento inválido (IllegalArgumentException)
       return Stream.of(Status.values())
               .filter(c -> c.getValue().equals(value))
               .findFirst()
               .orElseThrow(IllegalArgumentException::new);
    }

}
