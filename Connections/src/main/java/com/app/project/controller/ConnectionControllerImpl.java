package com.app.project.controller;

import com.app.project.controller.interfaces.ConnectionController;
import com.app.project.model.Connection;
import com.app.project.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ConnectionControllerImpl implements ConnectionController {
    @Autowired
    private ConnectionService userService;

    @PostMapping
    public void register(Connection user) {
        userService.register(user);
    }

    @PostMapping
    public void login(Connection user) {
        userService.login(user);
    }

    @Override
    public Connection getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(Connection user) {

    }

    @Override
    public void deleteUser(Connection user) {

    }
}
