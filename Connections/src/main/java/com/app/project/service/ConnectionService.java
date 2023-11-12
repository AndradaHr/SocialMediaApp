package com.app.project.service;


import com.app.project.controller.SuggestedFriendsResponse;
import com.app.project.model.Connection;
import com.app.project.model.User;
import com.app.project.repository.ConnectionRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private WebClient webClient;

    public void addUser(Long userId, List<Long> followingIds) {
        Connection user = new Connection();
        user.setUserId(userId);
        user.setFollowing(followingIds);
        connectionRepository.save(user);
    }

    public List<Long> getFollowingByUserId(Long userId) {
        return connectionRepository.findByUserId(userId)
                .map(Connection::getFollowing)
                .orElse(Collections.emptyList());
    }

    public void addFollowing(Long userId, Long followingId) {
        Connection connection = connectionRepository.findByUserId(userId).orElse(null);
        if (connection != null && !connection.getFollowing().contains(followingId)) {
            connection.getFollowing().add(followingId);
            connectionRepository.delete(connection);
            connectionRepository.save(connection);
        }
    }

    public void removeFollowing(Long userId, Long followingId) {
        Connection connection = connectionRepository.findByUserId(userId).orElse(null);
        if (connection != null && connection.getFollowing().contains(followingId)) {
            connection.getFollowing().remove(followingId);
            connectionRepository.delete(connection);
            connectionRepository.save(connection);
        }
    }

    public Flux<SuggestedFriendsResponse> getSuggestedFriends(Long userId) {
        List<Long> userFriends = getFollowingByUserId(userId);
        Set<Long> userFriendsSet = new HashSet<>(userFriends);

        Set<Long> suggestedFriendIds = userFriends.stream()
                .flatMap(friendId -> getFollowingByUserId(friendId).stream())
                .distinct()
                .filter(friendOfFriendId -> !userFriendsSet.contains(friendOfFriendId) && !userId.equals(friendOfFriendId))
                .collect(Collectors.toSet());

        return Flux.fromIterable(suggestedFriendIds)
                .map(friendOfFriendId -> {
                    Long commonFriendId = userFriends.stream()
                            .filter(userFriendId -> getFollowingByUserId(userId).contains(userFriendId))
                            .findFirst()
                            .orElse(null);

                    User suggestedFriend = getUser(friendOfFriendId).block();

                    User commonFriend = commonFriendId != null ? getUser(commonFriendId).block() : null;

                    return mapToSuggestedFriendsResponse(suggestedFriend, commonFriend);
                })
                .filter(Objects::nonNull);
    }

    private SuggestedFriendsResponse mapToSuggestedFriendsResponse(User suggestedFriend, User commonFriend) {
        return SuggestedFriendsResponse.builder()
                .userId(suggestedFriend.getUserId())
                .bio(suggestedFriend.getBio())
                .email(suggestedFriend.getEmail())
                .lastName(suggestedFriend.getLastName())
                .firstName(suggestedFriend.getFirstName())
                .birthdate(suggestedFriend.getBirthdate())
                .status(suggestedFriend.getStatus())
                .password(suggestedFriend.getPassword())
                .language(suggestedFriend.getLanguage())
                .phoneNumber(suggestedFriend.getPhoneNumber())
                .username(suggestedFriend.getUsername())
                .profilePicture(suggestedFriend.getProfilePicture())
                .isPrivate(suggestedFriend.getIsPrivate())
                .commonFriend(commonFriend)
                .build();
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
}
