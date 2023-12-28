package com.app.project.controller;


import com.app.project.model.Message;
import com.app.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
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
}
