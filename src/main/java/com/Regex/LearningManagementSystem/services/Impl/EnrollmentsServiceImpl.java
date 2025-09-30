package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.AdminUpdateEnrollmentRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.*;
import com.Regex.LearningManagementSystem.exceptions.ResourceAlreadyExistsException;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.EnrollmentsMapper;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.EnrollmentsRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.EnrollmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentsServiceImpl implements EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final BatchRepository batchRepository;
    private final EnrollmentsMapper enrollmentsMapper;


    @Override
    public EnrollmentsResponseDto createEnrollment(EnrollmentsRequestDto enrollmentsRequestDto) {
        // Fetch student and course, throw exception if not found
        Users student = usersRepository.findById(enrollmentsRequestDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollmentsRequestDto.getStudentId()));
        
        Courses course = coursesRepository.findById(enrollmentsRequestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentsRequestDto.getCourseId()));

        // Check if enrollment already exists for this student and course
        if (enrollmentsRepository.existsByStudentIdAndCoursesId(
                enrollmentsRequestDto.getStudentId(), 
                enrollmentsRequestDto.getCourseId())) {
            throw new ResourceAlreadyExistsException("Student is already enrolled in this course");
        }

        // Create new enrollment
        Enrollments enrollment = new Enrollments();
        enrollment.setStudent(student);
        enrollment.setCourses(course);
        enrollment.setStatus(EnrollmentStatus.PENDING); // Default status for new enrollments
        // enrolledAt is automatically set by @CreationTimestamp
        
        // Save the enrollment
        Enrollments savedEnrollment = enrollmentsRepository.save(enrollment);
        
        // Convert to DTO and return
        return enrollmentsMapper.toDto(savedEnrollment);
    }


    @Override
    public EnrollmentsResponseDto getEnrollmentById(Long enrollmentId) {
        Enrollments enrollment = enrollmentsRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        return enrollmentsMapper.toDto(enrollment);
    }


    @Override
    public List<EnrollmentsResponseDto> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentsRepository.findByStudentId(studentId).stream()
                .map(enrollmentsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<EnrollmentsResponseDto> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentsRepository.findByCoursesId(courseId).stream()
                .map(enrollmentsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public EnrollmentsResponseDto adminUpdateEnrollment(Long enrollmentId, AdminUpdateEnrollmentRequestDto updateDto) {
        Enrollments enrollment = enrollmentsRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        if(updateDto.getStatus() != null){
            enrollment.setStatus(updateDto.getStatus());
        }
        if(updateDto.getBatchId() != null){
            Batch batch = batchRepository.findById(updateDto.getBatchId())
                            .orElseThrow(()-> new ResourceNotFoundException("Batch not found with id: " + updateDto.getBatchId()));
            enrollment.setBatch(batch);
        }
        return enrollmentsMapper.toDto(enrollmentsRepository.save(enrollment));
    }


    @Override
    public List<EnrollmentsResponseDto> getAllEnrollments() {
        return enrollmentsRepository.findAll().stream()
                .map(enrollmentsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<EnrollmentsResponseDto> getEnrollmentsByStatus(EnrollmentStatus status) {
        return enrollmentsRepository.findByStatus(status).stream()
                .map(enrollmentsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<EnrollmentsResponseDto> getEnrollmentsByBatchId(Long batchId) {
        return enrollmentsRepository.findByBatchId(batchId).stream()
                .map(enrollmentsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteEnrollment(Long enrollmentId) {
        Enrollments enrollment = enrollmentsRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
        enrollmentsRepository.delete(enrollment);
    }


    @Override
    public boolean existsById(Long enrollmentId) {
        return enrollmentsRepository.existsById(enrollmentId);
    }


    @Override
    public boolean existsByStudentAndCourse(Long studentId, Long courseId) {
        return enrollmentsRepository.existsByStudentIdAndCoursesId(studentId, courseId);
    }


    @Override
    public int countEnrollmentsByCourseId(Long courseId) {
        return enrollmentsRepository.countByCoursesId(courseId);
    }


    @Override
    public int countEnrollmentsByStudentId(Long studentId) {
        return enrollmentsRepository.countByStudentId(studentId);
    }


    @Override
    public int countEnrollmentsByBatchId(Long batchId) {
        return enrollmentsRepository.countByBatchId(batchId);
    }


    @Override
    public int countEnrollmentsByStatus(EnrollmentStatus status) {
        return enrollmentsRepository.countByStatus(status);
    }

}
