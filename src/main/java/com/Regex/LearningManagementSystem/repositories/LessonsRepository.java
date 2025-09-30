package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonsRepository extends JpaRepository<Lessons, Long> {

    List<Lessons> findByModuleId(Long moduleId);

    @Query("SELECT l FROM Lessons l WHERE LOWER(l.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(l.content) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Lessons> searchByTitleOrContent(@Param("title") String query);

    int countByModuleId(Long moduleId);

    Lessons findFirstByModuleIdAndOrderNoGreaterThanOrderByOrderNoAsc(Long id, int orderNo);

    Lessons findFirstByModuleIdAndOrderNoLessThanOrderByOrderNoDesc(Long id, int orderNo);

}
