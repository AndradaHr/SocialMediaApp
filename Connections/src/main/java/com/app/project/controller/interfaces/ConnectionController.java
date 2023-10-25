package com.app.project.controller.interfaces;

import com.app.project.model.Connection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface ConnectionController {
    @PostMapping
    void register(Connection user);

    @PostMapping
    void login(Connection user);

    @GetMapping
    Connection getUser(Long id);

    @PutMapping
    void updateUser(Connection user);

    @DeleteMapping
    void deleteUser(Connection user);
}
