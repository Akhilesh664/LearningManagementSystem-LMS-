package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.ModulesRequestDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.dto.LessonsResponseDto;
import com.Regex.LearningManagementSystem.entities.Modules;
import com.Regex.LearningManagementSystem.entities.Lessons;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ModulesMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "course.id", source = "courseId"),
            @Mapping(target = "lessons", ignore = true) // lessons are handled separately
    })
    Modules toEntity(ModulesRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "courseId", source = "course.id"),
            @Mapping(target = "courseTitle", source = "course.title"),
            @Mapping(target = "lessonCount", expression = "java(modules.getLessons() != null ? modules.getLessons().size() : 0)"),
            @Mapping(target = "lessons", source = "lessons")
    })
    ModulesResponseDto toDto(Modules modules);


    // ✅ Add this missing method
// ✅ Map single Lessons → LessonsResponseDto, including nested module info
    @Mappings({
            @Mapping(target = "moduleId", source = "module.id"),
            @Mapping(target = "moduleTitle", source = "module.title")
    })
    LessonsResponseDto toDto(Lessons lesson);

    // ✅ This now works because of the above
    List<LessonsResponseDto> map(List<Lessons> lessons);
}