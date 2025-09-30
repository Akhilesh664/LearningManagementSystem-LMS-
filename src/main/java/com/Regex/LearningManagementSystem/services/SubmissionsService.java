package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.SubmissionsRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionsResponseDto;

import java.util.List;

public interface SubmissionsService {

    SubmissionsResponseDto createSubmission(SubmissionsRequestDto submissionsRequestDto);
    SubmissionsResponseDto getSubmissionById(Long submissionId);
    List<SubmissionsResponseDto> getAllSubmissions();
    List<SubmissionsResponseDto> getSubmissionsByAssignmentId(Long assignmentId);
    List<SubmissionsResponseDto> getSubmissionsByStudentId(Long studentId);
    List<SubmissionsResponseDto> getSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId);
    List<SubmissionsResponseDto> getRecentSubmissions(int limit);
    SubmissionsResponseDto updateSubmission(Long submissionId, SubmissionsRequestDto submissionsRequestDto);
    void deleteSubmission(Long submissionId);

    boolean existsById(Long submissionId);
    int countSubmissionsByAssignmentId(Long assignmentId);
    int countSubmissionsByStudentId(Long studentId);
    int countSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId);

}
