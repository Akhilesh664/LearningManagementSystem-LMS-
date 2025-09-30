package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.BatchStatus;
import com.Regex.LearningManagementSystem.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchResponseDto {

    private Long id;
    private String name;
    private BatchStatus status;

    private Long courseId;
    private String courseTitle; // Optional, for display

    private Long teacherId;
    private String teacherName; // Optional, for display

    private int studentCount;   // Optional: number of enrolled students
    private List<Users> students;

    private String googleMeetLink;
}
