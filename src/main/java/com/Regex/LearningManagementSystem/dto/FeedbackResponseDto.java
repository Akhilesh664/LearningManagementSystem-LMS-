package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDto {

    private Long id;
    private int rating;
    private String comment;

    private Long studentId;
    private String studentName; // Optional for UI display

    private Long courseId;
    private String courseTitle; // Optional for UI display

}
