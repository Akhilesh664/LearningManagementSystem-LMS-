package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String fileType;  // e.g., "image/png", "image/jpeg"

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // Ensures we only have one schedule per day
    @PrePersist
    @PreUpdate
    private void validate() {
        if (dayOfWeek == null) {
            throw new IllegalStateException("Day of week cannot be null");
        }
    }


}
