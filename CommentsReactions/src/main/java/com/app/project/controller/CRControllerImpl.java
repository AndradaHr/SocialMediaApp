package com.app.project.controller;

import com.app.project.controller.interfaces.CRController;
import com.app.project.model.CR;
import com.app.project.service.CRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class CRControllerImpl implements CRController {
    @Autowired
    private CRService userService;

    @PostMapping
    public void register(CR user) {
        userService.register(user);
    }

    @PostMapping
    public void login(CR user) {
        userService.login(user);
    }

    @Override
    public CR getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public void updateUser(CR user) {

    }

    @Override
    public void deleteUser(CR user) {

    }
}
