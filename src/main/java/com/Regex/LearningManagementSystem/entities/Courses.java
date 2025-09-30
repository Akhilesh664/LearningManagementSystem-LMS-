package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String Description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    private String googleClassroomId;

    @OneToMany(mappedBy = "course")
    private List<Modules> modules;

    // Getters and Setters

}
