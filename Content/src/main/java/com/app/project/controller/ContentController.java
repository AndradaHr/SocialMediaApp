package com.app.project.controller;

import com.app.project.model.Content;
import com.app.project.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/content")
@CrossOrigin
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/getFeedPosts/{userId}")
    public Flux<ContentResponse> getFeedPosts(@PathVariable Long userId) {
        return contentService.getPosts(userId);
    }

    @GetMapping("/getSuggestedPosts/{userId}")
    public Flux<ContentResponse> getSuggestedPosts(@PathVariable Long userId) {
        return contentService.getSuggestedPosts(userId);
    }


}

