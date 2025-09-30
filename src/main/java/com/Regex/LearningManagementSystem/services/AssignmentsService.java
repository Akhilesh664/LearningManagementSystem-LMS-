package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.AssignmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.AssignmentsResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentsService {

    AssignmentsResponseDto createAssignment(AssignmentsRequestDto assignmentsRequestDto);
    AssignmentsResponseDto getAssignmentById(Long assignmentId);
    List<AssignmentsResponseDto> getAllAssignments();
    List<AssignmentsResponseDto> getAssignmentsByCourseId(Long courseId);
    List<AssignmentsResponseDto> getAssignmentsDueBefore(LocalDate dueDate);

    List<AssignmentsResponseDto> getAssignmentsDueAfter(LocalDate dueDate);
    List<AssignmentsResponseDto> getAssignmentsDueBetween(LocalDate startDate, LocalDate endDate);
    AssignmentsResponseDto updateAssignment(Long assignmentId, AssignmentsRequestDto assignmentsRequestDto);
    void deleteAssignment(Long assignmentId);
    boolean existsById(Long assignmentId);

    // TODO: add later
//    List<AssignmentsResponseDto> getUpcomingDueAssignments();
//    List<AssignmentsResponseDto> getOverdueAssignments();

}
