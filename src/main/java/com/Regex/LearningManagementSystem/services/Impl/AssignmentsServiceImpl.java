package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.AssignmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.AssignmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.Assignments;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.AssignmentsMapper;
import com.Regex.LearningManagementSystem.repositories.AssignmentsRepository;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.services.AssignmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentsServiceImpl implements AssignmentsService {

    private final AssignmentsRepository assignmentsRepository;
    private final CoursesRepository coursesRepository;
    private final AssignmentsMapper assignmentsMapper;

    @Override
    public AssignmentsResponseDto createAssignment(AssignmentsRequestDto assignmentsRequestDto) {

        Assignments assignments = new Assignments();
        assignments.setTitle(assignmentsRequestDto.getTitle());
        assignments.setDescription(assignmentsRequestDto.getDescription());
        assignments.setDueDate(assignmentsRequestDto.getDueDate());

        Courses course = coursesRepository.findById(assignmentsRequestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + assignmentsRequestDto.getCourseId()));
        assignments.setCourses(course);

        return assignmentsMapper.toDto(assignmentsRepository.save(assignments));

    }


    @Override
    public AssignmentsResponseDto getAssignmentById(Long assignmentId) {
        Assignments assignments = assignmentsRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));
        return assignmentsMapper.toDto(assignments);
    }

    @Override
    public List<AssignmentsResponseDto> getAllAssignments() {
        return assignmentsRepository.findAll().stream()
                .map(assignmentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentsResponseDto> getAssignmentsByCourseId(Long courseId) {
        return assignmentsRepository.findByCoursesId(courseId).stream()
                .map(assignmentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentsResponseDto> getAssignmentsDueBefore(LocalDate dueDate) {
        return assignmentsRepository.findByDueDateBefore(dueDate).stream()
                .map(assignmentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentsResponseDto> getAssignmentsDueAfter(LocalDate dueDate) {
        return assignmentsRepository.findByDueDateAfter(dueDate)
                .stream()
                .map(assignmentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentsResponseDto> getAssignmentsDueBetween(LocalDate startDate, LocalDate endDate) {
        return assignmentsRepository.findByDueDateBetween(startDate, endDate)
                .stream()
                .map(assignmentsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentsResponseDto updateAssignment(Long assignmentId, AssignmentsRequestDto assignmentsRequestDto) {

        Assignments assignments = assignmentsRepository.findById(assignmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));

        assignments.setTitle(assignmentsRequestDto.getTitle());
        assignments.setDescription(assignmentsRequestDto.getDescription());
        assignments.setDueDate(assignmentsRequestDto.getDueDate());

        Courses course = coursesRepository.findById(assignmentsRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with ID: " + assignmentsRequestDto.getCourseId()));
        assignments.setCourses(course);

        return assignmentsMapper.toDto(assignmentsRepository.save(assignments));
    }

    @Override
    public void deleteAssignment(Long assignmentId) {
        Assignments assignments = assignmentsRepository.findById(assignmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment not found with ID: " + assignmentId));
        assignmentsRepository.delete(assignments);
    }

    @Override
    public boolean existsById(Long assignmentId) {
        return assignmentsRepository.existsById(assignmentId);
    }

}
