package com.app.project.repository;

import com.app.project.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDAO extends JpaRepository<Content, Long> {
}
