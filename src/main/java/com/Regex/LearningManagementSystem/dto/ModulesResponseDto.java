package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesResponseDto {

    private Long id;
    private String title;
    private int orderNo;

    private Long courseId;
    private String courseTitle; // Optional

    private int lessonCount; // Optional: count of lessons
    private List<LessonsResponseDto> lessons;

}
