package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.UsersRequestDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.Users;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    // Request DTO → Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "batch.id", source = "batchId"),
            @Mapping(target = "password", ignore = true), // Excluded from request DTO
            @Mapping(target = "isActive", ignore = true)
    })
    Users toEntity(UsersRequestDto dto);

    // Entity → Response DTO
    @Mappings({
            @Mapping(target = "batchId", source = "batch.id"),
            @Mapping(target = "batchName", source = "batch.name")
    })
    UsersResponseDto toDto(Users entity);
}