package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesRequestDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.services.ModulesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModulesController {

    private final ModulesService modulesService;

    @PostMapping
    public ResponseEntity<ModulesResponseDto> createModule(@RequestBody ModulesRequestDto moduleRequestDto) {
        log.info("Creating new module: {}", moduleRequestDto.getTitle());
        ModulesResponseDto createdModule = modulesService.createModule(moduleRequestDto);
        log.info("Module created successfully with ID: {}", createdModule.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModule);
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModulesResponseDto> getModuleById(@PathVariable Long moduleId) {
        log.info("Fetching module details for ID: {}", moduleId);
        ModulesResponseDto module = modulesService.getModuleById(moduleId);
        return ResponseEntity.ok(module);
    }

    @GetMapping
    public ResponseEntity<List<ModulesResponseDto>> getAllModules() {
        log.info("Fetching all modules");
        List<ModulesResponseDto> modules = modulesService.getAllModules();
        log.info("Total modules fetched: {}", modules.size());
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ModulesResponseDto>> getModulesByCourseId(@PathVariable Long courseId) {
        log.info("Fetching modules for course ID: {}", courseId);
        List<ModulesResponseDto> modules = modulesService.getModulesByCourseId(courseId);
        return ResponseEntity.ok(modules);
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<ModulesResponseDto> updateModule(@PathVariable Long moduleId,
                                                           @RequestBody ModulesRequestDto moduleRequestDto) {
        log.info("Updating module with ID: {}", moduleId);
        ModulesResponseDto updatedModule = modulesService.updateModule(moduleId, moduleRequestDto);
        log.info("Module updated successfully with ID: {}", updatedModule.getId());
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<String> deleteModule(@PathVariable Long moduleId) {
        log.info("Deleting module with ID: {}", moduleId);
        modulesService.deleteModule(moduleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ModulesResponseDto>> searchModules(@RequestParam String query) {
        log.info("Searching modules with query: {}", query);
        List<ModulesResponseDto> modules = modulesService.searchModules(query);
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/{moduleId}/lessons")
    public ResponseEntity<List<LessonsResponseDto>> getModuleLessons(@PathVariable Long moduleId) {
        log.info("Fetching lessons for module ID: {}", moduleId);
        List<LessonsResponseDto> lessons = modulesService.getModuleLessons(moduleId);
        return ResponseEntity.ok(lessons);
    }

    @PostMapping("/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<ModulesResponseDto> addLessonToModule(@PathVariable Long moduleId, @PathVariable Long lessonId) {
        log.info("Adding lesson ID: {} to module ID: {}", lessonId, moduleId);
        ModulesResponseDto updatedModule = modulesService.addLessonToModule(moduleId, lessonId);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<String> removeLessonFromModule(@PathVariable Long moduleId, @PathVariable Long lessonId) {
        log.info("Removing lesson ID: {} from module ID: {}", lessonId, moduleId);
        modulesService.removeLessonFromModule(moduleId, lessonId);
        return ResponseEntity.ok("Lesson removed from module successfully");
    }
}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.