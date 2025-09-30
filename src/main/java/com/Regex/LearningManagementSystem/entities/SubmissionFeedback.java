package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "submission_feedback")
@Data
public class SubmissionFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int marks; // Or use double if you want decimal grading

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submissions submission;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Users teacher;

}
