package com.app.project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private Long sender;
    private Long receiver;
    private String content;
    private LocalDateTime timestamp;
    private Boolean isRead;
    private Boolean IsEdited;
}
