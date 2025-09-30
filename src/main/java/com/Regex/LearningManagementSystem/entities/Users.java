package com.Regex.LearningManagementSystem.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;  // Stored in DB as a String like "ADMIN"

    private String phoneNo;
    private String qualification;

    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    // Getters and Setters
}
