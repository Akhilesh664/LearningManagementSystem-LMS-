package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.SubmissionsRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionsResponseDto;
import com.Regex.LearningManagementSystem.entities.Assignments;
import com.Regex.LearningManagementSystem.entities.Submissions;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.SubmissionsMapper;
import com.Regex.LearningManagementSystem.repositories.AssignmentsRepository;
import com.Regex.LearningManagementSystem.repositories.SubmissionsRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.SubmissionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionsServiceImpl implements SubmissionsService {

    private final SubmissionsRepository submissionsRepository;
    private final AssignmentsRepository assignmentsRepository;
    private final UsersRepository usersRepository;
    private final SubmissionsMapper submissionsMapper;


    @Override
    public SubmissionsResponseDto createSubmission(SubmissionsRequestDto submissionsRequestDto) {

        Assignments assignment = assignmentsRepository.findById(submissionsRequestDto.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + submissionsRequestDto.getAssignmentId()));

        Users user = usersRepository.findById(submissionsRequestDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + submissionsRequestDto.getStudentId()));

        Submissions submissions = new Submissions();
        submissions.setFileUrl(submissions.getFileUrl());
        submissions.setAssignments(assignment);
        submissions.setStudent(user);

        return submissionsMapper.toDto(submissionsRepository.save(submissions));

    }

    @Override
    public SubmissionsResponseDto getSubmissionById(Long submissionId) {
        Submissions submissions = submissionsRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + submissionId));
        return submissionsMapper.toDto(submissions);
    }


    @Override
    public List<SubmissionsResponseDto> getAllSubmissions() {
        return submissionsRepository.findAll().stream()
                .map(submissionsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SubmissionsResponseDto> getSubmissionsByAssignmentId(Long assignmentId) {
        return submissionsRepository.findByAssignmentsId(assignmentId).stream()
                .map(submissionsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SubmissionsResponseDto> getSubmissionsByStudentId(Long studentId) {
        return submissionsRepository.findByStudentId(studentId).stream()
                .map(submissionsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SubmissionsResponseDto> getSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId) {
        return submissionsRepository.findByAssignmentsIdAndStudentId(assignmentId, studentId).stream()
                .map(submissionsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<SubmissionsResponseDto> getRecentSubmissions(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return submissionsRepository.findTopRecentSubmissions(pageable).stream()
                .map(submissionsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public SubmissionsResponseDto updateSubmission(Long submissionId, SubmissionsRequestDto submissionsRequestDto) {

        Submissions submission = submissionsRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + submissionId));

        submission.setFileUrl(submissionsRequestDto.getFileUrl());

        if (submissionsRequestDto.getAssignmentId() != null) {
            Assignments assignment = assignmentsRepository.findById(submissionsRequestDto.getAssignmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));
            submission.setAssignments(assignment);
        }

        if (submissionsRequestDto.getStudentId() != null) {
            Users student = usersRepository.findById(submissionsRequestDto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
            submission.setStudent(student);
        }

        return submissionsMapper.toDto(submissionsRepository.save(submission));

    }


    @Override
    public void deleteSubmission(Long submissionId) {
        Submissions submissions = submissionsRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + submissionId));
        submissionsRepository.delete(submissions);
    }


    @Override
    public boolean existsById(Long submissionId) {
        return submissionsRepository.existsById(submissionId);
    }

    @Override
    public int countSubmissionsByAssignmentId(Long assignmentId) {
        return submissionsRepository.countByAssignmentsId(assignmentId);
    }

    @Override
    public int countSubmissionsByStudentId(Long studentId) {
        return submissionsRepository.countByStudentId(studentId);
    }

    @Override
    public int countSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId) {
        return submissionsRepository.countByAssignmentsIdAndStudentId(assignmentId, studentId);
    }


}
