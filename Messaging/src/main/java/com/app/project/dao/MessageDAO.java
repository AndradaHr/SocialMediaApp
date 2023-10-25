package com.app.project.dao;

import com.app.project.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDAO extends JpaRepository<Message, Long> {
}
