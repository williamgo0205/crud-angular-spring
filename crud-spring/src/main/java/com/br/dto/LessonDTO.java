package com.br.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("name")  @NotNull @NotBlank @Length(min = 5, max = 100) String name,
        @JsonProperty("youtubeUrl")  @NotNull  @NotBlank  @Length(min = 10, max = 11) String youtubeUrl) {
}
