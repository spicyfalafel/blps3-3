package com.neevin.klerk.service;

import com.neevin.klerk.dto.AdminMessageDto;
import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.dto.CommentDto;
import com.neevin.klerk.entity.Article;
import com.neevin.klerk.entity.ArticleStatus;
import com.neevin.klerk.exception.ArticleNotFoundException;
import com.neevin.klerk.mapper.ArticleMapper;
import com.neevin.klerk.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final JmsService jmsService;
    private final ArticleRepository articleRepository;

    public List<ArticleDto> allArticlesToReview(int page, int perPageCount) {
        var mapper = new ArticleMapper();
        return jmsService.getArticles(page, perPageCount).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void blockArticle(Integer id) {
        var article = changeArticleStatus(id, ArticleStatus.NEED_REVIEW, ArticleStatus.BLOCKED);
        articleRepository.save(article);
        jmsService.sendAdminReviewMessage(new AdminMessageDto(id, ArticleStatus.BLOCKED, null));
    }
    private Article changeArticleStatus(Integer id, ArticleStatus from, ArticleStatus to) {
        var art = articleRepository.findById(id);
        if (art.isPresent())
        {
            if (art.get().getArticleStatus() != from) {
                throw new ArticleNotFoundException("Article was not " + from.name());
            }
            art.get().setArticleStatus(to);
            return art.get();
        }
        else {
            throw new ArticleNotFoundException();
        }
    }

    public void unblockArticle(Integer id) {
        var article = changeArticleStatus(id, ArticleStatus.BLOCKED, ArticleStatus.CHECKED);
        articleRepository.save(article);
        jmsService.sendAdminReviewMessage(new AdminMessageDto(id, ArticleStatus.CHECKED, null));
    }
    @SneakyThrows
    public void approveArticle(Integer id) {
        var article = changeArticleStatus(id, ArticleStatus.NEED_REVIEW, ArticleStatus.CHECKED);
        jmsService.sendAdminReviewMessage(new AdminMessageDto(id, ArticleStatus.CHECKED, null));
        articleRepository.save(article);
    }

    @SneakyThrows
    public void sendToRework(Integer id, CommentDto commentDto) {
        var article = changeArticleStatus(id, ArticleStatus.NEED_REVIEW, ArticleStatus.NEED_REWORK);
        jmsService.sendAdminReviewMessage(new AdminMessageDto(id, ArticleStatus.NEED_REWORK, commentDto));
        articleRepository.save(article);
    }
}
