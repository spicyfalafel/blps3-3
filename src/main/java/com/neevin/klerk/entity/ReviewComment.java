package com.neevin.klerk.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "review_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    @OneToOne
    private Article article;

    public ReviewComment(String comment, Article article) {
        this.comment = comment;
        this.article = article;
    }
}
