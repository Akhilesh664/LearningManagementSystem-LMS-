package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.BatchRequestDto;
import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.BatchStatus;

import java.util.List;

public interface BatchService {

    BatchResponseDto createBatch(BatchRequestDto batchRequestDto);
    BatchResponseDto getBatchById(Long batchId);
    List<BatchResponseDto> getAllBatches();
    BatchResponseDto updateBatch(Long batchId, BatchRequestDto batchRequestDto);
    void deleteBatch(Long batchId);

    List<BatchResponseDto> getBatchesByStatus(String status);
    List<BatchResponseDto> getBatchesByCourseId(Long courseId);
    List<BatchResponseDto> getBatchesByTeacherId(Long teacherId);

    void addStudentToBatch(Long batchId, Long studentId);
    void removeStudentFromBatch(Long batchId, Long studentId);
    List<UsersResponseDto> getStudentsInBatch(Long batchId);
    BatchResponseDto updateBatchStatus(Long batchId, BatchStatus status);
    List<BatchResponseDto> getActiveBatches();

}
