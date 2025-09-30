package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.ScheduleRequestDto;
import com.Regex.LearningManagementSystem.dto.ScheduleResponseDto;
import com.Regex.LearningManagementSystem.entities.DayOfWeek;
import com.Regex.LearningManagementSystem.entities.Schedule;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.ScheduleMapper;
import com.Regex.LearningManagementSystem.repositories.ScheduleRepository;
import com.Regex.LearningManagementSystem.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleResponseDto createOrUpdateSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findByDayOfWeek(scheduleRequestDto.getDayOfWeek())
                .orElse(new Schedule());

        schedule.setDayOfWeek(scheduleRequestDto.getDayOfWeek());

//        [ToDo later : Actual logic of uploading through MultiPart]
        schedule.setImageUrl(scheduleRequestDto.getImageUrl());
        schedule.setFileType(scheduleRequestDto.getFileType());

        return scheduleMapper.toDto(scheduleRepository.save(schedule));

    }

    @Override
    public ScheduleResponseDto getScheduleForDay(DayOfWeek dayOfWeek) {
        Schedule schedule = scheduleRepository.findByDayOfWeek(dayOfWeek)
                .orElseThrow(() -> new ResourceNotFoundException("No schedule found for day: " + dayOfWeek));
        return scheduleMapper.toDto(schedule);
    }


    @Override
    public ScheduleResponseDto getCurrentDaySchedule() {
        DayOfWeek today = DayOfWeek.valueOf(LocalDate.now().getDayOfWeek().name());
        return getScheduleForDay(today);
    }


    @Override
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Schedule not found with ID: " + id));
        scheduleRepository.deleteById(schedule.getId());
    }


}
