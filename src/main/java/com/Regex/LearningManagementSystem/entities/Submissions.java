package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@Data
public class Submissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignments assignments;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Users student;

    // Getters and Setters
}
