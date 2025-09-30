package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.AdminUpdateEnrollmentRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.EnrollmentStatus;

import java.util.List;

public interface EnrollmentsService {

    // Regular user operations
    EnrollmentsResponseDto createEnrollment(EnrollmentsRequestDto enrollmentsRequestDto);
    EnrollmentsResponseDto getEnrollmentById(Long enrollmentId);
    List<EnrollmentsResponseDto> getEnrollmentsByStudentId(Long studentId);
    List<EnrollmentsResponseDto> getEnrollmentsByCourseId(Long courseId);

    // Admin operations
    EnrollmentsResponseDto adminUpdateEnrollment(Long enrollmentId, AdminUpdateEnrollmentRequestDto updateDto);
    List<EnrollmentsResponseDto> getAllEnrollments();
    List<EnrollmentsResponseDto> getEnrollmentsByStatus(EnrollmentStatus status);
    List<EnrollmentsResponseDto> getEnrollmentsByBatchId(Long batchId);
    void deleteEnrollment(Long enrollmentId);

    // Utility methods
    boolean existsById(Long enrollmentId);
    boolean existsByStudentAndCourse(Long studentId, Long courseId);
    int countEnrollmentsByCourseId(Long courseId);
    int countEnrollmentsByStudentId(Long studentId);
    int countEnrollmentsByBatchId(Long batchId);
    int countEnrollmentsByStatus(EnrollmentStatus status);

}
