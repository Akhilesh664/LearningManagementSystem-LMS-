package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AssignmentsResponseDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;

    private Long courseId;
    private String courseTitle; // Optional: useful for display
}
