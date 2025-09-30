package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.StudentsAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.StudentsAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.services.StudentsAttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students/attendance")
@RequiredArgsConstructor
public class StudentsAttendanceController {

    private final StudentsAttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<StudentsAttendanceResponseDto> saveAttendance(@RequestBody StudentsAttendanceRequestDto requestDto) {
        log.info("Saving attendance for studentId: {}, courseId: {}, date: {}", requestDto.getStudentId(), requestDto.getCourseId(), requestDto.getDate());
        StudentsAttendanceResponseDto response = attendanceService.saveAttendance(requestDto);
        log.info("Attendance saved successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentsAttendanceResponseDto> getAttendanceById(@PathVariable Long id) {
        log.info("Fetching attendance record with ID: {}", id);
        StudentsAttendanceResponseDto response = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentsAttendanceResponseDto>> getAttendanceByStudent(@PathVariable Long studentId) {
        log.info("Fetching attendance for studentId: {}", studentId);
        List<StudentsAttendanceResponseDto> response = attendanceService.getAttendanceByStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentsAttendanceResponseDto>> getAttendanceByCourse(@PathVariable Long courseId) {
        log.info("Fetching attendance for courseId: {}", courseId);
        List<StudentsAttendanceResponseDto> response = attendanceService.getAttendanceByCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<StudentsAttendanceResponseDto>> getStudentCourseAttendance(@PathVariable Long studentId, @PathVariable Long courseId) {
        log.info("Fetching attendance for studentId: {} in courseId: {}", studentId, courseId);
        List<StudentsAttendanceResponseDto> response = attendanceService.getStudentCourseAttendance(studentId, courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudentsAttendanceResponseDto>> getAttendanceByStatus(@PathVariable AttendanceStatus status) {
        log.info("Fetching attendance records with status: {}", status);
        List<StudentsAttendanceResponseDto> response = attendanceService.getAttendanceByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<StudentsAttendanceResponseDto>> getAttendanceByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        log.info("Fetching attendance for date: {}", localDate);
        List<StudentsAttendanceResponseDto> response = attendanceService.getAttendanceByDate(localDate);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<StudentsAttendanceResponseDto> updateAttendanceStatus(@PathVariable Long id, @RequestParam AttendanceStatus status) {
        log.info("Updating attendance status for ID: {} to {}", id, status);
        StudentsAttendanceResponseDto response = attendanceService.updateAttendanceStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        log.info("Deleting attendance record with ID: {}", id);
        attendanceService.deleteAttendance(id);
        log.info("Attendance record deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByStudentAndCourseAndDate(@RequestParam Long studentId,
                                                                   @RequestParam Long courseId,
                                                                   @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        log.info("Checking if attendance exists for studentId: {}, courseId: {}, date: {}", studentId, courseId, localDate);
        boolean exists = attendanceService.existsByStudentAndCourseAndDate(studentId, courseId, localDate);
        return ResponseEntity.ok(exists);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.