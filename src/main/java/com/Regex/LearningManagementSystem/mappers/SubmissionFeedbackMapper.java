package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackRequestDto;
import com.Regex.LearningManagementSystem.dto.SubmissionFeedbackResponseDto;
import com.Regex.LearningManagementSystem.entities.SubmissionFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubmissionFeedbackMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "submission.id", source = "submissionId"),
            @Mapping(target = "teacher.id", source = "teacherId"),
            @Mapping(target = "createdAt", ignore = true)
    })
    SubmissionFeedback toEntity(SubmissionFeedbackRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "submissionId", source = "submission.id"),
            @Mapping(target = "assignmentTitle", source = "submission.assignments.title"),
            @Mapping(target = "studentId", source = "submission.student.id"),
            @Mapping(target = "studentName", source = "submission.student.name"),
            @Mapping(target = "teacherId", source = "teacher.id"),
            @Mapping(target = "teacherName", source = "teacher.name"),
            @Mapping(target = "createdAt", ignore = true)
    })
    SubmissionFeedbackResponseDto toDto(SubmissionFeedback feedback);
}