package com.app.project.controller.interfaces;

import com.app.project.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface SearchController {
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
