package com.app.project.repository;

import com.app.project.model.Content;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository

public interface ContentRepository extends ReactiveCrudRepository<Content, Long> {
    Flux<Content> findAllByUserId(Long userId);
}

