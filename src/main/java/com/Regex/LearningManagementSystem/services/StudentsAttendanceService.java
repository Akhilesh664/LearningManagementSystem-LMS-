package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.StudentsAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.StudentsAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;

import java.time.LocalDate;
import java.util.List;

public interface StudentsAttendanceService {

    StudentsAttendanceResponseDto saveAttendance(StudentsAttendanceRequestDto studentsAttendanceRequestDto);
    StudentsAttendanceResponseDto getAttendanceById(Long id);
    List<StudentsAttendanceResponseDto> getAttendanceByStudent(Long studentId);
    List<StudentsAttendanceResponseDto> getAttendanceByCourse(Long courseId);
    List<StudentsAttendanceResponseDto> getStudentCourseAttendance(Long studentId, Long courseId);
    List<StudentsAttendanceResponseDto> getAttendanceByStatus(AttendanceStatus status);

    List<StudentsAttendanceResponseDto> getAttendanceByDate(LocalDate date);
    StudentsAttendanceResponseDto updateAttendanceStatus(Long id, AttendanceStatus status);
    void deleteAttendance(Long id);
    boolean existsByStudentAndCourseAndDate(Long studentId, Long courseId, LocalDate date);

}
