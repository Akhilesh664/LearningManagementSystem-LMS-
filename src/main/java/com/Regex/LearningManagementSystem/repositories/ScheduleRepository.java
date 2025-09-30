package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.DayOfWeek;
import com.Regex.LearningManagementSystem.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);

}
