package com.app.project.service;

import com.app.project.controller.ContentResponse;
import com.app.project.model.Content;
import com.app.project.model.User;
import com.app.project.repository.ContentRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ContentService {
    @Autowired
    private WebClient webClient;

    @Autowired
    private ContentRepository contentRepository;

    public Flux<ContentResponse> getSuggestedPosts(@NonNull Long userId) {
        return Flux.merge(getUserFriends(userId).map(this::getPosts));
    }

    public Flux<ContentResponse> getPosts(Long userId) {
        return getUserFriends(userId)
                .flatMap(friendId ->
                        getUser(friendId)
                                .zipWith(Mono.just(contentRepository.findByUserId(friendId)))
                                .map(tuple -> {
                                    User user = tuple.getT1();
                                    Content content = (Content) tuple.getT2();
                                    return mapToContentResponse(content, user);
                                }));
    }

    public Flux<ContentResponse> getRandomPosts() {
        return generateRandomIds()
                .flatMap(friendId ->
                        getUser(friendId)
                                .zipWith(Mono.just(contentRepository.findByUserId(friendId)))
                                .map(tuple -> {
                                    User user = tuple.getT1();
                                    Content content = (Content) tuple.getT2();
                                    return mapToContentResponse(content, user);
                                }));
    }

    private ContentResponse mapToContentResponse(Content content, User userEntity) {
        return ContentResponse.builder()
                .contentId(content.getContentId())
                .user(userEntity)
                .photo(content.getPhoto())
                .datePosted(content.getDatePosted())
                .description(content.getDescription())
                .build();
    }


    private Flux<Long> getUserFriends(@NonNull Long userId) {
        var webRequest = webClient
                .get()
                .uri("http://localhost:8085/api/connections/" + userId + "/following")
                .accept(MediaType.APPLICATION_JSON);

        return webRequest.exchangeToFlux(response -> {
            if (response.statusCode() == HttpStatus.OK) {
                return response.bodyToFlux(Long.class);
            } else {
                return response.bodyToFlux(String.class)
                        .flatMap(body -> Flux.error(new RuntimeException("Request failed:" + body)));
            }
        });
    }

    private Mono<User> getUser(@NonNull Long userId) {
        var webRequest = webClient
                .get()
                .uri("http://localhost:8080/api/user/getSession/" + userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwiaWF0IjoxNjk5NDY1MDY1LCJzdWIiOiJocnViYW5hbmRyYWRhQGdtYWlsLmNvbSIsImlzcyI6InBvcGEmcm9iZXJ0IiwiZXhwIjoxNzAwNDY1MDY1fQ.yIRaCz1opmBzRtCTVjOBpb4bNf2O5h_BZikh4ArLgj4")
                .accept(MediaType.APPLICATION_JSON);

        return webRequest.exchangeToMono(response -> {
            if (response.statusCode() == HttpStatus.OK) {
                return response.bodyToMono(User.class);
            } else {
                return response.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Request failed:" + body)));
            }
        });
    }

    private Flux<Long> generateRandomIds() {
        Random random = new Random();
        List<Long> randomUserId = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Long randomNumber = (long) random.nextInt(1, 5);
            randomUserId.add(randomNumber);
        }

        return (Flux<Long>) randomUserId;
    }
}


