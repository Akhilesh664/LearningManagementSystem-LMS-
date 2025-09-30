package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {

    List<Modules> findByCourseId(Long courseId);

    @Query("SELECT m FROM Modules m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Modules> findByTitleContainingIgnoreCase(@Param("title") String query);

}
