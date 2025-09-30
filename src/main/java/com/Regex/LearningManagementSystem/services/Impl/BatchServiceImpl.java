package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.BatchRequestDto;
import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.*;
import com.Regex.LearningManagementSystem.exceptions.ResourceAlreadyExistsException;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.BatchMapper;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;
    private final CoursesRepository coursesRepository;
    private final UsersRepository usersRepository;


    @Override
    public BatchResponseDto createBatch(BatchRequestDto batchRequestDto) {
        log.info("Creating new batch with name: {}",batchRequestDto.getName());
        try {
            Batch batch = batchMapper.toEntity(batchRequestDto);
            Courses course = coursesRepository.findById(batchRequestDto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + batchRequestDto.getCourseId()));
            batch.setCourses(course);

            Users teacher = usersRepository.findById(batchRequestDto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + batchRequestDto.getTeacherId()));
            batch.setTeacher(teacher);

            Batch savedBatch = batchRepository.save(batch);
            log.info("Batch created successfully with ID: {}", savedBatch.getId());
            return batchMapper.toDto(savedBatch);
        } catch (Exception ex) {
            log.error("Error creating batch: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public BatchResponseDto getBatchById(Long batchId) {
        log.debug("Fetching batch with ID: {}", batchId);
        try {
            Batch batch = batchRepository.findById(batchId).orElseThrow(() -> new RuntimeException("Batch not found with ID: " + batchId));
            return batchMapper.toDto(batch);
        } catch (Exception ex) {
            log.error("Error fetching batch with ID {}: {}", batchId, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<BatchResponseDto> getAllBatches() {
        log.info("Fetching all batches");
        try {
            return batchRepository.findAll().stream()
                    .map(batchMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching all batches: {}", ex.getMessage(),ex);
            throw ex;
        }
    }

    @Override
    public BatchResponseDto updateBatch(Long batchId, BatchRequestDto batchRequestDto) {
        log.info("Updating batch with Id: {}", batchId);
        try {
            Batch batch = batchRepository.findById(batchId)
                    .orElseThrow(() -> new RuntimeException("Batch not found with ID: " + batchId));
            batch.setName(batchRequestDto.getName());

            Courses course = coursesRepository.findById(batchRequestDto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + batchRequestDto.getCourseId()));
            batch.setCourses(course);

            Users teacher = usersRepository.findById(batchRequestDto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + batchRequestDto.getTeacherId()));
            batch.setTeacher(teacher);


            batch.setStatus(batchRequestDto.getStatus());
            batch.setGoogleMeetLink(batchRequestDto.getGoogleMeetLink());

            Batch updatedBatch = batchRepository.save(batch);
            log.info("Batch updated successfully with ID: {}", updatedBatch.getId());
            return batchMapper.toDto(updatedBatch);
        } catch (Exception ex){
            log.error("Error updating batch with ID {}: {}", batchId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public void deleteBatch(Long batchId) {
        log.warn("Deleting batch with ID: {}", batchId);
        try {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + batchId));
        batchRepository.delete(batch);
            log.info("Batch deleted successfully with ID: {}", batchId);
        } catch (Exception ex) {
            log.error("Error deleting batch with ID {}: {}", batchId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public List<BatchResponseDto> getBatchesByStatus(String status) {
        log.info("Fetching batches with status: {}", status);
        try {
        return batchRepository.findByStatus(status)
                .stream()
                .map(batchMapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching batches by status {}: {}", status, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public List<BatchResponseDto> getBatchesByCourseId(Long courseId) {
        log.info("Fetching batches for course ID: {}", courseId);
        try {
        return batchRepository.findByCourseId(courseId)
                .stream()
                .map(batchMapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching batches by course ID {}: {}", courseId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public List<BatchResponseDto> getBatchesByTeacherId(Long teacherId) {
        log.info("Fetching batches for teacher ID: {}", teacherId);
        try {
        return batchRepository.findByTeacherId(teacherId)
                .stream()
                .map(batchMapper::toDto)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching batches by teacher ID {}: {}", teacherId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    @Transactional
    public void addStudentToBatch(Long batchId, Long studentId) {
        log.info("Adding student ID: {} to batch ID: {}", studentId, batchId);
        try {
            Batch batch = batchRepository.findById(batchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));

            Users student = usersRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + studentId));

            if (student.getBatch() != null && student.getBatch().getId().equals(batchId)) {
                log.warn("Student {} is already in batch {}", studentId, batchId);
                throw new ResourceAlreadyExistsException("Student is already in this batch");
            }
            student.setBatch(batch);
            usersRepository.save(student);
            log.info("Student {} added to batch {}", studentId, batchId);

        } catch (Exception ex) {
            log.error("Error adding student ID {} to batch ID {}: {}", studentId, batchId, ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void removeStudentFromBatch(Long batchId, Long studentId) {
        log.info("Removing student {} from batch {}", studentId, batchId);
        try {
        Users student = usersRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + studentId));

        if (student.getBatch() == null || !student.getBatch().getId().equals(batchId)) {
            log.warn("Student {} is not in batch {}", studentId, batchId);
            throw new IllegalStateException("Student is not in this batch");
        }
        student.setBatch(null);
        usersRepository.save(student);
        log.info("Student {} removed from batch {}", studentId, batchId);

        } catch (Exception ex) {
            log.error("Error removing student ID {} from batch ID {}: {}", studentId, batchId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public List<UsersResponseDto> getStudentsInBatch(Long batchId) {
        log.info("Fetching students in batch {}", batchId);
        try {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));

        return batch.getStudents().stream()
                .map(student -> {
                    UsersResponseDto dto = new UsersResponseDto();
                    dto.setId(student.getId());
                    dto.setName(student.getName());
                    dto.setEmail(student.getEmail());
                    dto.setRole(student.getRole());
                    dto.setPhoneNo(student.getPhoneNo());
                    dto.setQualification(student.getQualification());
                    dto.setActive(student.isActive());
                    if (student.getBatch() != null) {
                        dto.setBatchId(student.getBatch().getId());
                        dto.setBatchName(student.getBatch().getName());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching students in batch {}: {}", batchId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public BatchResponseDto updateBatchStatus(Long batchId, BatchStatus status) {
        log.info("Updating status of batch {} to {}", batchId, status);
        try {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
        batch.setStatus(status);
        Batch savedBatch = batchRepository.save(batch);
        log.info("Batch status updated successfully for ID: {}", savedBatch.getId());
        return batchMapper.toDto(savedBatch);
        } catch (Exception ex) {
            log.error("Error updating batch status for ID {}: {}", batchId, ex.getMessage(), ex);
            throw ex;
        }
    }


    @Override
    public List<BatchResponseDto> getActiveBatches() {
        log.info("Fetching active batches");
        try {
            List<Batch> batches = batchRepository.findByStatus(String.valueOf(BatchStatus.ACTIVE));
            return batches.stream()
                    .map(batchMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception ex){
            log.error("Error fetching active batches: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

}
