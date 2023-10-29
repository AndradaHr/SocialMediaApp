package com.app.project.controller;

import com.app.project.model.Connection;
import com.app.project.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ConnectionController {
    @Autowired
    private ConnectionService userService;

    @PostMapping("/register")
    public void register(Connection user) {
        userService.register(user);
    }

    @PostMapping("login")
    public void login(Connection user) {
        userService.login(user);
    }

    public Connection getUser(Long id) {
        return userService.getUser(id);
    }

    public void updateUser(Connection user) {

    }

    public void deleteUser(Connection user) {

    }
}
