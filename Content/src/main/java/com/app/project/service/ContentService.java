package com.app.project.service;

import com.app.project.model.Content;
import com.app.project.model.User;
import com.app.project.model.Post;
import com.app.project.repository.ContentRepository;
import com.app.project.repository.PostRepository;
import com.app.project.serviceUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.app.project.util.Validators.passwordNumberValidator;
import static com.app.project.util.Validators.passwordSpecialCharacterValidator;

@Service
public class ContentService {
    @Autowired
    private ContentRepository userDAO;

    private final UserService userService;

    private final PostRepository postRepository;
    @Autowired
    public ContentService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    public void register(Content user) {
        if(!user.getEmail().contains("@")) {
            throw new RuntimeException("Invalid email");
        }
        if(user.getPassword().length() < 8) {
            throw new RuntimeException("Password too short");
        }
        if(!passwordSpecialCharacterValidator(user.getPassword())){
            throw new RuntimeException("Password should contain special characters");
        }
        if(!passwordNumberValidator(user.getPassword())){
            throw new RuntimeException("Password should contain numbers");
        }
        if(Period.between(user.getBirthdate(), LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("You should be at least 18 years old");
        }
        userDAO.save(user);
    }

    public void login(Content user) {
        Optional<Content> optionalUser = userDAO.findById(user.getUserId()).blockOptional();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }

    public Content getUser(Long id) {
        Optional<Content> optionalUser = userDAO.findById(id).blockOptional();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return optionalUser.get();
    }

    public void updateUser(Content user) {
        Optional<Content> optionalUser = userDAO.findById(user.getUserId()).blockOptional();
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
    }
    @Autowired
    private UserService friendService;

    // Method to get posts from friends
    public Flux<Post> getRecommendedPosts(Long userId) {
        return userService.getUser(userId)
                .flatMapMany(user ->
                        Flux.fromIterable(user.getFriends())
                                .flatMap(friend -> getPostsByUser(friend.getUserId()))
                );
    }


    public Flux<Post> getSuggestedPosts(Long userId) {
        User user = userService.getUser(userId).block();
        Set<User> friendsOfFriends = user.getFriends().stream()
                .flatMap(friend -> friend.getFriends().stream())
                .collect(Collectors.toSet());
        return Flux.fromIterable(friendsOfFriends)
                .flatMap(friendOfFriend -> getPostsByUser(friendOfFriend.getUserId()));
    }

    private Flux<Post> getPostsByUser(Long userId) {

        return postRepository.findByUserId(userId);
    }



}


