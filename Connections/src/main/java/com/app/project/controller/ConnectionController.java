package com.app.project.controller;

import com.app.project.model.Connection;
import com.app.project.model.User;
import com.app.project.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("api/connections")
@CrossOrigin
public class ConnectionController {
    @Autowired
    private ConnectionService userService;

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody Connection user) {
        userService.addUser(user.getUserId(), user.getFollowing());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/follow")
    public ResponseEntity<?> addFollowing(@PathVariable Long userId, @RequestBody Long followingId) {
        userService.addFollowing(userId, followingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Long>> getFollowing(@PathVariable Long userId) {
        List<Long> followingIds = userService.getFollowingByUserId(userId);
        return ResponseEntity.ok(followingIds);
    }

    @DeleteMapping("/{userId}/unfollow")
    public ResponseEntity<?> removeFollowing(@PathVariable Long userId, @RequestBody Long followingId) {
        userService.removeFollowing(userId, followingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getSuggestedFriends/{userId}")
    public Flux<SuggestedFriendsResponse> getSuggestedFriends(@PathVariable Long userId){
        return userService.getSuggestedFriends(userId);
    }
}
