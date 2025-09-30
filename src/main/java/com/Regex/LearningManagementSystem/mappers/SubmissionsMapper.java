package com.Regex.LearningManagementSystem.mappers;


import com.Regex.LearningManagementSystem.dto.SubmissionsRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionsResponseDto;
import com.Regex.LearningManagementSystem.entities.Submissions;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubmissionsMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "assignments.id", source = "assignmentId"),
            @Mapping(target = "student.id", source = "studentId"),
            @Mapping(target = "submittedAt", ignore = true)
    })
    Submissions toEntity(SubmissionsRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "assignmentId", source = "assignments.id"),
            @Mapping(target = "assignmentTitle", source = "assignments.title"),
            @Mapping(target = "studentId", source = "student.id"),
            @Mapping(target = "studentName", source = "student.name")
    })
    SubmissionsResponseDto toDto(Submissions entity);
}