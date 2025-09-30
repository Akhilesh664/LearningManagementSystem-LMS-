package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.LessonsRequestDto;
import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.entities.Lessons;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LessonsMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "module.id", source = "moduleId")
    })
    Lessons toEntity(LessonsRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "moduleId", source = "module.id"),
            @Mapping(target = "moduleTitle", source = "module.title"),
            @Mapping(target = "courseId", source = "module.course.id"),
            @Mapping(target = "courseTitle", source = "module.course.title")
    })
    LessonsResponseDto toDto(Lessons lesson);
}