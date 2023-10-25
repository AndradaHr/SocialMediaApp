package com.app.project.controller;

import com.app.project.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface UserController {
    @PostMapping
    void register(User user);

    @PostMapping
    void login(User user);

    @GetMapping
    User getUser(Long id);

    @PutMapping
    void updateUser(User user);

    @DeleteMapping
    void deleteUser(User user);
}
