package com.neevin.klerk.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentDto {
    @JsonView
    @Size(min = 1, max = 100, message = "Слишком короткий или длинный комментарий")
    private String comment;
}