package com.msci.test.service.impl;

import com.msci.test.model.ProcessedMessage;
import com.msci.test.repository.ProcessedMessageRepository;
import com.msci.test.service.ProcessedMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessedMessageServiceImpl implements ProcessedMessageService {

    private ProcessedMessageRepository processedMessageRepository;

    @Override
    @Transactional
    public void saveMessage(String message) {
        ProcessedMessage processedMessage = new ProcessedMessage(message);
        processedMessageRepository.save(processedMessage);
    }

    @Override
    public List<String> findAll() {
        return processedMessageRepository.findAll()

                .stream()
                .map(ProcessedMessage::getMessage)
                .collect(Collectors.toList());
    }
}
