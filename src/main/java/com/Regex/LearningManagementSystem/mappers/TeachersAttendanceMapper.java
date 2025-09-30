package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.TeachersAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.TeachersAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.TeachersAttendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeachersAttendanceMapper {

    // Convert RequestDto -> Entity
    @Mapping(source = "teacherId", target = "teacher.id")
    TeachersAttendance toEntity(TeachersAttendanceRequestDto dto);

    // Convert Entity -> ResponseDto
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.name", target = "teacherName")
    @Mapping(source = "teacher.email", target = "teacherEmail")
    TeachersAttendanceResponseDto toDto(TeachersAttendance entity);

}
