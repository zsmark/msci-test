package com.msci.test.service;

import java.util.List;

public interface ProcessedMessageService {

    void saveMessage(String message);

    List<String> findAll();
}
