package com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
}
