package com.app.project.controller;


import com.app.project.model.User;
import com.app.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping(value="/getUserWithId:{id}")
    public Mono<User> getUser(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping(value="/allUsers" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User>getAllUsers(){
        return userService.getAllUsers();
    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }
}
