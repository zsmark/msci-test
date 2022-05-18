package com.msci.test.consumer.service.impl;

import com.msci.test.consumer.service.MessageConsumerService;
import com.msci.test.service.ProcessedMessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("SecondConsumer")
@Service
@AllArgsConstructor
public class SecondMessageConsumerServiceImpl implements MessageConsumerService {

    private ProcessedMessageService processedMessageService;

    @Override
    public void consumeMessage(String message) {
        processedMessageService.saveMessage(processMessage(message));
    }
}
