package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.ScheduleRequestDto;
import com.Regex.LearningManagementSystem.dto.ScheduleResponseDto;
import com.Regex.LearningManagementSystem.entities.Schedule;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    // Request DTO → Entity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dayOfWeek", source = "dayOfWeek")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "fileType", source = "fileType")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Schedule toEntity(ScheduleRequestDto dto);

    // Entity → Response DTO
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dayOfWeek", source = "dayOfWeek")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "fileType", source = "fileType")
    ScheduleResponseDto toDto(Schedule entity);
}