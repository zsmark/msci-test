package com.msci.test.application.controller;

import com.msci.test.producer.service.ProducerService;
import com.msci.test.service.ProcessedMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    private ProducerService messageProducer;

    private ProcessedMessageService processedMessageService;

    @PostMapping("/actions/send-messages")
    public void sendMessages(@RequestParam Integer messageCount){
        Stream.of("A", "B", "C", "D")
                .forEach(prefix -> messageProducer.sendMessage(messageCount, prefix));
    }

    @GetMapping("")
    public List<String> getAllMessages(){
        return processedMessageService.findAll();
    }

}
