package com.app.project.controller;

import com.app.project.model.User;
import com.app.project.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/save")
    public Mono<User> saveUser(@RequestBody User user) {
        return searchService.saveUser(user);
    }

    @DeleteMapping("/delete/{userId}")
    public Mono<Void> deleteUser(@PathVariable Long userId) {
        return searchService.deleteUser(userId);
    }

    @GetMapping("/getUser/{userId}")
    public Mono<User> getUserById(@PathVariable Long userId) {
        return searchService.getUserById(userId);
    }

    @GetMapping("/findAll")
    public Flux<User> findAllUsers() {
        return searchService.findAllUsers();
    }
}
