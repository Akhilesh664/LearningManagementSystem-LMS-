package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.TeachersAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.TeachersAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.AttendanceStatus;
import com.Regex.LearningManagementSystem.services.TeachersAttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/teachers/attendance")
@RequiredArgsConstructor
public class TeachersAttendanceController {

    private final TeachersAttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<TeachersAttendanceResponseDto> saveAttendance(@RequestBody TeachersAttendanceRequestDto requestDto) {
        log.info("Saving teacher attendance for teacherId: {}, date: {}", requestDto.getTeacherId(), requestDto.getDate());
        TeachersAttendanceResponseDto response = attendanceService.saveAttendance(requestDto);
        log.info("Teacher attendance saved successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachersAttendanceResponseDto> getAttendanceById(@PathVariable Long id) {
        log.info("Fetching teacher attendance with ID: {}", id);
        TeachersAttendanceResponseDto response = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeachersAttendanceResponseDto>> getAttendanceByTeacher(@PathVariable Long teacherId) {
        log.info("Fetching attendance records for teacherId: {}", teacherId);
        List<TeachersAttendanceResponseDto> response = attendanceService.getAttendanceByTeacher(teacherId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TeachersAttendanceResponseDto>> getAttendanceByStatus(@PathVariable AttendanceStatus status) {
        log.info("Fetching teacher attendance records with status: {}", status);
        List<TeachersAttendanceResponseDto> response = attendanceService.getAttendanceByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TeachersAttendanceResponseDto>> getAttendanceByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        log.info("Fetching teacher attendance records for date: {}", localDate);
        List<TeachersAttendanceResponseDto> response = attendanceService.getAttendanceByDate(localDate);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TeachersAttendanceResponseDto> updateAttendanceStatus(@PathVariable Long id, @RequestParam AttendanceStatus status) {
        log.info("Updating teacher attendance status for ID: {} to {}", id, status);
        TeachersAttendanceResponseDto response = attendanceService.updateAttendanceStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        log.info("Deleting teacher attendance record with ID: {}", id);
        attendanceService.deleteAttendance(id);
        log.info("Teacher attendance deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByTeacherAndDate(@RequestParam Long teacherId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        log.info("Checking if attendance exists for teacherId: {}, date: {}", teacherId, localDate);
        boolean exists = attendanceService.existsByTeacherAndDate(teacherId, localDate);
        return ResponseEntity.ok(exists);
    }
}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.