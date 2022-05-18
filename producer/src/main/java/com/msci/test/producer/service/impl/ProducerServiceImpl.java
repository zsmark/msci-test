package com.msci.test.producer.service.impl;

import com.msci.test.producer.service.ProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@AllArgsConstructor
@Service
public class ProducerServiceImpl implements ProducerService {

    public static final String MESSAGE_QUEUE_NAME = "message-queue";

    private JmsTemplate jmsTemplate;

    @Async
    @Override
    public void sendMessageByCountAndPrefix(int messageCount, String prefix) {
        log.info("Sending {} message with prefix {}", messageCount, prefix);
        IntStream.range(0, messageCount).forEach(i -> sendMessage(prefix + i));
    }

    @Override
    public void sendMessage(String message) {
        log.info("Sending message {}", message);
        jmsTemplate.convertAndSend(MESSAGE_QUEUE_NAME, message);
    }
}
