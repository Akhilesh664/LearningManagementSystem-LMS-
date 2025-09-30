package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.ScheduleRequestDto;
import com.Regex.LearningManagementSystem.dto.ScheduleResponseDto;
import com.Regex.LearningManagementSystem.entities.DayOfWeek;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto createOrUpdateSchedule(ScheduleRequestDto request);
    ScheduleResponseDto getScheduleForDay(DayOfWeek dayOfWeek);
    ScheduleResponseDto getCurrentDaySchedule();
    List<ScheduleResponseDto> getAllSchedules();
    void deleteSchedule(Long id);

}
