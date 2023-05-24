package com.neevin.klerk.entity;

import com.neevin.klerk.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "header")
    private String header;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "article_text")
    private String articleText;

    @Column(name = "cover_image_link")
    private String coverImageLink;

    @Column(name = "cover_video_link")
    private String coverVideoLink;

    @ManyToMany
    @Column(name = "tags")
    private Set<Tag> tags;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ArticleStatus articleStatus;

    private Integer userId;

    @Column(name = "creation_date")
    @Basic
    private java.sql.Date creationDate;

//    public static Article fromDto(ArticleDto articleDto) {
//        return new Article(articleDto.getId(),
//                articleDto.getHeader(),
//                articleDto.getShortDescription(),
//                articleDto.getArticleText(),
//                articleDto.getCoverImageLink(),
//                articleDto.getCoverVideoLink(),
//                articleDto.getTags().stream().map(t -> new Tag(t, "")).collect(Collectors.toSet()),
//                ArticleStatus.NEED_REVIEW,
//                1, articleDto.getCreationDate());
//    }
}
