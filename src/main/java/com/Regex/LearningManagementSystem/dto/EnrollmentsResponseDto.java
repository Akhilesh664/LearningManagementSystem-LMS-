package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentsResponseDto {
    private Long id;
    private EnrollmentStatus status;
    private LocalDateTime enrolledAt;

    private Long studentId;
    private String studentName; // Optional: for display
    private String studentEmail;

    private Long courseId;
    private String courseTitle; // Optional

    private Long batchId;
    private String batchName;   // Optional
}
