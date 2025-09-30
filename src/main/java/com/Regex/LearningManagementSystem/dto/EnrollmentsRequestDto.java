package com.Regex.LearningManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO for student-initiated enrollment requests.
 * ⚠️ NOTE:
 * This DTO is intended only for **creating new enrollments**.
 * Do NOT use it for updating enrollment status or assigning batches.
 *
 * For admin updates (e.g., approving, rejecting, assigning batch),
 * use a separate DTO: {@link AdminUpdateEnrollmentRequestDto}.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentsRequestDto {
    private Long studentId;
    private Long courseId;

    // [ *Important* ] Make Separate AdminUpdateEnrollmentRequestDto for Status, Batch_Id

}

