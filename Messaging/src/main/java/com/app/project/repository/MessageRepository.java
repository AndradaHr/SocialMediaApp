package com.app.project.repository;

import com.app.project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT u FROM Message u WHERE u.receiverId = :receiver")
    List<Message> findByReceiver(Long receiver);
}
