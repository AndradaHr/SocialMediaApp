package com.app.project.service;

import com.app.project.repository.MessageRepository;
import com.app.project.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        message.setIsRead(false);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesForUser(Long username) {
        return messageRepository.findByReceiver(username);
    }

    public Message readMessage(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setIsRead(true);
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        messageRepository.delete(message);
    }
}
