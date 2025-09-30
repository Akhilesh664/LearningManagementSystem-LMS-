package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.CoursesRequestDto;
import com.Regex.LearningManagementSystem.dto.CoursesResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.services.CoursesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CoursesService coursesService;

    @PostMapping
    public ResponseEntity<CoursesResponseDto> createCourse(@RequestBody CoursesRequestDto courseRequestDto) {
        log.info("Creating new course: {}", courseRequestDto.getTitle());
        CoursesResponseDto createdCourse = coursesService.createCourse(courseRequestDto);
        log.info("Course created successfully with ID: {}", createdCourse.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CoursesResponseDto> getCourseById(@PathVariable Long courseId) {
        log.info("Fetching course details for ID: {}", courseId);
        CoursesResponseDto course = coursesService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CoursesResponseDto>> getAllCourses() {
        log.info("Fetching all courses");
        List<CoursesResponseDto> courses = coursesService.getAllCourses();
        log.info("Total courses fetched: {}", courses.size());
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CoursesResponseDto> updateCourse(@PathVariable Long courseId,
                                                           @RequestBody CoursesRequestDto courseRequestDto) {
        log.info("Updating course with ID: {}", courseId);
        CoursesResponseDto updatedCourse = coursesService.updateCourse(courseId, courseRequestDto);
        log.info("Course updated successfully with ID: {}", updatedCourse.getId());
        return ResponseEntity.ok(updatedCourse);
    }


    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        log.info("Deleting course with ID: {}", courseId);
        coursesService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{courseId}/classroom-link")
    public ResponseEntity<String> getGoogleClassroomLink(@PathVariable Long courseId) {
        log.info("Fetching Google Classroom link for course ID: {}", courseId);
        String link = coursesService.getGoogleClassroomLink(courseId);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/{courseId}/modules")
    public ResponseEntity<List<ModulesResponseDto>> getCourseModules(@PathVariable Long courseId) {
        log.info("Fetching modules for course ID: {}", courseId);
        List<ModulesResponseDto> modules = coursesService.getCourseModules(courseId);
        return ResponseEntity.ok(modules);
    }

    @PostMapping("/{courseId}/modules/{moduleId}")
    public ResponseEntity<CoursesResponseDto> addModuleToCourse(@PathVariable Long courseId, @PathVariable Long moduleId) {
        log.info("Adding module ID: {} to course ID: {}", moduleId, courseId);
        CoursesResponseDto updatedCourse = coursesService.addModuleToCourse(courseId, moduleId);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}/modules/{moduleId}")
    public ResponseEntity<String> removeModuleFromCourse(@PathVariable Long courseId, @PathVariable Long moduleId) {
        log.info("Removing module ID: {} from course ID: {}", moduleId, courseId);
        coursesService.removeModuleFromCourse(courseId, moduleId);
        return ResponseEntity.ok("Module removed from course successfully");
    }

    @GetMapping("/{courseId}/batches")
    public ResponseEntity<List<BatchResponseDto>> getCourseBatches(@PathVariable Long courseId) {
        log.info("Fetching batches for course ID: {}", courseId);
        List<BatchResponseDto> batches = coursesService.getCourseBatches(courseId);
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CoursesResponseDto>> searchCourses(@RequestParam String query) {
        log.info("Searching courses with query: {}", query);
        List<CoursesResponseDto> courses = coursesService.searchCourses(query);
        return ResponseEntity.ok(courses);
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.