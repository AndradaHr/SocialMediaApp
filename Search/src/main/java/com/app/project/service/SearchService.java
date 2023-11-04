package com.app.project.service;

import com.app.project.model.User;
import com.app.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SearchService {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> saveUser(User user) {
        return Mono.just(userRepository.save(user));
    }

    public Mono<Void> deleteUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                })
                .then(Mono.empty());
    }

    public Mono<User> getUserById(Long userId) {
        return Mono.justOrEmpty(userRepository.findById(userId));
    }

    public Flux<User> findAllUsers() {
        return Flux.fromIterable(userRepository.findAll());
    }
}
