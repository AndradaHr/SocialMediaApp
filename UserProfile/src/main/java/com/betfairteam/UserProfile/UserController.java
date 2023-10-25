package com.betfairteam.UserProfile;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface UserController {
    @PostMapping
    void register(User user);

    @PostMapping
    void login(User user);

    @GetMapping
    void getUser(Long id);

    @PutMapping
    void updateUser(User user);

    @DeleteMapping
    void deleteUser(User user);
}
