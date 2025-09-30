package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackResponseDto;
import com.Regex.LearningManagementSystem.entities.SubmissionFeedback;
import com.Regex.LearningManagementSystem.entities.Submissions;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.SubmissionFeedbackMapper;
import com.Regex.LearningManagementSystem.repositories.SubmissionFeedbackRepository;
import com.Regex.LearningManagementSystem.repositories.SubmissionsRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.SubmissionFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionFeedbackServiceImpl implements SubmissionFeedbackService {

    private final SubmissionFeedbackRepository submissionFeedbackRepository;
    private final SubmissionsRepository submissionsRepository;
    private final UsersRepository usersRepository;
    private final SubmissionFeedbackMapper submissionFeedbackMapper;


    @Override
    public SubmissionFeedbackResponseDto createFeedback(SubmissionFeedbackRequestDto requestDto) {
        Submissions submission = submissionsRepository.findById(requestDto.getSubmissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));
        Users teacher = usersRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        SubmissionFeedback feedback = new SubmissionFeedback();
        feedback.setComment(requestDto.getComment());
        feedback.setMarks(requestDto.getMarks());
        feedback.setSubmission(submission);
        feedback.setTeacher(teacher);

        feedback = submissionFeedbackRepository.save(feedback);
        return submissionFeedbackMapper.toDto(feedback);
    }


    @Override
    public SubmissionFeedbackResponseDto getFeedbackById(Long feedbackId) {
        return submissionFeedbackRepository.findById(feedbackId)
                .map(submissionFeedbackMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
    }

    @Override
    public List<SubmissionFeedbackResponseDto> getFeedbackBySubmissionId(Long submissionId) {
        return submissionFeedbackRepository.findBySubmissionId(submissionId).stream()
                .map(submissionFeedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubmissionFeedbackResponseDto> getFeedbackByTeacherId(Long teacherId) {
        return submissionFeedbackRepository.findByTeacherId(teacherId).stream()
                .map(submissionFeedbackMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public boolean hasFeedbackForSubmission(Long submissionId) {
        return submissionFeedbackRepository.existsBySubmissionId(submissionId);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        SubmissionFeedback submissionFeedback = submissionFeedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback not found")); // Get feedback
        submissionFeedbackRepository.deleteById(feedbackId);
    }


}
