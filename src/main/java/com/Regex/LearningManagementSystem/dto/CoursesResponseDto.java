package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesResponseDto {

    private Long id;
    private String title;
    private String description;
    private String googleClassroomId;

    private Long createdById;
    private String createdByName;

    private int moduleCount;             // Optional
    private List<ModulesResponseDto> modules;   // ✅ List of module titles only

}
