package com.br.enums.converters;

import com.br.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/*
* Classe utilizada para converter o ENUM Category em uma String
* @Converter - Aplica a conversao sempre que necessário de forma automática
*/
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
       if (category == null) {
           return null;
       }
       return category.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        /**
         * Faz um Stream buscando todos os valores de categorias e compara com o valor passdo via parametro (value)
         * se for o mesmo retorna o primeiro valor encontrado (findFirst)
         * lança uma exception de argumento inválido (IllegalArgumentException)
         */
       return Stream.of(Category.values())
               .filter(c -> c.getValue().equals(value))
               .findFirst()
               .orElseThrow(IllegalArgumentException::new);
    }
}
