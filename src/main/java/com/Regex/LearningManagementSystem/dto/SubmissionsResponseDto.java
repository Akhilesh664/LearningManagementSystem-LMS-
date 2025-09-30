package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionsResponseDto {

    private Long id;
    private String fileUrl;
    private LocalDateTime submittedAt;

    private Long assignmentId;
    private String assignmentTitle; // Optional (if needed)

    private Long studentId;
    private String studentName;     // Optional (if needed)
}
