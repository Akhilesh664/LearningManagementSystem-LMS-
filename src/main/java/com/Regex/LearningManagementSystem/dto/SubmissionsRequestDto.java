package com.Regex.LearningManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionsRequestDto {

    private String fileUrl;
    private Long assignmentId;
    private Long studentId;     // Optional (if needed)

}
