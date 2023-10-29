package com.app.project.controller;

import com.app.project.model.Message;
import com.app.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class MessagesController {
    @Autowired
    private MessageService userService;

    @PostMapping("/register")
    public void register(Message user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public void login(Message user) {
        userService.login(user);
    }


    public Message getUser(Long id) {
        return userService.getUser(id);
    }


    public void updateUser(Message user) {

    }


    public void deleteUser(Message user) {

    }
}
