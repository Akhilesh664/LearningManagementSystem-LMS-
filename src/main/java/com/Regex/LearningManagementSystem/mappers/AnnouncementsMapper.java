package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.AnnouncementsRequestDto;
import com.Regex.LearningManagementSystem.dto.AnnouncementsResponseDto;
import com.Regex.LearningManagementSystem.entities.Announcements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AnnouncementsMapper {

    // dto -> entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "courses.id", source = "courseId"),
            @Mapping(target = "postedBy.id", source = "postedById"),
            @Mapping(target = "createdAt", ignore = true)
    })
    Announcements toEntity(AnnouncementsRequestDto dto);

    // entity -> dto
    @Mappings({
            @Mapping(target = "courseId", source = "courses.id"),
            @Mapping(target = "courseTitle", source = "courses.title"),
            @Mapping(target = "postedById", source = "postedBy.id"),
            @Mapping(target = "postedByName", source = "postedBy.name"),
    })
    AnnouncementsResponseDto toDto(Announcements entity);
}
