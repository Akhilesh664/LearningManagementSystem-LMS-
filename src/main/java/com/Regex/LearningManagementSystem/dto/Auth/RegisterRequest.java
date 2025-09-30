package com.Regex.LearningManagementSystem.dto.Auth;

import com.Regex.LearningManagementSystem.entities.Role;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    private String password;

    @NotBlank
    private Role role; // Or use Role enum if preferred

    private String phoneNo;
    private String qualification;
    private Long batchId; // Optional, useful for student registration
}
