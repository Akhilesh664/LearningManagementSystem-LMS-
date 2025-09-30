package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.TeachersAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.TeachersAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;

import java.time.LocalDate;
import java.util.List;

public interface TeachersAttendanceService {

    TeachersAttendanceResponseDto saveAttendance(TeachersAttendanceRequestDto requestDto);
    TeachersAttendanceResponseDto getAttendanceById(Long id);
    List<TeachersAttendanceResponseDto> getAttendanceByTeacher(Long teacherId);
    List<TeachersAttendanceResponseDto> getAttendanceByStatus(AttendanceStatus status);

    List<TeachersAttendanceResponseDto> getAttendanceByDate(LocalDate date);
    TeachersAttendanceResponseDto updateAttendanceStatus(Long id, AttendanceStatus status);
    void deleteAttendance(Long id);
    boolean existsByTeacherAndDate(Long teacherId, LocalDate date);

}
