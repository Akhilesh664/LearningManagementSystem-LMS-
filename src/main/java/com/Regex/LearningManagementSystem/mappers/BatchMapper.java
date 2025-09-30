package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.BatchRequestDto;
import com.Regex.LearningManagementSystem.dto.BatchResponseDto;
import com.Regex.LearningManagementSystem.entities.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "courses.id", source = "courseId"),
            @Mapping(target = "teacher.id", source = "teacherId"),
            @Mapping(target = "students", ignore = true) // Handled elsewhere (not in request)
    })
    Batch toEntity(BatchRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "courseId", source = "courses.id"),
            @Mapping(target = "courseTitle", source = "courses.title"),
            @Mapping(target = "teacherId", source = "teacher.id"),
            @Mapping(target = "teacherName", source = "teacher.name"),
            @Mapping(target = "studentCount", expression = "java(batch.getStudents() != null ? batch.getStudents().size() : 0)"),
            @Mapping(target = "students", source = "students")
    })
    BatchResponseDto toDto(Batch batch);
}