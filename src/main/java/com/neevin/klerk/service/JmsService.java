package com.neevin.klerk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neevin.klerk.dto.AdminMessageDto;
import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.entity.Article;
import com.neevin.klerk.mapper.ArticleMapper;
import com.neevin.klerk.repository.ArticleRepository;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class JmsService {
    @Value("${rabbitmq.admin.queuename}")
    private String adminQueueName;

    @Value("${rabbitmq.reviews.queuename}")
    private String reviewsQueueName;
    private final ArticleRepository articleRepository;
    private final JmsTemplate jmsTemplate;
    private final ConnectionFactory connectionFactory;

    public void sendAdminReviewMessage(AdminMessageDto messageDto) {
        try (Connection clientConnection = connectionFactory.createConnection()) {
            clientConnection.start();
            this.jmsTemplate.convertAndSend(reviewsQueueName, messageDto);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> getArticles(int page, int perPageCount) {
        List<Article> articles = new LinkedList<>(articleRepository.allArticlesToReview(page, perPageCount));
        try (Connection clientConnection = connectionFactory.createConnection()) {
            clientConnection.start();
            JmsTemplate tpl = new JmsTemplate(connectionFactory);
            tpl.setReceiveTimeout(2000);
            Object message;
            do {
                message = tpl.receiveAndConvert(adminQueueName);
                if (message == null){ // there are no new messages
                    return articles;
                }
                if (message instanceof String) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Article article = objectMapper.readValue((String) message, Article.class);
                    articles.add(article);
                    articleRepository.save(article);
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            return articles;
        }
    }
}
