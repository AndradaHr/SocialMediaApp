package com.app.project.controller;


import com.app.project.authentication.JsonLoginResponse;
import com.app.project.model.User;
import com.app.project.service.UserService;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.app.project.authentication.LoginController.createJWT;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<JsonLoginResponse>> saveUser(@RequestBody @NonNull final User request) {
        var user= userService.saveUser(request);
        String token = String.valueOf(createJWT(user.getUserId().toString(), user.getEmail(), 999999999));
        return Mono.just(ResponseEntity.ok(new JsonLoginResponse(token,user)));
    }


    @GetMapping(value = "/getUserWithId:{id}")
    public Mono<User> getUser(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping(value = "/allUsers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}/change-password")
    public void changePassword(
            @PathVariable Long userId){

        userService.changePassword(userId);
    }
    @PostMapping("/{userId}/change-password")
    public void changePassword(
            @PathVariable Long userId, @RequestBody PasswordChangeRequest request){

        userService.changePasswordChange(userId, request.getOldPassword(), request.getNewPassword());
    }

    @GetMapping("/forgot-password/{userId}")
    public void forgotPassword(
            @PathVariable Long userId, @RequestBody PasswordForgottenRequest request){
        userService.forgotPassword(userId, request.getEmail());
    }
    @PostMapping("/forgot-password/{userId}")
    public void forgotPasswordChange(
            @PathVariable Long userId, @RequestBody PasswordChangeRequest request){
        userService.forgotPasswordChange(userId, request.getNewPassword());
    }




    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }
}