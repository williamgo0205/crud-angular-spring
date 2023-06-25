package com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// Classe refernte as aulas (Lesson) de um curso
@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(length = 100, nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(length = 11, nullable = false)
    @JsonProperty("youTubeUrl")
    private String youTubeUrl;

    //  @ManyToOne - Fazendo o Mapeamento Muitos para Um (Muitos cursos para uma aula)
    //          fetch = FetchType.LAZY - Carrega esse mapeamento apenas quando for excutada a função getCourse
    //                  FetchType.EAGER - Carrega esse mapeamento toda vez que a entidade Lesson for rexecutada/carregada
    //          optional = false - É informado ao Hibernate que essa coluna de curso é obrigatória e não opcional
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // JoinColum - Indica qual o nome da coluna que é feito o JOIN, nesse caso (course_id)
    @JoinColumn(name = "course_id", nullable = false)
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    //    Essa anotação faz apenas o SET como parte dessa deserialização do objeto pois, não será utilizado o GET nessa propriedade
    //    a fim de evitar redundância dessa entidade (LESSON) para (COURSE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Course course;
}
