package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.NotificationsRequestDto;
import com.Regex.LearningManagementSystem.dto.NotificationsResponseDto;
import com.Regex.LearningManagementSystem.entities.Notifications;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationsMapper {

    // Map Request DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "sentAt", ignore = true)
    Notifications toEntity(NotificationsRequestDto dto);

    // Map Request Entity -> Dto
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    NotificationsResponseDto toDto(Notifications entity);

}
