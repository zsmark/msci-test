package com.msci.test.application.resource;

import com.msci.test.application.service.MessageSendStarterService;
import com.msci.test.service.ProcessedMessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    private MessageSendStarterService messageSendStarterService;
    private ProcessedMessageService processedMessageService;

    @PostMapping("/actions/send-messages")
    public void sendMessages(@RequestParam Integer messageCount){
        messageSendStarterService.sendMessages(messageCount);
    }

    @GetMapping("")
    public List<String> getAllMessages(){
        return processedMessageService.findAll();
    }

}
