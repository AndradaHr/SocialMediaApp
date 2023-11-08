package com.app.project.serviceUser;

import com.app.project.model.User;
import com.app.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userDAO;

    @Autowired
    public UserService(UserRepository userDAO) {
        this.userDAO = userDAO;
    }

    public Mono<User> getUser(Long id) {
        return userDAO.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

}



