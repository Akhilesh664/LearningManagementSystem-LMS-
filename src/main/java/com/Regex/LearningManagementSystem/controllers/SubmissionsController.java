package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.SubmissionsRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionsResponseDto;
import com.Regex.LearningManagementSystem.services.SubmissionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionsController {

    private final SubmissionsService submissionsService;

    @PostMapping
    public ResponseEntity<SubmissionsResponseDto> createSubmission(@RequestBody SubmissionsRequestDto submissionsRequestDto) {
        log.info("Creating a new submission for assignment ID: {}", submissionsRequestDto.getAssignmentId());
        SubmissionsResponseDto createdSubmission = submissionsService.createSubmission(submissionsRequestDto);
        log.info("Submission created successfully with ID: {}", createdSubmission.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubmission);
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionsResponseDto> getSubmissionById(@PathVariable Long submissionId) {
        log.info("Fetching submission with ID: {}", submissionId);
        SubmissionsResponseDto submission = submissionsService.getSubmissionById(submissionId);
        return ResponseEntity.ok(submission);
    }

    @GetMapping
    public ResponseEntity<List<SubmissionsResponseDto>> getAllSubmissions() {
        log.info("Fetching all submissions");
        List<SubmissionsResponseDto> submissions = submissionsService.getAllSubmissions();
        log.info("Total submissions fetched: {}", submissions.size());
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionsResponseDto>> getSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        log.info("Fetching submissions for assignment ID: {}", assignmentId);
        List<SubmissionsResponseDto> submissions = submissionsService.getSubmissionsByAssignmentId(assignmentId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionsResponseDto>> getSubmissionsByStudentId(@PathVariable Long studentId) {
        log.info("Fetching submissions for student ID: {}", studentId);
        List<SubmissionsResponseDto> submissions = submissionsService.getSubmissionsByStudentId(studentId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/assignment/{assignmentId}/student/{studentId}")
    public ResponseEntity<List<SubmissionsResponseDto>> getSubmissionsByAssignmentAndStudent(@PathVariable Long assignmentId,
                                                                                             @PathVariable Long studentId) {
        log.info("Fetching submissions for assignment ID: {} and student ID: {}", assignmentId, studentId);
        List<SubmissionsResponseDto> submissions = submissionsService.getSubmissionsByAssignmentAndStudent(assignmentId, studentId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<SubmissionsResponseDto>> getRecentSubmissions(@RequestParam(defaultValue = "10") int limit) {
        log.info("Fetching recent {} submissions", limit);
        List<SubmissionsResponseDto> submissions = submissionsService.getRecentSubmissions(limit);
        return ResponseEntity.ok(submissions);
    }

    @PutMapping("/{submissionId}")
    public ResponseEntity<SubmissionsResponseDto> updateSubmission(@PathVariable Long submissionId,
                                                                   @RequestBody SubmissionsRequestDto submissionsRequestDto) {
        log.info("Updating submission with ID: {}", submissionId);
        SubmissionsResponseDto updatedSubmission = submissionsService.updateSubmission(submissionId, submissionsRequestDto);
        log.info("Submission updated successfully with ID: {}", updatedSubmission.getId());
        return ResponseEntity.ok(updatedSubmission);
    }

    @DeleteMapping("/{submissionId}")
    public ResponseEntity<String> deleteSubmission(@PathVariable Long submissionId) {
        log.info("Deleting submission with ID: {}", submissionId);
        submissionsService.deleteSubmission(submissionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{submissionId}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long submissionId) {
        log.info("Checking if submission exists with ID: {}", submissionId);
        boolean exists = submissionsService.existsById(submissionId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count/assignment/{assignmentId}")
    public ResponseEntity<Integer> countSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        log.info("Counting submissions for assignment ID: {}", assignmentId);
        int count = submissionsService.countSubmissionsByAssignmentId(assignmentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/student/{studentId}")
    public ResponseEntity<Integer> countSubmissionsByStudentId(@PathVariable Long studentId) {
        log.info("Counting submissions for student ID: {}", studentId);
        int count = submissionsService.countSubmissionsByStudentId(studentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/assignment/{assignmentId}/student/{studentId}")
    public ResponseEntity<Integer> countSubmissionsByAssignmentAndStudent(@PathVariable Long assignmentId,
                                                                          @PathVariable Long studentId) {
        log.info("Counting submissions for assignment ID: {} and student ID: {}", assignmentId, studentId);
        int count = submissionsService.countSubmissionsByAssignmentAndStudent(assignmentId, studentId);
        return ResponseEntity.ok(count);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.