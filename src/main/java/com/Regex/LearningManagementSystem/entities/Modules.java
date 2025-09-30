package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "modules")
@Data
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    private int orderNo;

    @OneToMany(mappedBy = "module")
    private List<Lessons> lessons;

    // Getters and Setters

}
