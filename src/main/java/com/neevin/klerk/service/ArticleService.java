package com.neevin.klerk.service;

import com.neevin.klerk.dto.AdminMessageDto;
import com.neevin.klerk.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final JmsService jmsService;

    public List<ArticleDto> allArticlesToReview(int page, int perPageCount) {
        return jmsService.getArticles();
    }
}
