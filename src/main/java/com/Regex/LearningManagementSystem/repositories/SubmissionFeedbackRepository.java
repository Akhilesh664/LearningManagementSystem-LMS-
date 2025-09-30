package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.SubmissionFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionFeedbackRepository extends JpaRepository<SubmissionFeedback, Long> {
    List<SubmissionFeedback> findBySubmissionId(Long submissionId);
    List<SubmissionFeedback> findByTeacherId(Long teacherId);
    boolean existsBySubmissionId(Long submissionId);
}