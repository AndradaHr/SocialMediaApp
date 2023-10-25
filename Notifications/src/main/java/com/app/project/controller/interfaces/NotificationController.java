package com.app.project.controller.interfaces;

import com.app.project.model.Notification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface NotificationController {
    @PostMapping
    void register(Notification user);

    @PostMapping
    void login(Notification user);

    @GetMapping
    Notification getUser(Long id);

    @PutMapping
    void updateUser(Notification user);

    @DeleteMapping
    void deleteUser(Notification user);
}
