package com.app.project.controller;

import com.app.project.model.Message;
import com.app.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/user/{username}")
    public List<Message> getMessagesForUser(@PathVariable Long username) {
        return messageService.getMessagesForUser(username);
    }

    @PostMapping("/read/{id}")
    public Message readMessage(@PathVariable Long id) {
        return messageService.readMessage(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
