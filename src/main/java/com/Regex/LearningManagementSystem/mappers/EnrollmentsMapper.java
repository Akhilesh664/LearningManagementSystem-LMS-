package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.EnrollmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.EnrollmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.Enrollments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnrollmentsMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "student.id", source = "studentId"),
            @Mapping(target = "courses.id", source = "courseId"),
            @Mapping(target = "enrolledAt", ignore = true),
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "batch", ignore = true) // set separately in admin flow
    })
    Enrollments toEntity(EnrollmentsRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "studentId", source = "student.id"),
            @Mapping(target = "studentName", source = "student.name"),
            @Mapping(target = "studentEmail", source = "student.email"),
            @Mapping(target = "courseId", source = "courses.id"),
            @Mapping(target = "courseTitle", source = "courses.title"),
            @Mapping(target = "batchId", source = "batch.id"),
            @Mapping(target = "batchName", source = "batch.name")
    })
    EnrollmentsResponseDto toDto(Enrollments enrollment);
}