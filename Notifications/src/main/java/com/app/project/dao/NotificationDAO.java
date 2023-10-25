package com.app.project.dao;

import com.app.project.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDAO extends JpaRepository<Notification, Long> {
}
