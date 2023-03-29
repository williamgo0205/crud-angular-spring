package com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
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
    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(name = "category", length = 10, nullable = false)
    @JsonProperty("category")
    private String category;

    @NonNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(name = "status", length = 10, nullable = false)
    @JsonProperty("status")
    private String status = "Ativo";
}
