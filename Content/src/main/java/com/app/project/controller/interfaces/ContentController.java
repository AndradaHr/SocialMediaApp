package com.app.project.controller.interfaces;

import com.app.project.model.Content;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface ContentController {
    @PostMapping
    void register(Content user);

    @PostMapping
    void login(Content user);

    @GetMapping
    Content getUser(Long id);

    @PutMapping
    void updateUser(Content user);

    @DeleteMapping
    void deleteUser(Content user);
}
