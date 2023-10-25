package com.app.project.controller;

import com.app.project.controller.interfaces.NotificationController;
import com.app.project.model.Notification;
import com.app.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class NotificationControllerImpl implements NotificationController {
    @Autowired
    private NotificationService userService;

    @PostMapping
    public void register(Notification user) {
        userService.register(user);
    }

    @PostMapping
    public void login(Notification user) {
        userService.login(user);
    }

    @Override
    public Notification getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(Notification user) {

    }

    @Override
    public void deleteUser(Notification user) {

    }
}
