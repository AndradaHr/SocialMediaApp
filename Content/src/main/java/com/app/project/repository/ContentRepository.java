package com.app.project.repository;

import com.app.project.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository

public interface ContentRepository extends JpaRepository<Content, Long> {
    Optional<Content> findAllByUserId(Long userId);
}

