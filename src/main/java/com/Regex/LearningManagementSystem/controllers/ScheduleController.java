package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.ScheduleRequestDto;
import com.Regex.LearningManagementSystem.dto.ScheduleResponseDto;
import com.Regex.LearningManagementSystem.entities.DayOfWeek;
import com.Regex.LearningManagementSystem.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createOrUpdateSchedule(@RequestBody ScheduleRequestDto request) {
        log.info("Creating or updating schedule for day: {}", request.getDayOfWeek());
        ScheduleResponseDto response = scheduleService.createOrUpdateSchedule(request);
        log.info("Schedule created/updated successfully for day: {}", response.getDayOfWeek());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<ScheduleResponseDto> getScheduleForDay(@PathVariable DayOfWeek dayOfWeek) {
        log.info("Fetching schedule for day: {}", dayOfWeek);
        ScheduleResponseDto response = scheduleService.getScheduleForDay(dayOfWeek);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/today")
    public ResponseEntity<ScheduleResponseDto> getCurrentDaySchedule() {
        log.info("Fetching schedule for current day");
        ScheduleResponseDto response = scheduleService.getCurrentDaySchedule();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        log.info("Fetching all schedules");
        List<ScheduleResponseDto> schedules = scheduleService.getAllSchedules();
        log.info("Total schedules fetched: {}", schedules.size());
        return ResponseEntity.ok(schedules);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        log.info("Deleting schedule with ID: {}", id);
        scheduleService.deleteSchedule(id);
        log.info("Schedule deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.