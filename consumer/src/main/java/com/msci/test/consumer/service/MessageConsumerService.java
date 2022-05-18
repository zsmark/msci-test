package com.msci.test.consumer.service;

import org.springframework.scheduling.annotation.Async;

public interface MessageConsumerService {

    void consumeMessage(String message);

    default String processMessage(String message){
        return message + "processed";
    }
}
