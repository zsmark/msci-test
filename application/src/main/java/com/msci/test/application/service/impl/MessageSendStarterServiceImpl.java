package com.msci.test.application.service.impl;

import com.msci.test.application.service.MessageSendStarterService;
import com.msci.test.producer.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MessageSendStarterServiceImpl implements MessageSendStarterService {

    private ProducerService messageProducer;

    @Override
    public void sendMessages(int messageCount) {
        Stream.of("A", "B", "C", "D")
                .forEach(prefix ->messageProducer.sendMessageByCountAndPrefix(messageCount, prefix));
    }

}
