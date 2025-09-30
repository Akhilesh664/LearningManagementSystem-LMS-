package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.entities.TeachersAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface TeachersAttendanceRepository extends JpaRepository<TeachersAttendance, Long> {

    List<TeachersAttendance> findByTeacherId(Long teacherId);

    List<TeachersAttendance> findByStatus(AttendanceStatus status);

    List<TeachersAttendance> findByDate(LocalDate date);

    boolean existsByTeacherIdAndDate(Long teacherId, LocalDate date);
}
