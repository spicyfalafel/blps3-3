package com.neevin.klerk.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    @Null(message = "Нельзя указать id")
    private Integer id;

    @JsonView
    @Length(min = 1, max = 32, message = "Слишком короткое или длинное название тега")
    @NotBlank(message = "Тег не должен быть пустым")
    private String name;
}
