package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.entities.StudentsAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface StudentsAttendanceRepository extends JpaRepository<StudentsAttendance, Long> {

    List<StudentsAttendance> findByStudentId(Long studentId);
    List<StudentsAttendance> findByCourseId(Long courseId);
    List<StudentsAttendance> findByStudentIdAndCourseId(Long studentId, Long courseId);
    List<StudentsAttendance> findByStatus(AttendanceStatus status);
    List<StudentsAttendance> findByDate(LocalDate date);
    boolean existsByStudentIdAndCourseIdAndDate(Long studentId, Long courseId, LocalDate date);

}
