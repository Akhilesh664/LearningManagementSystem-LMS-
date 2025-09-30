package com.Regex.LearningManagementSystem.dto;


import com.Regex.LearningManagementSystem.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role; // Or String, if you want "ADMIN", "TEACHER", etc.

    private String phoneNo;
    private String qualification;
    private boolean isActive;

    private Long batchId;   // Only the ID, not the full Batch object
    private String batchName; // Optional: if you want to expose it

}
