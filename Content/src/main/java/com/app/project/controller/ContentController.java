package com.app.project.controller;

import com.app.project.model.Content;
import com.app.project.model.Post;
import com.app.project.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/user")
public class ContentController {
//    @Autowired
//    private ContentService userService;
//
//
//    @PostMapping("/register")
//    public void register(Content user) {
//        userService.register(user);
//    }
//
//    @PostMapping("/login")
//    public void login(Content user) {
//        userService.login(user);
//    }
//
//
//    public void updateUser(Content user) {
//
//    }
//
//    public void deleteUser(Content user) {
//
//    }
//    @GetMapping("/{userId}/recommended")
//    public Flux<Post> getRecommendedPosts(@PathVariable Long userId) {
//        return contentService.getRecommendedPosts(userId);
//    }
//
//    @GetMapping("/{userId}/suggested")
//    public Flux<Post> getSuggestedPosts(@PathVariable Long userId) {
//        return contentService.getSuggestedPosts(userId);
//    }
}

