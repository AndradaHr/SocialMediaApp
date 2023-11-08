package com.app.project.repository;

import com.app.project.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> {

    Flux<Post> findByUserId(Long userId);
}
