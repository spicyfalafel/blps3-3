package com.neevin.klerk.repository;

import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.entity.Article;
import com.neevin.klerk.entity.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
//    @Query(value = "select * from articles where article_status = 'CHECKED' limit :limit offset :offset", nativeQuery = true)
//    List<Article> allArticles(
//            @Param("limit") Integer limit,
//            @Param("offset") Integer offset
//    );


    @Query(value = "select * from articles where article_status = 'NEED_REVIEW' limit :limit offset :offset", nativeQuery = true)
    List<Article> allArticlesToReview(
            @Param("limit") Integer limit,
            @Param("offset") Integer offset
    );
//
//    Optional<Article> getArticleByIdAndArticleStatus(@Param("id") Integer id, @Param("article_status") ArticleStatus articleStatus);
//
//
//    List<Article> getAllByIdAndArticleStatus(@Param("id") Integer id, @Param("article_status") ArticleStatus articleStatus);
//
//
//    @Query(value = "select * from articles where user_id = :id", nativeQuery = true)
//    List<Article> getArticlesByUserId(Integer id);
//
//    @Query(value = "select count(*) from articles where user_id = :id and creation_date = :date", nativeQuery = true)
//    Integer getCountOfArticlesByUserIdAnsDate(Integer id, Date date);
}
