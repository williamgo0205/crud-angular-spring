package com.br.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

/**
 * Link: https://www.guiadojava.com.br/2021/04/java-records.html
 *
 * *** Record ***
 * Esta funcionalidade da linguagem apareceu pela primeira vez na versão 14 como experimental e assim continuou até a versão 15.
 * Agora liberada de forma definitiva no Java 16.
 *
 * O objetivo é ser possível ter classes que atuam como portadores transparentes de dados imutáveis. Os registros podem ser considerados
 * tuplas nominais. Ou seja, após criado, um record não pode mais ser alterado.
 *
 * Records oferece uma uma sintaxe compacta para declarar classes que são portadores transparentes para dados imutáveis superficiais
 * visando reduzir significamente o detalhamento dessas classes e irá melhorar a capacidade de leitura e manutenção do código.
 */
public record CourseDTO(@JsonProperty("_id") Long id,
                        @JsonProperty("name") @NotBlank @NotNull @Length(min = 5, max = 200) String name,
                        @JsonProperty("category") @NotNull @Length(min = 10) @Pattern(regexp = "Back-end|Front-end") String category) {
}
