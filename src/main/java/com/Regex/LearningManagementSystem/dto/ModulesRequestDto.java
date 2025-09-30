package com.Regex.LearningManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModulesRequestDto {

    private String title;
    private int orderNo;
    private Long courseId;

    // [ *Important* ] Make Separate AdminUpdateFeatureDTO for lessons Number, List<Lessons>

}
