package com.app.project.controller;


import com.app.project.model.Message;
import com.app.project.model.MessageDto;
import com.app.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class WebSocketController {

    private final MessageService messageService;

    @Autowired
    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload Message message) {
        messageService.saveMessage(message);
        return message;
    }

    @MessageMapping("/updateReadStatus")
    public void updateReadStatus(@Payload ReadStatusUpdate update) {
        messageService.updateMessageReadStatus(update.getMessageId(), update.getIsRead());
    }

    @MessageMapping("/updateMessageContent")
    public void updateMessageContent(@Payload MessageContentUpdate update) {
        messageService.updateMessageContent(update.getMessageId(), update.getNewContent());
    }

    @MessageMapping("/deleteMessage")
    public void deleteMessage(@Payload MessageDelete delete) {
        messageService.deleteMessage(delete.getMessageId());
    }

    @GetMapping("/getPersonConversations/{userId}")
    public List<MessageDto> getPersonConversations(@PathVariable Long userId) {
        return messageService.getPersonConversations(userId);
    }

    @GetMapping("/getPersonChat/{userId1}/{userId2}")
    public List<Message>getPersonChat(@PathVariable Long userId1, @PathVariable Long userId2){
        return messageService.getPersonChat(userId1, userId2);
    }

}
