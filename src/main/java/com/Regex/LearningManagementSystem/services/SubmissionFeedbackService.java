package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackResponseDto;

import java.util.List;

public interface SubmissionFeedbackService {

    SubmissionFeedbackResponseDto createFeedback(SubmissionFeedbackRequestDto requestDto);
    SubmissionFeedbackResponseDto getFeedbackById(Long feedbackId);
    List<SubmissionFeedbackResponseDto> getFeedbackBySubmissionId(Long submissionId);
    List<SubmissionFeedbackResponseDto> getFeedbackByTeacherId(Long teacherId);
    boolean hasFeedbackForSubmission(Long submissionId);
    void deleteFeedback(Long feedbackId);

}
