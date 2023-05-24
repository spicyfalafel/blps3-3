package com.neevin.klerk.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    @Null(message = "Нельзя указать id")
    private Integer id;

    @JsonView
    @Size(min = 1, max = 32, message = "Слишком короткий или длинный заголовок")
    @NotNull(message = "Заголовок статьи не должен быть пустым")
    private String header;

    @JsonView
    @Size(min = 1, max = 64, message = "Слишком короткое или длинное короткое описание")
    @NotNull(message = "Короткое описание не должно быть пустым")
    private String shortDescription;

    @JsonView
    @Size(min = 1, max = 2048, message = "Слишком короткий или длинный текст статьи")
    @NotNull(message = "Текст статьи не должен быть пустым")
    private String articleText;

    @JsonView
    @Size(min = 1, max = 128, message = "Слишком короткая или длинная ссылка на изображение")
    @NotNull(message = "Ссылка на изображение не должна быть пустой")
    private String coverImageLink;

    @JsonView
    @Size(min = 1, max = 64, message = "Слишком короткая или длинная ссылка на видео")
    @Pattern(
            regexp = "^https://www\\.youtube\\.com/watch\\?v=[a-zA-Z0-9\\-]+$",
            message = "Можно добавить только ссылку на youtube"
    )
    private String coverVideoLink;

    @JsonView
    private Set<Integer> tags;

    @Null(message = "Нельзя указать дату создания")
    private String creationDate;
}
