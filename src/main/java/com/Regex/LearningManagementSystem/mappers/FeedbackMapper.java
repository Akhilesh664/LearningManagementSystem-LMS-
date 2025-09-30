package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.FeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.FeedbackResponseDto;
import com.Regex.LearningManagementSystem.entities.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "student.id", source = "studentId"),
            @Mapping(target = "courses.id", source = "courseId"),
            @Mapping(target = "createdAt", ignore = true)
    })
    Feedback toEntity(FeedbackRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "studentId", source = "student.id"),
            @Mapping(target = "studentName", source = "student.name"),
            @Mapping(target = "courseId", source = "courses.id"),
            @Mapping(target = "courseTitle", source = "courses.title")
    })
    FeedbackResponseDto toDto(Feedback feedback);
}