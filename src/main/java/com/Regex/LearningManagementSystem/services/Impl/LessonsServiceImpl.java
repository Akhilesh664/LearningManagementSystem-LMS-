package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.LessonsRequestDto;
import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.entities.Lessons;
import com.Regex.LearningManagementSystem.entities.Modules;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.LessonsMapper;
import com.Regex.LearningManagementSystem.repositories.LessonsRepository;
import com.Regex.LearningManagementSystem.repositories.ModulesRepository;
import com.Regex.LearningManagementSystem.services.LessonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonsServiceImpl implements LessonsService {

    private final LessonsRepository lessonsRepository;
    private final ModulesRepository modulesRepository;
    private final LessonsMapper lessonsMapper;

    @Override
    public LessonsResponseDto createLesson(LessonsRequestDto lessonsRequestDto) {
        Modules module = modulesRepository.findById(lessonsRequestDto.getModuleId())
                .orElseThrow(()->new ResourceNotFoundException("Module not found with id: "+lessonsRequestDto.getModuleId()));

        Lessons lesson = new Lessons();
        lesson.setTitle(lessonsRequestDto.getTitle());
        lesson.setVideoUrl(lessonsRequestDto.getVideoUrl());
        lesson.setContent(lessonsRequestDto.getContent());
        lesson.setOrderNo(lessonsRequestDto.getOrderNo());
        lesson.setModule(module);

        Lessons savedLesson = lessonsRepository.save(lesson);
        return lessonsMapper.toDto(savedLesson);

    }


    @Override
    public LessonsResponseDto getLessonById(Long lessonId) {
        Lessons lesson = lessonsRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        return lessonsMapper.toDto(lesson);
    }


    @Override
    public List<LessonsResponseDto> getAllLessons(){
        return lessonsRepository.findAll().stream()
                .map(lessonsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<LessonsResponseDto> getLessonsByModuleId(Long moduleId) {
        return lessonsRepository.findByModuleId(moduleId).stream()
                .map(lessonsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonsResponseDto updateLesson(Long lessonId, LessonsRequestDto lessonsRequestDto) {

        Lessons existingLesson = lessonsRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson not found with id: "+lessonId));

        Modules module = modulesRepository.findById(lessonsRequestDto.getModuleId())
                .orElseThrow(()-> new ResourceNotFoundException("Module not found with id: "+lessonsRequestDto.getModuleId()));

        existingLesson.setTitle(lessonsRequestDto.getTitle());
        existingLesson.setVideoUrl(lessonsRequestDto.getVideoUrl());
        existingLesson.setContent(lessonsRequestDto.getContent());
        existingLesson.setOrderNo(lessonsRequestDto.getOrderNo());
        existingLesson.setModule(module);

        Lessons updatedLesson = lessonsRepository.save(existingLesson);
        return lessonsMapper.toDto(updatedLesson);

    }

    @Override
    public void deleteLesson(Long lessonId) {
        Lessons lesson = lessonsRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson not found with id: "+lessonId));

        lessonsRepository.delete(lesson);
    }


    @Override
    public List<LessonsResponseDto> searchLessons(String query) {
        List<Lessons> lessonsList = lessonsRepository.searchByTitleOrContent(query);
        return lessonsList.stream()
                .map(lessonsMapper::toDto)
                .collect(Collectors.toList());

    }


    @Override
    public int getLessonCountByModuleId(Long moduleId) {
        return lessonsRepository.countByModuleId(moduleId);
    }


    @Override
    public boolean existsById(Long lessonId) {
        return lessonsRepository.existsById(lessonId);
    }

    @Override
    public LessonsResponseDto getNextLesson(Long currentLessonId) {
        Lessons currentLesson = lessonsRepository.findById(currentLessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + currentLessonId));

        Lessons nextLesson = lessonsRepository.findFirstByModuleIdAndOrderNoGreaterThanOrderByOrderNoAsc(
                currentLesson.getModule().getId(), currentLesson.getOrderNo());

        if(nextLesson == null){
            throw new ResourceNotFoundException("No next lesson found");
        }
        return lessonsMapper.toDto(nextLesson);
    }


    @Override
    public LessonsResponseDto getPreviousLesson(Long currentLessonId) {
        Lessons currentLesson = lessonsRepository.findById(currentLessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with ID: " + currentLessonId));

        Lessons prevLesson = lessonsRepository.findFirstByModuleIdAndOrderNoLessThanOrderByOrderNoDesc(
                currentLesson.getModule().getId(), currentLesson.getOrderNo());

        if (prevLesson == null) {
            throw new ResourceNotFoundException("No previous lesson found.");
        }
        return lessonsMapper.toDto(prevLesson);
    }

}
