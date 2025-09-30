package com.Regex.LearningManagementSystem.dto;


import com.Regex.LearningManagementSystem.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequestDto {

    private String name;
    private String email;
    // Make Separate Dto for PasswordUpdateChange
    private Role role; // Or String, if you want "ADMIN", "TEACHER", etc.

    private String phoneNo;
    private String qualification;

    private Long batchId;   // Only the ID, not the full Batch object

}
