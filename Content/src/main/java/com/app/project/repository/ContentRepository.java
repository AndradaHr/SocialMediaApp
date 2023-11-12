package com.app.project.repository;

import com.app.project.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT p from Content p where p.userId= :userId")
    Content findByUserId(Long userId);
}