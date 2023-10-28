package com.app.project.repository;

import com.app.project.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionDAO extends JpaRepository<Connection, Long> {
}
