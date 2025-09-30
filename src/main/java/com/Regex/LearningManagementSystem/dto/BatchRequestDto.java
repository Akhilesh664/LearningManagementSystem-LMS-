package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.BatchStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchRequestDto {

    private String name;

    private Long courseId;

    private Long teacherId;

    private BatchStatus status;

    private String googleMeetLink;

}
