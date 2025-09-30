package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "batches")
@Data
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Users teacher;

    @OneToMany(mappedBy = "batch")
    private List<Users> students;

    @Enumerated(EnumType.STRING)
    private BatchStatus status;

    private String googleMeetLink;

    // Getters and Setters

}
