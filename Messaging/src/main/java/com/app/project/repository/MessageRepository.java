package com.app.project.repository;

import com.app.project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Fetch all messages between two users
    List<Message> findBySenderAndReceiver(Long sender, Long receiver);
}
