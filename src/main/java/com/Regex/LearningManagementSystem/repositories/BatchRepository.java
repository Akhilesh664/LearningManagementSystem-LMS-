package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByStatus(String status);                     // for getBatchesByStatus()

    List<Batch> findByCourseId(Long courseId);                   // for getBatchesByCourseId()

    List<Batch> findByTeacherId(Long teacherId);


}
