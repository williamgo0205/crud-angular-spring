package com.br.model;

import com.br.enums.Category;
import com.br.enums.Status;
import com.br.enums.converters.CategoryConverter;
import com.br.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
// Anotação - @SQLDelete é possivel repassar o sql que queremos que o hibernate execute toda vez que ele executar o delete do registro
@SQLDelete(sql = "UPDATE course SET status = 'Inativo' WHERE id = ?")
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
    @Column(name = "status", length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @NotNull
    @NotEmpty
    @Valid
    // Relacionamento OneToMany (Uma aula para muitos cursos)
    // cascade:
    //       CascadeType.ALL - nesse caso se um curso for removido, também é removida a aula
    //       orphanRemoval = true - remove os registros que ficaram orfãos
    //        mappedBy = "course" - Mapeamento realizado também para a classe Course
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    // JoinColum - Indica qual o nome da coluna que é feito o JOIN, nesse caso (course_id)
    // @JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(Long id, String name, Category category, Status status, List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.lessons = lessons;
    }

    public Course() {

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", category=").append(category);
        sb.append(", status=").append(status);
        sb.append(", lessons=").append(lessons);
        sb.append('}');
        return sb.toString();
    }
}
