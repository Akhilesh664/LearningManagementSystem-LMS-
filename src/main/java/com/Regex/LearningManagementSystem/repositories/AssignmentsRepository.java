package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {

    List<Assignments> findByCoursesId(Long courseId);

    List<Assignments> findByDueDateBefore(LocalDate dueDate);
    
    List<Assignments> findByDueDateAfter(LocalDate dueDate);

    List<Assignments> findByDueDateBetween(LocalDate startDate, LocalDate endDate);

}
