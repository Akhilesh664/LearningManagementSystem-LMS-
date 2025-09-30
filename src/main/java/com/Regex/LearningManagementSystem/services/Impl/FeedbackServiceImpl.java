package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.FeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.FeedbackResponseDto;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.Feedback;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.FeedbackMapper;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.FeedbackRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final FeedbackMapper feedbackMapper;


    @Override
    public FeedbackResponseDto createFeedback(FeedbackRequestDto feedbackRequestDto) {

        Feedback feedback = new Feedback();

        feedback.setRating(feedbackRequestDto.getRating());
        feedback.setComment(feedbackRequestDto.getComment());

        Courses course = coursesRepository.findById(feedbackRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Feedback not found with ID: "+feedbackRequestDto.getCourseId()));

        Users student = usersRepository.findById(feedbackRequestDto.getStudentId())
                .orElseThrow(()-> new ResourceNotFoundException("Feedback not found with ID: "+feedbackRequestDto.getStudentId()));

        feedback.setCourses(course);
        feedback.setStudent(student);

        return feedbackMapper.toDto(feedbackRepository.save(feedback));

    }


    @Override
    public FeedbackResponseDto getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback not found with ID: "+feedbackId));

        return feedbackMapper.toDto(feedback);
    }


    @Override
    public List<FeedbackResponseDto> getAllFeedback() {
        return feedbackRepository.findAll().stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<FeedbackResponseDto> getFeedbackByCourseId(Long courseId) {
        return feedbackRepository.findByCoursesId(courseId).stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<FeedbackResponseDto> getFeedbackByStudentId(Long studentId) {
        return feedbackRepository.findByStudentId(studentId).stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackResponseDto> getRecentFeedback(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return feedbackRepository.findRecentFeedback(pageable).stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public FeedbackResponseDto updateFeedback(Long feedbackId, FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));

        feedback.setComment(feedbackRequestDto.getComment());
        feedback.setRating(feedbackRequestDto.getRating());

        if (feedbackRequestDto.getStudentId() != null) {
            Users student = usersRepository.findById(feedbackRequestDto.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
            feedback.setStudent(student);
        }

        if (feedbackRequestDto.getCourseId() != null) {
            Courses course = coursesRepository.findById(feedbackRequestDto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            feedback.setCourses(course);
        }

        return feedbackMapper.toDto(feedbackRepository.save(feedback));
    }


    @Override
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(()-> new ResourceNotFoundException("Feedback not found with ID: "+feedbackId));
        feedbackRepository.delete(feedback);
    }


    @Override
    public boolean existsById(Long feedbackId) {
        return feedbackRepository.existsById(feedbackId);
    }


    @Override
    public double getAverageRatingByCourseId(Long courseId) {
        Double avg = feedbackRepository.findAverageRatingByCourseId(courseId);
        return (avg != null) ? avg : 0.0;
    }


    @Override
    public List<FeedbackResponseDto> getFeedbackByRatingRange(int minRating, int maxRating) {
        return feedbackRepository.findByRatingBetween(minRating, maxRating).stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public boolean hasStudentGivenFeedback(Long studentId, Long courseId) {
        return feedbackRepository.existsByStudentAndCourse(studentId, courseId);
    }


}
