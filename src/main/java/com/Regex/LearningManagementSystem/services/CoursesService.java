package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.dto.CoursesRequestDto;
import com.Regex.LearningManagementSystem.dto.CoursesResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.entities.Batch;
import com.Regex.LearningManagementSystem.entities.Users;

import java.util.List;

public interface CoursesService {
    
    // Course CRUD operations
    CoursesResponseDto createCourse(CoursesRequestDto courseRequestDto);
    CoursesResponseDto getCourseById(Long courseId);
    List<CoursesResponseDto> getAllCourses();
    CoursesResponseDto updateCourse(Long courseId, CoursesRequestDto courseRequestDto);
    void deleteCourse(Long courseId);
    String getGoogleClassroomLink(Long courseId);


    // Course content management
    List<ModulesResponseDto> getCourseModules(Long courseId);
    CoursesResponseDto addModuleToCourse(Long courseId, Long moduleId);
    void removeModuleFromCourse(Long courseId, Long moduleId);


    // Batch management for courses
    List<BatchResponseDto> getCourseBatches(Long courseId);


    // Search and filter
    List<CoursesResponseDto> searchCourses(String query);




    // TODO: add later
//    // Google Classroom integration
//    String syncWithGoogleClassroom(Long courseId);
//    // Course statistics
//    double getCourseAverageRating(Long courseId);
//    int getTotalModulesCount(Long courseId);
//    int getTotalLessonsCount(Long courseId);
//    // Course content management
//    String uploadCourseMaterial(Long courseId, String materialUrl, String materialType);
//    void removeCourseMaterial(Long courseId, Long materialId);
//    // Student enrollment
//    List<Users> getEnrolledStudents(Long courseId);
//    int getEnrolledStudentsCount(Long courseId);


}
