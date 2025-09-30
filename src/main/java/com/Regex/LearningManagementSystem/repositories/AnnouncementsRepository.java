package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Announcements;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, Long> {

    List<Announcements> findByCoursesId(Long courseId);

    List<Announcements> findByPostedById(Long userId);

    @Query("SELECT a FROM Announcements a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(a.message) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Announcements> searchByTitleOrMessage(@Param("query") String query);

    @Query("SELECT a FROM Announcements a ORDER BY a.createdAt DESC")
    List<Announcements> findRecentAnnouncements(Pageable pageable);

    @Query("SELECT a FROM Announcements a WHERE a.courses.id = :courseId AND a.createdAt > :date")
    List<Announcements> findByCourseAndDateAfter(@Param("courseId") Long courseId, @Param("date") LocalDateTime date);

}
