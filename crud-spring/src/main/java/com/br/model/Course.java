package com.br.model;

import com.br.enums.Category;
import com.br.enums.Status;
import com.br.enums.converters.CategoryConverter;
import com.br.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor(force = true)
// Anotação - @SQLDelete é possivel repassar o sql que queremos que o hibernate execute toda vez que ele executar o delete do registro
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
// Anotação - @Where é possivel filtrar no select where a clausula repassada, nesse caso "status = 'Ativo'
@Where(clause = "status = 'Ativo'")
//@Table(name = "cursos")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NonNull
    @Length(min = 5, max = 200)
    @Column(name = "name", length = 200, nullable = false)
    @JsonProperty("name")
    private String name;

    @NonNull
    @Column(name = "category", length = 10, nullable = false)
    @JsonProperty("category")
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NonNull
    @JsonProperty("status")
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;
}
