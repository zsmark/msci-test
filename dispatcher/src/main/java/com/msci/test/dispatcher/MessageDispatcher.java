package com.msci.test.dispatcher;

import com.msci.test.consumer.service.MessageConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.msci.test.dispatcher.configuration.JmsConfig.MESSAGE_QUEUE_NAME;

@Slf4j
@Component
public class MessageDispatcher {


    private final MessageConsumerService firstMessageConsumerService;

    private final MessageConsumerService secondMessageConsumerService;

    public MessageDispatcher(@Qualifier("FirstConsumer") MessageConsumerService firstMessageConsumerService,
                             @Qualifier("SecondConsumer") MessageConsumerService secondMessageConsumerService) {
        this.firstMessageConsumerService = firstMessageConsumerService;
        this.secondMessageConsumerService = secondMessageConsumerService;
    }

    @JmsListener(destination = MESSAGE_QUEUE_NAME)
    public void receiveMessage(@Payload String message) {
        log.info("Message received from queue {}", message);
        if (message.startsWith("A") || message.startsWith("B")) {
            firstMessageConsumerService.consumeMessage(message);
        } else {
            secondMessageConsumerService.consumeMessage(message);
        }
    }

}
