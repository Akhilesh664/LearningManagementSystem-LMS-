package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.FeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.FeedbackResponseDto;
import com.Regex.LearningManagementSystem.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        log.info("Creating feedback for courseId: {} by studentId: {}", feedbackRequestDto.getCourseId(), feedbackRequestDto.getStudentId());
        FeedbackResponseDto response = feedbackService.createFeedback(feedbackRequestDto);
        log.info("Feedback created successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> getFeedbackById(@PathVariable Long id) {
        log.info("Fetching feedback with ID: {}", id);
        FeedbackResponseDto response = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDto>> getAllFeedback() {
        log.info("Fetching all feedback records");
        List<FeedbackResponseDto> feedbackList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedbackByCourseId(@PathVariable Long courseId) {
        log.info("Fetching feedback for courseId: {}", courseId);
        List<FeedbackResponseDto> feedbackList = feedbackService.getFeedbackByCourseId(courseId);
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedbackByStudentId(@PathVariable Long studentId) {
        log.info("Fetching feedback for studentId: {}", studentId);
        List<FeedbackResponseDto> feedbackList = feedbackService.getFeedbackByStudentId(studentId);
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<FeedbackResponseDto>> getRecentFeedback(@RequestParam(defaultValue = "5") int limit) {
        log.info("Fetching {} most recent feedback entries", limit);
        List<FeedbackResponseDto> feedbackList = feedbackService.getRecentFeedback(limit);
        return ResponseEntity.ok(feedbackList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> updateFeedback(@PathVariable Long id,
                                                              @RequestBody FeedbackRequestDto feedbackRequestDto) {
        log.info("Updating feedback with ID: {}", id);
        FeedbackResponseDto response = feedbackService.updateFeedback(id, feedbackRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        log.info("Deleting feedback with ID: {}", id);
        feedbackService.deleteFeedback(id);
        log.info("Feedback deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        log.info("Checking if feedback exists with ID: {}", id);
        boolean exists = feedbackService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/course/{courseId}/average-rating")
    public ResponseEntity<Double> getAverageRatingByCourseId(@PathVariable Long courseId) {
        log.info("Calculating average rating for courseId: {}", courseId);
        double avgRating = feedbackService.getAverageRatingByCourseId(courseId);
        return ResponseEntity.ok(avgRating);
    }

    @GetMapping("/rating-range")
    public ResponseEntity<List<FeedbackResponseDto>> getFeedbackByRatingRange(@RequestParam int minRating,
                                                                              @RequestParam int maxRating) {
        log.info("Fetching feedback with rating between {} and {}", minRating, maxRating);
        List<FeedbackResponseDto> feedbackList = feedbackService.getFeedbackByRatingRange(minRating, maxRating);
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/student/{studentId}/course/{courseId}/exists")
    public ResponseEntity<Boolean> hasStudentGivenFeedback(@PathVariable Long studentId,
                                                           @PathVariable Long courseId) {
        log.info("Checking if studentId {} has given feedback for courseId {}", studentId, courseId);
        boolean hasGiven = feedbackService.hasStudentGivenFeedback(studentId, courseId);
        return ResponseEntity.ok(hasGiven);
    }
}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.