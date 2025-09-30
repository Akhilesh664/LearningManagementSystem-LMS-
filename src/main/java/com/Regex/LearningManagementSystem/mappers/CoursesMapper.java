package com.Regex.LearningManagementSystem.mappers;


import com.Regex.LearningManagementSystem.dto.CoursesRequestDto;
import com.Regex.LearningManagementSystem.dto.CoursesResponseDto;
import com.Regex.LearningManagementSystem.dto.ModulesResponseDto;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.Modules;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursesMapper {

    // DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdBy.id", source = "createdById"),
            @Mapping(target = "modules", ignore = true) // Not set from request DTO
    })
    Courses toEntity(CoursesRequestDto dto);

    // Entity → DTO
    @Mappings({
            @Mapping(target = "createdById", source = "createdBy.id"),
            @Mapping(target = "createdByName", source = "createdBy.name"),
            @Mapping(target = "moduleCount", expression = "java(course.getModules() != null ? course.getModules().size() : 0)"),
            @Mapping(target = "modules", source = "modules")
    })
    CoursesResponseDto toDto(Courses course);

    // ✅ FIXED: Map single Module → Response DTO
    @Mappings({
            @Mapping(target = "courseId", source = "course.id"),
            @Mapping(target = "courseTitle", source = "course.title"),
            @Mapping(target = "lessonCount", expression = "java(module.getLessons() != null ? module.getLessons().size() : 0)")
    })
    ModulesResponseDto toDto(Modules module);


    // ✅ MapStruct will now use this automatically
    List<ModulesResponseDto> map(List<Modules> modules);

}