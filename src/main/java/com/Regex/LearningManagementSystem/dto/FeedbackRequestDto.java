package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequestDto {

    private String comment;
    private int rating;
    private Long studentId;
    private Long courseId;

}
