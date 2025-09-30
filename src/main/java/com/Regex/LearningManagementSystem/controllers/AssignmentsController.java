package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.AssignmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.AssignmentsResponseDto;
import com.Regex.LearningManagementSystem.services.AssignmentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentsController {

    private final AssignmentsService assignmentsService;

    @PostMapping
    public ResponseEntity<AssignmentsResponseDto> createAssignment(@RequestBody AssignmentsRequestDto assignmentsRequestDto) {
        log.info("Creating a new assignment: {}", assignmentsRequestDto.getTitle());
        AssignmentsResponseDto createdAssignment = assignmentsService.createAssignment(assignmentsRequestDto);
        log.info("Assignment created successfully with ID: {}", createdAssignment.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentsResponseDto> getAssignmentById(@PathVariable Long assignmentId) {
        log.info("Fetching assignment with ID: {}", assignmentId);
        AssignmentsResponseDto assignment = assignmentsService.getAssignmentById(assignmentId);
        return ResponseEntity.ok(assignment);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentsResponseDto>> getAllAssignments() {
        log.info("Fetching all assignments");
        List<AssignmentsResponseDto> assignments = assignmentsService.getAllAssignments();
        log.info("Total assignments fetched: {}", assignments.size());
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssignmentsResponseDto>> getAssignmentsByCourseId(@PathVariable Long courseId) {
        log.info("Fetching assignments for course ID: {}", courseId);
        List<AssignmentsResponseDto> assignments = assignmentsService.getAssignmentsByCourseId(courseId);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/due-before")
    public ResponseEntity<List<AssignmentsResponseDto>> getAssignmentsDueBefore(@RequestParam LocalDate dueDate) {
        log.info("Fetching assignments due before: {}", dueDate);
        List<AssignmentsResponseDto> assignments = assignmentsService.getAssignmentsDueBefore(dueDate);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/due-after")
    public ResponseEntity<List<AssignmentsResponseDto>> getAssignmentsDueAfter(@RequestParam LocalDate dueDate) {
        log.info("Fetching assignments due after: {}", dueDate);
        List<AssignmentsResponseDto> assignments = assignmentsService.getAssignmentsDueAfter(dueDate);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/due-between")
    public ResponseEntity<List<AssignmentsResponseDto>> getAssignmentsDueBetween(@RequestParam LocalDate startDate,
                                                                                 @RequestParam LocalDate endDate) {
        log.info("Fetching assignments due between {} and {}", startDate, endDate);
        List<AssignmentsResponseDto> assignments = assignmentsService.getAssignmentsDueBetween(startDate, endDate);
        return ResponseEntity.ok(assignments);
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<AssignmentsResponseDto> updateAssignment(@PathVariable Long assignmentId,
                                                                   @RequestBody AssignmentsRequestDto assignmentsRequestDto) {
        log.info("Updating assignment with ID: {}", assignmentId);
        AssignmentsResponseDto updatedAssignment = assignmentsService.updateAssignment(assignmentId, assignmentsRequestDto);
        log.info("Assignment updated successfully with ID: {}", updatedAssignment.getId());
        return ResponseEntity.ok(updatedAssignment);
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long assignmentId) {
        log.info("Deleting assignment with ID: {}", assignmentId);
        assignmentsService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assignmentId}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long assignmentId) {
        log.info("Checking if assignment exists with ID: {}", assignmentId);
        boolean exists = assignmentsService.existsById(assignmentId);
        return ResponseEntity.ok(exists);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.
