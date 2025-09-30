package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesRequestDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;

import java.util.List;

public interface ModulesService {
    
    // Module CRUD operations
    ModulesResponseDto createModule(ModulesRequestDto moduleRequestDto);
    ModulesResponseDto getModuleById(Long moduleId);
    List<ModulesResponseDto> getAllModules();
    List<ModulesResponseDto> getModulesByCourseId(Long courseId);
    ModulesResponseDto updateModule(Long moduleId, ModulesRequestDto moduleRequestDto);
    void deleteModule(Long moduleId);

    // Search and filter
    List<ModulesResponseDto> searchModules(String query);

    // Module content management
    List<LessonsResponseDto> getModuleLessons(Long moduleId);
    ModulesResponseDto addLessonToModule(Long moduleId, Long lessonId);
    void removeLessonFromModule(Long moduleId, Long lessonId);


    // TODO: add later
//    // Module progress and status
//    double getModuleCompletionPercentage(Long moduleId, Long userId);
//    boolean isModuleCompleted(Long moduleId, Long userId);
//    // Module reordering
//    void reorderModule(Long courseId, Long moduleId, int newPosition);
//    // Module resources
//    void addResourceToModule(Long moduleId, String resourceUrl, String resourceType);
//    void removeResourceFromModule(Long moduleId, Long resourceId);

}
