package com.app.project.controller.interfaces;

import com.app.project.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface MessagesController {
    @PostMapping
    void register(Message user);

    @PostMapping
    void login(Message user);

    @GetMapping
    Message getUser(Long id);

    @PutMapping
    void updateUser(Message user);

    @DeleteMapping
    void deleteUser(Message user);
}
