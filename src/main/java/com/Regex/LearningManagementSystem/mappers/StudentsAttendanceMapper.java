package com.Regex.LearningManagementSystem.mappers;

import com.Regex.LearningManagementSystem.dto.StudentsAttendanceRequestDto;
import com.Regex.LearningManagementSystem.dto.StudentsAttendanceResponseDto;
import com.Regex.LearningManagementSystem.entities.StudentsAttendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StudentsAttendanceMapper {

    // Convert RequestDto → Entity (ignore relationships, set them in Service)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "courses", ignore = true)
    StudentsAttendance toEntity(StudentsAttendanceRequestDto requestDto);

    // Convert Entity → ResponseDto
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "student.email", target = "studentEmail")
    @Mapping(source = "courses.id", target = "courseId")
    @Mapping(source = "courses.title", target = "courseTitle")
    StudentsAttendanceResponseDto toDto(StudentsAttendance attendance);

}
