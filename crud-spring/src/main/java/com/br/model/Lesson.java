package com.br.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

// Classe refernte as aulas (Lesson) de um curso
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    @JsonProperty("name")
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 10, max = 11)
    @Column(length = 11, nullable = false)
    @JsonProperty("youTubeUrl")
    private String youTubeUrl;

    @NotNull
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

    public Lesson(Long id, String name, String youTubeUrl, Course course) {
        this.id = id;
        this.name = name;
        this.youTubeUrl = youTubeUrl;
        this.course = course;
    }

    public Lesson() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        final StringBuffer builder = new StringBuffer("Lesson [");
        builder.append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", youTubeUrl='").append(youTubeUrl).append('\'')
                .append(", course=").append(course)
                .append(']');
        return builder.toString();
    }
}
