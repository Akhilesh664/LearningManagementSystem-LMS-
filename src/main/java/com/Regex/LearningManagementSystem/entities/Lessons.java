package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoUrl;
    private String content;
    private int orderNo;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Modules module;

    // Getters and Setters
}
