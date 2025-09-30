package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackResponseDto;
import com.Regex.LearningManagementSystem.services.SubmissionFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class SubmissionFeedbackController {

    private final SubmissionFeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<SubmissionFeedbackResponseDto> createFeedback(@RequestBody SubmissionFeedbackRequestDto requestDto) {
        log.info("Creating feedback for submissionId: {}, teacherId: {}", requestDto.getSubmissionId(), requestDto.getTeacherId());
        SubmissionFeedbackResponseDto response = feedbackService.createFeedback(requestDto);
        log.info("Feedback created successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<SubmissionFeedbackResponseDto> getFeedbackById(@PathVariable Long feedbackId) {
        log.info("Fetching feedback with ID: {}", feedbackId);
        SubmissionFeedbackResponseDto response = feedbackService.getFeedbackById(feedbackId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<SubmissionFeedbackResponseDto>> getFeedbackBySubmissionId(@PathVariable Long submissionId) {
        log.info("Fetching feedback for submissionId: {}", submissionId);
        List<SubmissionFeedbackResponseDto> response = feedbackService.getFeedbackBySubmissionId(submissionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<SubmissionFeedbackResponseDto>> getFeedbackByTeacherId(@PathVariable Long teacherId) {
        log.info("Fetching feedback given by teacherId: {}", teacherId);
        List<SubmissionFeedbackResponseDto> response = feedbackService.getFeedbackByTeacherId(teacherId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exists/{submissionId}")
    public ResponseEntity<Boolean> hasFeedbackForSubmission(@PathVariable Long submissionId) {
        log.info("Checking if feedback exists for submissionId: {}", submissionId);
        boolean exists = feedbackService.hasFeedbackForSubmission(submissionId);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        log.info("Deleting feedback with ID: {}", feedbackId);
        feedbackService.deleteFeedback(feedbackId);
        log.info("Feedback deleted successfully with ID: {}", feedbackId);
        return ResponseEntity.noContent().build();
    }
}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK. [put / get]
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created. [created]
// ResponseEntity.noContent().build(); for 204 No Content. [delete]