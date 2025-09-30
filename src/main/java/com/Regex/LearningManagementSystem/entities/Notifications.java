package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // Getters and Setters
}
