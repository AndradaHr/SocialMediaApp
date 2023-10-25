package com.app.project.controller.interfaces;

import com.app.project.model.CR;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public interface CRController {
    @PostMapping
    void register(CR user);

    @PostMapping
    void login(CR user);

    @GetMapping
    CR getUser(Long id);

    @PutMapping
    void updateUser(CR user);

    @DeleteMapping
    void deleteUser(CR user);
}
