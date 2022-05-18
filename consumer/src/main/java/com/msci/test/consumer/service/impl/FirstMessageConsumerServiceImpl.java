package com.msci.test.consumer.service.impl;

import com.msci.test.consumer.service.MessageConsumerService;
import com.msci.test.service.ProcessedMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.msci.test.consumer.service.MessageConsumerService.FIRST_CONSUMER_QUALIFIER;

@Slf4j
@Qualifier(FIRST_CONSUMER_QUALIFIER)
@Service
@AllArgsConstructor
public class FirstMessageConsumerServiceImpl implements MessageConsumerService {

    private ProcessedMessageService processedMessageService;

    @Override
    public void consumeMessage(String message) {
        log.info("Processing incoming message {}", message);
        processedMessageService.saveMessage(processMessage(message));
        log.info("{} message processed!", message);
    }
}
