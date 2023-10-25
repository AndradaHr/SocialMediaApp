package com.app.project.controller;

import com.app.project.controller.interfaces.MessagesController;
import com.app.project.model.Message;
import com.app.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class MessagesControllerImpl implements MessagesController {
    @Autowired
    private MessageService userService;

    @PostMapping
    public void register(Message user) {
        userService.register(user);
    }

    @PostMapping
    public void login(Message user) {
        userService.login(user);
    }

    @Override
    public Message getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(Message user) {

    }

    @Override
    public void deleteUser(Message user) {

    }
}
