package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.StudentsAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.StudentsAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.StudentsAttendance;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.StudentsAttendanceMapper;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.StudentsAttendanceRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.StudentsAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsAttendanceServiceImpl implements StudentsAttendanceService {


    private final StudentsAttendanceRepository studentsAttendanceRepository;
    private final UsersRepository usersRepository;
    private final CoursesRepository coursesRepository;
    private final StudentsAttendanceMapper studentsAttendanceMapper;


    public StudentsAttendanceResponseDto saveAttendance(StudentsAttendanceRequestDto studentsAttendanceRequestDto) {
        Users Student = usersRepository.findById(studentsAttendanceRequestDto.getStudentId())
                .orElseThrow(()-> new ResourceNotFoundException("Student not found with ID: "+studentsAttendanceRequestDto.getStudentId()));

        Courses course = coursesRepository.findById(studentsAttendanceRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with ID: "+studentsAttendanceRequestDto.getCourseId()));

        StudentsAttendance studentsAttendance = new StudentsAttendance();

        studentsAttendance.setDate(studentsAttendanceRequestDto.getDate());
        studentsAttendance.setStatus(studentsAttendanceRequestDto.getStatus());
        studentsAttendance.setStudent(Student);
        studentsAttendance.setCourses(course);

        return studentsAttendanceMapper.toDto(studentsAttendanceRepository.save(studentsAttendance));
    }


    @Override
    public StudentsAttendanceResponseDto getAttendanceById(Long id) {
        StudentsAttendance studentsAttendance = studentsAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        return studentsAttendanceMapper.toDto(studentsAttendance);
    }

    @Override
    public List<StudentsAttendanceResponseDto> getAttendanceByStudent(Long studentId) {
        return studentsAttendanceRepository.findByStudentId(studentId).stream()
                .map(studentsAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<StudentsAttendanceResponseDto> getAttendanceByCourse(Long courseId) {
        return studentsAttendanceRepository.findByCourseId(courseId).stream()
                .map(studentsAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentsAttendanceResponseDto> getStudentCourseAttendance(Long studentId, Long courseId) {
        return studentsAttendanceRepository.findByStudentIdAndCourseId(studentId, courseId).stream()
                .map(studentsAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentsAttendanceResponseDto> getAttendanceByStatus(AttendanceStatus status){
        return studentsAttendanceRepository.findByStatus(status).stream()
                .map(studentsAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentsAttendanceResponseDto> getAttendanceByDate(LocalDate date){
        return studentsAttendanceRepository.findByDate(date).stream()
                .map(studentsAttendanceMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public StudentsAttendanceResponseDto updateAttendanceStatus(Long id, AttendanceStatus status) {
        StudentsAttendance studentsAttendance = studentsAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        studentsAttendance.setStatus(status);
        return studentsAttendanceMapper.toDto(studentsAttendanceRepository.save(studentsAttendance));
    }


    @Override
    public void deleteAttendance(Long id) {
        StudentsAttendance studentsAttendance = studentsAttendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        studentsAttendanceRepository.delete(studentsAttendance);
    }


    @Override
    public boolean existsByStudentAndCourseAndDate(Long studentId, Long courseId, LocalDate date) {
        return studentsAttendanceRepository.existsByStudentIdAndCourseIdAndDate(studentId, courseId, date);
    }




}
