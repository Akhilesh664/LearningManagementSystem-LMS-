package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.LessonsRequestDto;
import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;

import java.util.List;

public interface LessonsService {

    LessonsResponseDto createLesson(LessonsRequestDto lessonsRequestDto);
    LessonsResponseDto getLessonById(Long lessonId);
    List<LessonsResponseDto> getAllLessons();
    List<LessonsResponseDto> getLessonsByModuleId(Long moduleId);
    LessonsResponseDto updateLesson(Long lessonId, LessonsRequestDto lessonsRequestDto);
    void deleteLesson(Long lessonId);

    List<LessonsResponseDto> searchLessons(String query);

    int getLessonCountByModuleId(Long moduleId);
    boolean existsById(Long lessonId);

    LessonsResponseDto getNextLesson(Long currentLessonId);
    LessonsResponseDto getPreviousLesson(Long currentLessonId);

}
