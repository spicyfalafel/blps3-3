package com.neevin.klerk.mapper;

import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.entity.Article;
import com.neevin.klerk.entity.ArticleStatus;
import com.neevin.klerk.entity.Tag;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public ArticleDto toDto(Article article) {
        return new ArticleDto(
            article.getId(),
            article.getHeader(),
            article.getShortDescription(),
            article.getArticleText(),
            article.getCoverImageLink(),
            article.getCoverVideoLink(),
            article.getTags().stream().map(Tag::getId).collect(Collectors.toSet()),
            article.getCreationDate().toString()
        );
    }

    public Article fromDto(ArticleDto articleDto) {
        return new Article(
                articleDto.getId(),
                articleDto.getHeader(),
                articleDto.getShortDescription(),
                articleDto.getArticleText(),
                articleDto.getCoverImageLink(),
                articleDto.getCoverVideoLink(),
                articleDto.getTags().stream().map(x->new Tag(x, "")).collect(Collectors.toSet()),
                ArticleStatus.NOT_CHECKED,
                null,
                new Date(System.currentTimeMillis())
        );
    }
}
