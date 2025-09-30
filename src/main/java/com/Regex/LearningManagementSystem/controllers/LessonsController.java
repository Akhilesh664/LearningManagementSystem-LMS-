package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.LessonsRequestDto;
import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.services.LessonsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonsController {

    private final LessonsService lessonsService;

    @PostMapping
    public ResponseEntity<LessonsResponseDto> createLesson(@RequestBody LessonsRequestDto lessonsRequestDto) {
        log.info("Creating a new lesson: {}", lessonsRequestDto.getTitle());
        LessonsResponseDto createdLesson = lessonsService.createLesson(lessonsRequestDto);
        log.info("Lesson created successfully with ID: {}", createdLesson.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonsResponseDto> getLessonById(@PathVariable Long lessonId) {
        log.info("Fetching lesson with ID: {}", lessonId);
        LessonsResponseDto lesson = lessonsService.getLessonById(lessonId);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping
    public ResponseEntity<List<LessonsResponseDto>> getAllLessons() {
        log.info("Fetching all lessons");
        List<LessonsResponseDto> lessons = lessonsService.getAllLessons();
        log.info("Total lessons fetched: {}", lessons.size());
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<LessonsResponseDto>> getLessonsByModuleId(@PathVariable Long moduleId) {
        log.info("Fetching lessons for module ID: {}", moduleId);
        List<LessonsResponseDto> lessons = lessonsService.getLessonsByModuleId(moduleId);
        return ResponseEntity.ok(lessons);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<LessonsResponseDto> updateLesson(@PathVariable Long lessonId,
                                                           @RequestBody LessonsRequestDto lessonsRequestDto) {
        log.info("Updating lesson with ID: {}", lessonId);
        LessonsResponseDto updatedLesson = lessonsService.updateLesson(lessonId, lessonsRequestDto);
        log.info("Lesson updated successfully with ID: {}", updatedLesson.getId());
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long lessonId) {
        log.info("Deleting lesson with ID: {}", lessonId);
        lessonsService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LessonsResponseDto>> searchLessons(@RequestParam String query) {
        log.info("Searching lessons with query: {}", query);
        List<LessonsResponseDto> lessons = lessonsService.searchLessons(query);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/module/{moduleId}/count")
    public ResponseEntity<Integer> getLessonCountByModuleId(@PathVariable Long moduleId) {
        log.info("Fetching lesson count for module ID: {}", moduleId);
        int count = lessonsService.getLessonCountByModuleId(moduleId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{lessonId}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long lessonId) {
        log.info("Checking if lesson exists with ID: {}", lessonId);
        boolean exists = lessonsService.existsById(lessonId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/{lessonId}/next")
    public ResponseEntity<LessonsResponseDto> getNextLesson(@PathVariable Long lessonId) {
        log.info("Fetching next lesson after ID: {}", lessonId);
        LessonsResponseDto nextLesson = lessonsService.getNextLesson(lessonId);
        return ResponseEntity.ok(nextLesson);
    }

    @GetMapping("/{lessonId}/previous")
    public ResponseEntity<LessonsResponseDto> getPreviousLesson(@PathVariable Long lessonId) {
        log.info("Fetching previous lesson before ID: {}", lessonId);
        LessonsResponseDto previousLesson = lessonsService.getPreviousLesson(lessonId);
        return ResponseEntity.ok(previousLesson);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.