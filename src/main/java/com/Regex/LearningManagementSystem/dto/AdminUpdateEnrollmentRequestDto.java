package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateEnrollmentRequestDto {

    private EnrollmentStatus status; // APPROVED, REJECTED, etc.
    private Long batchId;            // Optional

}
