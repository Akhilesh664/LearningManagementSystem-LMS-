package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.BatchRequestDto;
import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.BatchStatus;
import com.Regex.LearningManagementSystem.services.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponseDto> createBatch(@Valid @RequestBody BatchRequestDto batchRequestDto){
        log.info("Creating new batch with name: {}",batchRequestDto.getName());
        BatchResponseDto response = batchService.createBatch(batchRequestDto);
        log.debug("Batch created successfully: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<BatchResponseDto> getBatchById(@PathVariable Long batchId){
        log.info("Retrieving batch with ID: {}", batchId);
        BatchResponseDto response = batchService.getBatchById(batchId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BatchResponseDto>> getAllBatches() {
        log.info("Fetching all batches");
        List<BatchResponseDto> response = batchService.getAllBatches();
        log.debug("Total batches found: {}", response.size());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{batchId}")
    public ResponseEntity<BatchResponseDto> updateBatch(@PathVariable Long batchId,
                                                        @Valid @RequestBody BatchRequestDto batchRequestDto) {
        log.info("Updating batch with ID: {}", batchId);
        BatchResponseDto response = batchService.updateBatch(batchId, batchRequestDto);
        log.debug("Batch updated successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long batchId) {
        log.warn("Deleting batch with ID: {}", batchId);
        batchService.deleteBatch(batchId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BatchResponseDto>> getBatchesByStatus(@PathVariable String status) {
        log.info("Fetching batches with status: {}", status);
        List<BatchResponseDto> response = batchService.getBatchesByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<BatchResponseDto>> getBatchesByCourseId(@PathVariable Long courseId) {
        log.info("Fetching batches for course ID: {}", courseId);
        List<BatchResponseDto> response = batchService.getBatchesByCourseId(courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<BatchResponseDto>> getBatchesByTeacherId(@PathVariable Long teacherId) {
        log.info("Fetching batches for teacher ID: {}", teacherId);
        List<BatchResponseDto> response = batchService.getBatchesByTeacherId(teacherId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{batchId}/students/{studentId}")
    public ResponseEntity<Void> addStudentToBatch(@PathVariable Long batchId, @PathVariable Long studentId) {
        log.info("Adding student ID: {} to batch ID: {}", studentId, batchId);
        batchService.addStudentToBatch(batchId, studentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{batchId}/students/{studentId}")
    public ResponseEntity<Void> removeStudentFromBatch(@PathVariable Long batchId, @PathVariable Long studentId) {
        log.info("Removing student ID: {} from batch ID: {}", studentId, batchId);
        batchService.removeStudentFromBatch(batchId, studentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{batchId}/students")
    public ResponseEntity<List<UsersResponseDto>> getStudentsInBatch(@PathVariable Long batchId) {
        log.info("Fetching students for batch ID: {}", batchId);
        List<UsersResponseDto> response = batchService.getStudentsInBatch(batchId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{batchId}/status")
    public ResponseEntity<BatchResponseDto> updateBatchStatus(@PathVariable Long batchId,
                                                              @RequestParam BatchStatus status) {
        log.info("Updating status for batch ID: {} to {}", batchId, status);
        BatchResponseDto response = batchService.updateBatchStatus(batchId, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active")
    public ResponseEntity<List<BatchResponseDto>> getActiveBatches() {
        log.info("Fetching all active batches");
        List<BatchResponseDto> response = batchService.getActiveBatches();
        return ResponseEntity.ok(response);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.