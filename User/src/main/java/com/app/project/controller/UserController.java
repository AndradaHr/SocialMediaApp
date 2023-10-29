package com.app.project.controller;


import com.app.project.model.User;
import com.app.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(User user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public void login(User user) {
        userService.login(user);
    }

    @GetMapping("/getUserWithId:{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }


    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }
}
