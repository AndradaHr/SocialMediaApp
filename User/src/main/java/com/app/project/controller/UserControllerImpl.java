package com.app.project.controller;

import com.app.project.controller.interfaces.UserController;
import com.app.project.model.User;
import com.app.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void register(User user) {
        userService.register(user);
    }

    @PostMapping
    public void login(User user) {
        userService.login(user);
    }

    @Override
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
