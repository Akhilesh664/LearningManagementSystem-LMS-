package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementsResponseDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;

    private Long courseId;
    private String courseTitle;     // Optional: for frontend display

    private Long postedById;
    private String postedByName;    // Optional: for display

}
