package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.EnrollmentStatus;
import com.Regex.LearningManagementSystem.entities.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Collection;
import java.util.List;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    boolean existsByStudentIdAndCoursesId(Long studentId, Long courseId);

    List<Enrollments> findByStudentId(Long studentId);

    List<Enrollments> findByCoursesId(Long courseId);

    List<Enrollments> findByStatus(EnrollmentStatus status);

    List<Enrollments> findByBatchId(Long batchId);

    int countByCoursesId(Long courseId);

    int countByStudentId(Long studentId);

    int countByBatchId(Long batchId);

    int countByStatus(EnrollmentStatus status);

}