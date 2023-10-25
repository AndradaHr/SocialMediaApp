package com.app.project.controller;

import com.app.project.controller.interfaces.ContentController;
import com.app.project.model.Content;
import com.app.project.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class ContentControllerImpl implements ContentController {
    @Autowired
    private ContentService userService;

    @PostMapping
    public void register(Content user) {
        userService.register(user);
    }

    @PostMapping
    public void login(Content user) {
        userService.login(user);
    }

    @Override
    public Content getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(Content user) {

    }

    @Override
    public void deleteUser(Content user) {

    }
}
