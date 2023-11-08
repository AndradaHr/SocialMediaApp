package com.app.project.service;

import com.app.project.model.User;
import com.app.project.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User saveUser(@NonNull User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        checkIfValueInUse(user.getUsername(), "Username already in use", this::isUsernameInUse);
        checkIfValueInUse(user.getPhoneNumber(), "Phone number is already in use", this::isPhoneNumberInUse);
        checkIfValueInUse(user.getEmail(), "Email already in use", this::isEmailInUse);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void changePassword(Long encodedUserId, String oldPassword, String newPassword) {
        Long userIdTrue=Long.parseLong( new String(Base64.getDecoder().decode(String.valueOf(encodedUserId))));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(userIdTrue)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), oldPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }


    }
    public void forgotPassword(Long encodedUserId, String newPassword) {
        Long userIdTrue=Long.parseLong( new String(Base64.getDecoder().decode(String.valueOf(encodedUserId))));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(userIdTrue)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private void checkIfValueInUse(String value, String errorMessage, Predicate<String> checkFunction) {
        if (value != null && checkFunction.test(value)) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public Mono<User> getUser(Long id) {
        return Mono.just(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public Flux<User> getAllUsers() {
        return Flux.defer(() -> Flux.fromIterable(userRepository.findAll()))
                .subscribeOn(Schedulers.boundedElastic())
                .delayElements(Duration.ofSeconds(1));
    }


    private boolean isEmailInUse(String email) {
        return findByEmail(email) != null;
    }

    private boolean isUsernameInUse(String username) {
        return findByUsername(username) != null;
    }

    private boolean isPhoneNumberInUse(String phoneNumber) {
        return findByPhoneNumber(phoneNumber) != null;
    }

    public User findByEmail(String email) {
        Optional<User> usern = userRepository.findByUsername(email);
        return usern.orElse(null);
    }

    public User findByUsername(String username) {
        Optional<User> usern = userRepository.findByUsername(username);
        return usern.orElse(null);
    }

    public User findByPhoneNumber(String phoneNumber) {
        Optional<User> usern = userRepository.findByUsername(phoneNumber);
        return usern.orElse(null);
    }

}