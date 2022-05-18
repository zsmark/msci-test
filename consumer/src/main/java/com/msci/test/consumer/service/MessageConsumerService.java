package com.msci.test.consumer.service;

public interface MessageConsumerService {

    String FIRST_CONSUMER_QUALIFIER = "FirstConsumer";
    String SECOND_CONSUMER_QUALIFIER = "SecondConsumer";
    String PROCESSED_POSTFIX = "processed";

    void consumeMessage(String message);

    default String processMessage(String message) {
        if (message != null) {
            return message + PROCESSED_POSTFIX;
        }
        throw new IllegalArgumentException("Message cannot be null!");
    }
}
