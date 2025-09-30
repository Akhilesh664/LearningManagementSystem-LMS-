package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private DayOfWeek dayOfWeek;
    private String imageUrl;
    private String fileType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
