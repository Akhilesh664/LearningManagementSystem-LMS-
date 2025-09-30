package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Submissions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionsRepository extends JpaRepository<Submissions, Long> {

    List<Submissions> findByAssignmentsId(Long assignmentId);
    List<Submissions> findByStudentId(Long studentId);
    List<Submissions> findByAssignmentsIdAndStudentId(Long assignmentId, Long studentId);

    @Query("SELECT s FROM Submissions s ORDER BY s.submittedAt DESC")
    List<Submissions> findTopRecentSubmissions(Pageable pageable);

    int countByAssignmentsId(Long assignmentId);
    int countByStudentId(Long studentId);
    int countByAssignmentsIdAndStudentId(Long assignmentId, Long studentId);

}
