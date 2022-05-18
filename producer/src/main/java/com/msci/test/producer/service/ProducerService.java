package com.msci.test.producer.service;

public interface ProducerService {

    void sendMessageByCountAndPrefix(int messageCount, String prefix);

    void sendMessage(String message);
}
