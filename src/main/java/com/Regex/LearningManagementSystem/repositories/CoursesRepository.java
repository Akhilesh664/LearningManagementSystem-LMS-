package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

    @Query("SELECT c FROM Courses c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Courses> findByTitleContainingIgnoreCase(@Param("title") String title);

}
