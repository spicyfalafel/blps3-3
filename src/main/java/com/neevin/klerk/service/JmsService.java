package com.neevin.klerk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neevin.klerk.dto.AdminMessageDto;
import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.mapper.ArticleMapper;
import com.neevin.klerk.repository.ArticleRepository;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
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

    private final ArticleRepository articleRepository;
    private final JmsTemplate jmsTemplate;
    private final ConnectionFactory connectionFactory;

    public void sendAdminMessage(AdminMessageDto messageDto) throws Exception {
        try (Connection clientConnection = connectionFactory.createConnection()) {
            clientConnection.start();
            this.jmsTemplate.convertAndSend(adminQueueName, messageDto);
        }
    }

    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtos = new LinkedList<>();
        try (Connection clientConnection = connectionFactory.createConnection()) {
            clientConnection.start();
            JmsTemplate tpl = new JmsTemplate(connectionFactory);
            tpl.setReceiveTimeout(2000);
            Object message;
            do {
                message = tpl.receiveAndConvert(adminQueueName);
                if (message == null){ // there are no new messages
                    return articleDtos;
                }
                if (message instanceof String) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ArticleDto articleDto = objectMapper.readValue((String) message, ArticleDto.class);
                    articleDtos.add(articleDto);
                    articleRepository.save(new ArticleMapper().fromDto(articleDto));
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
            return articleDtos;
        }
    }
}
