package com.msci.test.dispatcher;

import com.msci.test.consumer.service.MessageConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.msci.test.dispatcher.configuration.JmsConfig.MESSAGE_QUEUE_NAME;

@Slf4j
@Component
public class MessageDispatcher {

    private static final String A_PREFIX = "A";
    private static final String B_PREFIX = "A";

    private final MessageConsumerService firstMessageConsumerService;

    private final MessageConsumerService secondMessageConsumerService;

    public MessageDispatcher(@Qualifier(MessageConsumerService.FIRST_CONSUMER_QUALIFIER) MessageConsumerService firstMessageConsumerService,
                             @Qualifier(MessageConsumerService.SECOND_CONSUMER_QUALIFIER) MessageConsumerService secondMessageConsumerService) {
        this.firstMessageConsumerService = firstMessageConsumerService;
        this.secondMessageConsumerService = secondMessageConsumerService;
    }

    @JmsListener(destination = MESSAGE_QUEUE_NAME)
    public void receiveMessage(@Payload String message) {
        log.info("Message received from queue {}", message);

        if(!StringUtils.hasText(message))
            throw new IllegalArgumentException("Message cannot be null!");

        if (message.startsWith(A_PREFIX) || message.startsWith(B_PREFIX)) {
            firstMessageConsumerService.consumeMessage(message);
        } else {
            secondMessageConsumerService.consumeMessage(message);
        }
    }

}
