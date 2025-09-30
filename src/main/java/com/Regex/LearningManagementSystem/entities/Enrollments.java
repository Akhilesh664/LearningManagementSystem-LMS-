package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime enrolledAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Users student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    // Getters and Setters
}
