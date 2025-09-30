package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.DayOfWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotNull(message = "Day of week is required")
    private DayOfWeek dayOfWeek;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @NotBlank(message = "File type is required")
    private String fileType;

}
