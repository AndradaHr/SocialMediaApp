package com.app.project.repository;

import com.app.project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Query("SELECT u FROM Message u WHERE u.receiver = :receiver")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver(String receiver);
}
