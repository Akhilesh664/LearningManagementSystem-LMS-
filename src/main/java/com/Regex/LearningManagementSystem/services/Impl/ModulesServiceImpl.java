package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesRequestDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.Lessons;
import com.Regex.LearningManagementSystem.entities.Modules;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.LessonsMapper;
import com.Regex.LearningManagementSystem.mappers.ModulesMapper;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.LessonsRepository;
import com.Regex.LearningManagementSystem.repositories.ModulesRepository;
import com.Regex.LearningManagementSystem.services.ModulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModulesServiceImpl implements ModulesService {

    private final ModulesRepository modulesRepository;
    private final CoursesRepository coursesRepository;
    private final LessonsRepository lessonsRepository;
    private final ModulesMapper modulesMapper;
    private final LessonsMapper lessonsMapper;

    @Override
    public ModulesResponseDto createModule(ModulesRequestDto modulesRequestDto){
        Courses course = coursesRepository.findById(modulesRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with ID: "+modulesRequestDto.getCourseId()));
        Modules module = new Modules();
        module.setTitle(modulesRequestDto.getTitle());
        module.setOrderNo(modulesRequestDto.getOrderNo());
        module.setCourse(course);

        Modules savedModule = modulesRepository.save(module);

        return modulesMapper.toDto(savedModule);
    }

    @Override
    public ModulesResponseDto getModuleById(Long moduleId){
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(()-> new ResourceNotFoundException("Module not found with ID: "+moduleId));

        return modulesMapper.toDto(module);
   }

    @Override
    public List<ModulesResponseDto> getAllModules(){

        return modulesRepository.findAll().stream()
                .map(modulesMapper::toDto)
                .collect(Collectors.toList());

    }


    @Override
    public List<ModulesResponseDto> getModulesByCourseId(Long courseId) {
        if (!coursesRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        return modulesRepository.findByCourseId(courseId).stream()
                .map(modulesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ModulesResponseDto updateModule(Long moduleId, ModulesRequestDto moduleRequestDto) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
        Courses course = coursesRepository.findById(moduleRequestDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + moduleRequestDto.getCourseId()));

        module.setTitle(moduleRequestDto.getTitle());
        module.setOrderNo(moduleRequestDto.getOrderNo());
        module.setCourse(course);

        Modules updatedModule = modulesRepository.save(module);
        return modulesMapper.toDto(updatedModule);
    }

    @Override
    public void deleteModule(Long moduleId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
        modulesRepository.delete(module);
    }

    @Override
    public List<ModulesResponseDto> searchModules(String query) {
        return modulesRepository.findByTitleContainingIgnoreCase(query)
                .stream()
                .map(modulesMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<LessonsResponseDto> getModuleLessons(Long moduleId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
        return module.getLessons().stream()
                .map(lessonsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public ModulesResponseDto addLessonToModule(Long moduleId, Long lessonId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));

        Lessons lesson = lessonsRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));

        module.getLessons().add(lesson);
        lesson.setModule(module);
        modulesRepository.save(module);

        return modulesMapper.toDto(module);
    }


    @Override
    public void removeLessonFromModule(Long moduleId, Long lessonId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));

        Lessons lesson = lessonsRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));

        if (module.getLessons().contains(lesson)) {
            module.getLessons().remove(lesson);
            lesson.setModule(null);
            modulesRepository.save(module);
        }
    }

}
