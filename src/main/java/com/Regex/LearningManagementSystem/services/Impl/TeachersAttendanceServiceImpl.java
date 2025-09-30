package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.TeachersAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.TeachersAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.entities.TeachersAttendance;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.TeachersAttendanceMapper;
import com.Regex.LearningManagementSystem.repositories.TeachersAttendanceRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.TeachersAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeachersAttendanceServiceImpl implements TeachersAttendanceService {

    private final TeachersAttendanceRepository teachersAttendanceRepository;
    private final UsersRepository usersRepository;
    private final TeachersAttendanceMapper teachersAttendanceMapper;

    @Override
    public TeachersAttendanceResponseDto saveAttendance(TeachersAttendanceRequestDto teachersAttendanceRequestDto) {
        Users teacher = usersRepository.findById(teachersAttendanceRequestDto.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        TeachersAttendance attendance = new TeachersAttendance();
        attendance.setDate(teachersAttendanceRequestDto.getDate());
        attendance.setStatus(teachersAttendanceRequestDto.getStatus());
        attendance.setTeacher(teacher);

        return teachersAttendanceMapper.toDto(teachersAttendanceRepository.save(attendance));
    }

    @Override
    public TeachersAttendanceResponseDto getAttendanceById(Long id) {
        TeachersAttendance attendance = teachersAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found"));
        return teachersAttendanceMapper.toDto(attendance);
    }

    @Override
    public List<TeachersAttendanceResponseDto> getAttendanceByTeacher(Long teacherId) {
        return teachersAttendanceRepository.findByTeacherId(teacherId).stream()
                .map(teachersAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeachersAttendanceResponseDto> getAttendanceByStatus(AttendanceStatus status) {
        return teachersAttendanceRepository.findByStatus(status).stream()
                .map(teachersAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeachersAttendanceResponseDto> getAttendanceByDate(LocalDate date) {
        return teachersAttendanceRepository.findByDate(date).stream()
                .map(teachersAttendanceMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public TeachersAttendanceResponseDto updateAttendanceStatus(Long id, AttendanceStatus status) {
        TeachersAttendance attendance = teachersAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found"));
        attendance.setStatus(status);
        return teachersAttendanceMapper.toDto(teachersAttendanceRepository.save(attendance));
    }

    @Override
    public void deleteAttendance(Long id) {
        TeachersAttendance attendance = teachersAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance record not found"));
        teachersAttendanceRepository.deleteById(id);
    }

    @Override
    public boolean existsByTeacherAndDate(Long teacherId, LocalDate date) {
        return teachersAttendanceRepository.existsByTeacherIdAndDate(teacherId, date);
    }





}
