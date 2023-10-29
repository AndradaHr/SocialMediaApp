package com.app.project.service;

import com.app.project.model.User;
import com.app.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.function.Function;

import static com.app.project.util.Validators.passwordNumberValidator;
import static com.app.project.util.Validators.passwordSpecialCharacterValidator;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email");
        }
        if (user.getPassword().length() < 8) {
            throw new RuntimeException("Password too short");
        }
        if (!passwordSpecialCharacterValidator(user.getPassword())) {
            throw new RuntimeException("Password should contain special characters");
        }
        if (!passwordNumberValidator(user.getPassword())) {
            throw new RuntimeException("Password should contain numbers");
        }
        if (Period.between(user.getBirthdate(), LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("You should be at least 18 years old");
        }
        userRepository.save(user);
    }

    public void login(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }

    public Mono<User> getUser(Long id) {
        return Mono.just(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }


    public Flux<User> getAllUsers() {
        // Assume userRepository.findAll() is a blocking call and should be scheduled to run on a different thread.
        // We can use Flux.defer to lazily fetch the users only when the Flux is actually subscribed to.
        return Flux.defer(() -> Flux.fromIterable(userRepository.findAll()))
                // Schedule this to run on a boundedElastic scheduler if it's a blocking call.
                .subscribeOn(Schedulers.boundedElastic())
                // Add a delay between each element.
                .delayElements(Duration.ofSeconds(1));
    }


    public void updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

    }
}
