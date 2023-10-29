package com.app.project.controller;

import com.app.project.model.Notification;
import com.app.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class NotificationController {
    @Autowired
    private NotificationService userService;

    @PostMapping("/register")
    public void register(Notification user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public void login(Notification user) {
        userService.login(user);
    }


    public Notification getUser(Long id) {
        return userService.getUser(id);
    }


    public void updateUser(Notification user) {

    }


    public void deleteUser(Notification user) {

    }
}
