package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonsResponseDto {

    private Long id;
    private String title;
    private String videoUrl;
    private String content;
    private int orderNo;

    private Long moduleId;
    private String moduleTitle; // Optional: for display

    private Long courseId;
    private String courseTitle;

}
