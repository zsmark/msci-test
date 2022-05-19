package com.msci.test.application;

import com.msci.test.model.ProcessedMessage;
import com.msci.test.producer.service.ProducerService;
import com.msci.test.repository.ProcessedMessageRepository;
import lombok.SneakyThrows;
import org.awaitility.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ApplicationE2ETest {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProcessedMessageRepository processedMessageRepository;

    @Test
    @Disabled
    public void whenProducersSendDatasOnMultipleThreads_shouldPreserveMessageOrder() {
        List<String> messages = new ArrayList<>();
        Stream.of("A", "B", "C", "D").forEach(prefix -> {
            Runnable runnable = () ->
                    IntStream.range(0, 10).forEach(i -> {
                        String message = prefix + i;
                        messages.add(message);
                        producerService.sendMessage(message);
                    });
            Thread thread = new Thread(runnable);
            thread.start();
        });

        await()
                .atMost(Duration.ONE_MINUTE)
                .with()
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .until(() -> processedMessageRepository.count() == messages.size());

        List<String> processedMessageListWithoutPostfix = processedMessageRepository.findAllByOrderByIdAsc().stream()
                .map(ProcessedMessage::getMessage)
                .map(message -> message.replace("processed", ""))
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(messages.toArray(new String[]{}), processedMessageListWithoutPostfix.toArray());
    }

    @Test
    public void whenProducersGenerateDataFirst_shouldPreserveMessageOrder() {
        List<String> messages = new ArrayList<>();
        Stream.of("A", "B", "C", "D").parallel().forEach(prefix -> {
            IntStream.range(0, 10).parallel().forEach(i -> {
                String message = prefix + i;
                messages.add(message);
                producerService.sendMessage(message);
            });
        });

        messages.forEach(message -> producerService.sendMessage(message));

        await()
                .atMost(Duration.ONE_MINUTE)
                .with()
                .pollInterval(Duration.ONE_HUNDRED_MILLISECONDS)
                .until(() -> processedMessageRepository.count() == messages.size());

        List<String> processedMessageListWithoutPostfix = processedMessageRepository.findAllByOrderByIdAsc().stream()
                .map(ProcessedMessage::getMessage)
                .map(message -> message.replace("processed", ""))
                .collect(Collectors.toList());

        Assertions.assertArrayEquals(messages.toArray(new String[]{}), processedMessageListWithoutPostfix.toArray());

    }
}
