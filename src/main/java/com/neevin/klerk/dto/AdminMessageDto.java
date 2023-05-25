package com.neevin.klerk.dto;

import com.neevin.klerk.entity.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminMessageDto {
    private Integer articleId;
    private ArticleStatus articleStatus;

    private CommentDto commentDto;

}
