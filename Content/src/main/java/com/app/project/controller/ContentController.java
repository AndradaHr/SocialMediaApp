package com.app.project.controller;

import com.app.project.model.Content;
import com.app.project.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ContentController {
    @Autowired
    private ContentService userService;

    @PostMapping("/register")
    public void register(Content user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public void login(Content user) {
        userService.login(user);
    }

    public Content getUser(Long id) {
        return userService.getUser(id);
    }

    public void updateUser(Content user) {

    }

    public void deleteUser(Content user) {

    }
}
