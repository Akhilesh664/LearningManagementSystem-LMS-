package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.AssignmentsRequestDto;
import com.Regex.LearningManagementSystem.dto.AssignmentsResponseDto;
import com.Regex.LearningManagementSystem.entities.Assignments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AssignmentsMapper {

    // dto -> entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "courses.id", source = "courseId"),
    })
    Assignments toEntity(AssignmentsRequestDto dto);

    // entity -> dto
    @Mappings({
            @Mapping(target = "courseId", source = "courses.id"),
            @Mapping(target = "courseTitle", source = "courses.title")
    })
    AssignmentsResponseDto toDto(Assignments entity);

}
