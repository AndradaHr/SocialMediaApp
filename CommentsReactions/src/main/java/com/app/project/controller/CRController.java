package com.app.project.controller;

import com.app.project.model.CR;
import com.app.project.service.CRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class CRController {
    @Autowired
    private CRService userService;

    @PostMapping("/register")
    public void register(CR user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public void login(CR user) {
        userService.login(user);
    }

    public CR getUser(Long id) {
        return userService.getUser(id);
    }

    public void updateUser(CR user) {

    }

    public void deleteUser(CR user) {

    }
}
