package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionFeedbackResponseDto {

    private Long id;
    private String comment;
    private int marks;
    private LocalDateTime createdAt;

    private Long submissionId;
    private String assignmentTitle; // from submission.assignments.title

    private Long studentId;
    private String studentName;

    private Long teacherId;
    private String teacherName;

}
