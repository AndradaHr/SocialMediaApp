package com.app.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime timestamp;
    private Boolean isRead;
}
