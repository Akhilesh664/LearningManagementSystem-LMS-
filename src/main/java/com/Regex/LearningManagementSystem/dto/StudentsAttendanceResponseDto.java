package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsAttendanceResponseDto {
    private Long id;
    private LocalDate date;
    private AttendanceStatus status;

    private Long studentId;
    private String studentName;
    private String studentEmail;

    private Long courseId;
    private String courseTitle;
}
