package com.app.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private Boolean status;
    private String firstName;
    private String lastName;
    private String bio;
    @NonNull
    private LocalDate birthdate;
    private String phoneNumber;
    @NonNull
    private String language;
    @NonNull
    private Boolean isPrivate;
}
