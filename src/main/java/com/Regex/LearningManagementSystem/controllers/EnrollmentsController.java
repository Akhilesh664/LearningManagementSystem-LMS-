package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.AdminUpdateEnrollmentRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.EnrollmentStatus;
import com.Regex.LearningManagementSystem.services.EnrollmentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentsController {

    private final EnrollmentsService enrollmentsService;

    @PostMapping
    public ResponseEntity<EnrollmentsResponseDto> createEnrollment(@RequestBody EnrollmentsRequestDto enrollmentsRequestDto) {
        log.info("Creating enrollment for studentId: {} in courseId: {}", enrollmentsRequestDto.getStudentId(), enrollmentsRequestDto.getCourseId());
        EnrollmentsResponseDto response = enrollmentsService.createEnrollment(enrollmentsRequestDto);
        log.info("Enrollment created successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentsResponseDto> getEnrollmentById(@PathVariable Long id) {
        log.info("Fetching enrollment with ID: {}", id);
        EnrollmentsResponseDto response = enrollmentsService.getEnrollmentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentsResponseDto>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        log.info("Fetching enrollments for studentId: {}", studentId);
        List<EnrollmentsResponseDto> enrollments = enrollmentsService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentsResponseDto>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        log.info("Fetching enrollments for courseId: {}", courseId);
        List<EnrollmentsResponseDto> enrollments = enrollmentsService.getEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}/admin-update")
    public ResponseEntity<EnrollmentsResponseDto> adminUpdateEnrollment(@PathVariable Long id,
                                                                        @RequestBody AdminUpdateEnrollmentRequestDto updateDto) {
        log.info("Admin updating enrollment with ID: {} (Status: {}, BatchId: {})", id, updateDto.getStatus(), updateDto.getBatchId());
        EnrollmentsResponseDto response = enrollmentsService.adminUpdateEnrollment(id, updateDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentsResponseDto>> getAllEnrollments() {
        log.info("Fetching all enrollments");
        List<EnrollmentsResponseDto> enrollments = enrollmentsService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EnrollmentsResponseDto>> getEnrollmentsByStatus(@PathVariable EnrollmentStatus status) {
        log.info("Fetching enrollments with status: {}", status);
        List<EnrollmentsResponseDto> enrollments = enrollmentsService.getEnrollmentsByStatus(status);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<EnrollmentsResponseDto>> getEnrollmentsByBatchId(@PathVariable Long batchId) {
        log.info("Fetching enrollments for batchId: {}", batchId);
        List<EnrollmentsResponseDto> enrollments = enrollmentsService.getEnrollmentsByBatchId(batchId);
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        log.info("Deleting enrollment with ID: {}", id);
        enrollmentsService.deleteEnrollment(id);
        log.info("Enrollment deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        log.info("Checking if enrollment exists with ID: {}", id);
        boolean exists = enrollmentsService.existsById(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/student/{studentId}/course/{courseId}")
    public ResponseEntity<Boolean> existsByStudentAndCourse(@PathVariable Long studentId,
                                                            @PathVariable Long courseId) {
        log.info("Checking if studentId {} is enrolled in courseId {}", studentId, courseId);
        boolean exists = enrollmentsService.existsByStudentAndCourse(studentId, courseId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count/course/{courseId}")
    public ResponseEntity<Integer> countEnrollmentsByCourseId(@PathVariable Long courseId) {
        log.info("Counting enrollments for courseId: {}", courseId);
        int count = enrollmentsService.countEnrollmentsByCourseId(courseId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/student/{studentId}")
    public ResponseEntity<Integer> countEnrollmentsByStudentId(@PathVariable Long studentId) {
        log.info("Counting enrollments for studentId: {}", studentId);
        int count = enrollmentsService.countEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/batch/{batchId}")
    public ResponseEntity<Integer> countEnrollmentsByBatchId(@PathVariable Long batchId) {
        log.info("Counting enrollments for batchId: {}", batchId);
        int count = enrollmentsService.countEnrollmentsByBatchId(batchId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Integer> countEnrollmentsByStatus(@PathVariable EnrollmentStatus status) {
        log.info("Counting enrollments with status: {}", status);
        int count = enrollmentsService.countEnrollmentsByStatus(status);
        return ResponseEntity.ok(count);
    }
}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.