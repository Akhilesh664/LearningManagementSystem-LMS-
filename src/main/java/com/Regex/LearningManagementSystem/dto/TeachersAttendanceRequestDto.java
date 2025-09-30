package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachersAttendanceRequestDto {

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Status is required")
    private AttendanceStatus status;

    @NotNull(message = "Teacher ID is required")
    private Long teacherId;
}
