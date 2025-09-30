package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.FeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.FeedbackResponseDto;

import java.util.List;

public interface FeedbackService {

    FeedbackResponseDto createFeedback(FeedbackRequestDto feedbackRequestDto);
    FeedbackResponseDto getFeedbackById(Long feedbackId);
    List<FeedbackResponseDto> getAllFeedback();
    List<FeedbackResponseDto> getFeedbackByCourseId(Long courseId);
    List<FeedbackResponseDto> getFeedbackByStudentId(Long studentId);
    List<FeedbackResponseDto> getRecentFeedback(int limit);
    FeedbackResponseDto updateFeedback(Long feedbackId, FeedbackRequestDto feedbackRequestDto);
    void deleteFeedback(Long feedbackId);

    boolean existsById(Long feedbackId);
    double getAverageRatingByCourseId(Long courseId);
    List<FeedbackResponseDto> getFeedbackByRatingRange(int minRating, int maxRating);
    boolean hasStudentGivenFeedback(Long studentId, Long courseId);

}
