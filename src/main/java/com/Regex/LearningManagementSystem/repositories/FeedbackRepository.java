package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Feedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByCoursesId(Long courseId);

    List<Feedback> findByStudentId(Long studentId);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.courses.id = :courseId")
    Double findAverageRatingByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT f FROM Feedback f ORDER BY f.createdAt DESC")
    List<Feedback> findRecentFeedback(Pageable pageable);

    @Query("SELECT f FROM Feedback f WHERE f.rating BETWEEN :minRating AND :maxRating")
    List<Feedback> findByRatingBetween(@Param("minRating") int minRating, @Param("maxRating") int maxRating);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Feedback f WHERE f.student.id = :studentId AND f.courses.id = :courseId")
    boolean existsByStudentAndCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

}
