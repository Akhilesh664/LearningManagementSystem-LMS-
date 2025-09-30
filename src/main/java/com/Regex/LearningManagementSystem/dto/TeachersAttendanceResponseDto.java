package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TeachersAttendanceResponseDto {
    private Long id;
    private LocalDate date;
    private AttendanceStatus status;

    private Long teacherId;
    private String teacherName;
    private String teacherEmail;
}
