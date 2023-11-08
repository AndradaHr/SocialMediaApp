package com.app.project.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
@Entity
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private Boolean status;
    private String firstName;
    private String lastName;
    private String bio;

    @NonNull
    private LocalDate birthdate;

    @Column(unique = true)
    private String phoneNumber;

    @NonNull
    private String language;
    @NonNull
    private Boolean isPrivate;

    private String profilePicture;
    @ManyToMany
    private Set<User> friends = new HashSet<>();
}