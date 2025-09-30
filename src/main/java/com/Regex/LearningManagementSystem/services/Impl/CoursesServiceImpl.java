package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.CoursesRequestDto;
import com.Regex.LearningManagementSystem.dto.CoursesResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.entities.Batch;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.Modules;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.BatchMapper;
import com.Regex.LearningManagementSystem.mappers.CoursesMapper;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.ModulesRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService {


    private final CoursesRepository coursesRepository;
    private final UsersRepository usersRepository;
    private final ModulesRepository modulesRepository;
    private final BatchRepository batchRepository;
    private final CoursesMapper coursesMapper;
    private final BatchMapper batchMapper;


    @Override
    public CoursesResponseDto createCourse(CoursesRequestDto courseRequestDto) {
        Courses course = coursesMapper.toEntity(courseRequestDto);

        if (courseRequestDto.getCreatedById() != null) {
            Users creator = usersRepository.findById(courseRequestDto.getCreatedById())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + courseRequestDto.getCreatedById()));
            course.setCreatedBy(creator);
        }

        return coursesMapper.toDto(coursesRepository.save(course));
    }


    @Override
    public CoursesResponseDto getCourseById(Long courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
        return coursesMapper.toDto(course);
    }


    @Override
    public List<CoursesResponseDto> getAllCourses() {
        return coursesRepository.findAll()
                .stream()
                .map(coursesMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public CoursesResponseDto updateCourse(Long courseId, CoursesRequestDto courseRequestDto) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with ID: "+courseId));

        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());

        if(courseRequestDto.getCreatedById() != null){
            Users creator = usersRepository.findById(courseRequestDto.getCreatedById())
                    .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: "+courseRequestDto.getCreatedById()));
            course.setCreatedBy(creator);
        }

        course.setGoogleClassroomId(courseRequestDto.getGoogleClassroomId());
        return coursesMapper.toDto(coursesRepository.save(course));

    }


    @Override
    public void deleteCourse(Long courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
        coursesRepository.delete(course);
    }


    @Override
    public String getGoogleClassroomLink(Long courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        if (course.getGoogleClassroomId() == null || course.getGoogleClassroomId().isEmpty()) {
            throw new ResourceNotFoundException("Google Classroom link not found for course with ID: " + courseId);
        }
        return course.getGoogleClassroomId();
    }


    @Override
    public List<ModulesResponseDto> getCourseModules(Long courseId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        return course.getModules().stream()
                .map(module -> {
                    ModulesResponseDto modulesResponseDto = new ModulesResponseDto();
                    modulesResponseDto.setId(module.getId());
                    modulesResponseDto.setOrderNo(module.getOrderNo());
                    modulesResponseDto.setTitle(module.getTitle());
                    return modulesResponseDto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public CoursesResponseDto addModuleToCourse(Long courseId, Long moduleId) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with ID: " + moduleId));

        module.setCourse(course);
        modulesRepository.save(module);

        return coursesMapper.toDto(course);
    }


    @Override
    public void removeModuleFromCourse(Long courseId, Long moduleId) {
        Modules module = modulesRepository.findById(moduleId)
                .orElseThrow(()-> new ResourceNotFoundException("Module not found with ID: "+moduleId));

        if(module.getCourse() == null || module.getCourse().getId().equals(courseId)){
            throw new IllegalArgumentException("Module is not part of this course.");
        }
        module.setCourse(null);
        modulesRepository.save(module);
    }


    @Override
    public List<BatchResponseDto> getCourseBatches(Long courseId){
        // Get all batches for the course
        List<Batch> batches = batchRepository.findByCourseId(courseId);

        // Convert entities to DTOs
        return batches.stream()
                .map(batchMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CoursesResponseDto> searchCourses(String query) {
        return coursesRepository.findByTitleContainingIgnoreCase(query)
                .stream()
                .map(coursesMapper :: toDto)
                .collect(Collectors.toList());
    }
}
