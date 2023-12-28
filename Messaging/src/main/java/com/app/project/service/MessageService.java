package com.app.project.service;

import com.app.project.model.Message;
import com.app.project.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        return messageRepository.findBySenderAndReceiver(senderId, receiverId);
    }

    public Message updateMessageReadStatus(Long messageId, Boolean isRead) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
        message.setIsRead(isRead);
        return messageRepository.save(message);
    }

    public Message updateMessageContent(Long messageId, String newContent) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));
        message.setContent(newContent);
        message.setIsEdited(true);
        return messageRepository.save(message);
    }

    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
