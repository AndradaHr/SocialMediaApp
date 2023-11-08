package com.app.project.repository;

import com.app.project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//
//    Optional<Post> findByUserId(Long userId);
}
