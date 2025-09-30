package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.AnnouncementsRequestDto;
import com.Regex.LearningManagementSystem.dto.AnnouncementsResponseDto;
import com.Regex.LearningManagementSystem.entities.Announcements;
import com.Regex.LearningManagementSystem.entities.Courses;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.AnnouncementsMapper;
import com.Regex.LearningManagementSystem.repositories.AnnouncementsRepository;
import com.Regex.LearningManagementSystem.repositories.CoursesRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.AnnouncementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;
    private final CoursesRepository coursesRepository;
    private final AnnouncementsMapper announcementsMapper;
    private final UsersRepository usersRepository;

    @Override
    public AnnouncementsResponseDto createAnnouncement(AnnouncementsRequestDto announcementsRequestDto) {

        Announcements announcement = new Announcements();
        announcement.setTitle(announcementsRequestDto.getTitle());
        announcement.setMessage(announcementsRequestDto.getMessage());

        Courses course = coursesRepository.findById(announcementsRequestDto.getCourseId())
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with ID: "+announcementsRequestDto.getCourseId()));
        announcement.setCourses(course);

        Users postedBy = usersRepository.findById(announcementsRequestDto.getPostedById())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: "+announcementsRequestDto.getPostedById()));
        announcement.setPostedBy(postedBy);

        return announcementsMapper.toDto(announcementsRepository.save(announcement));
    }


    @Override
    public AnnouncementsResponseDto getAnnouncementById(Long announcementId) {
        Announcements announcement = announcementsRepository.findById(announcementId)
                .orElseThrow(()-> new ResourceNotFoundException("Announcement not found with ID: "+announcementId));
        return announcementsMapper.toDto(announcement);
    }


    @Override
    public List<AnnouncementsResponseDto> getAllAnnouncements() {
        return announcementsRepository.findAll().stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementsResponseDto> getAnnouncementsByCourseId(Long courseId) {
        return announcementsRepository.findByCoursesId(courseId).stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<AnnouncementsResponseDto> getAnnouncementsByUserId(Long userId) {
        return announcementsRepository.findByPostedById(userId).stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementsResponseDto updateAnnouncement(Long announcementId, AnnouncementsRequestDto announcementsRequestDto) {

        Announcements announcement = announcementsRepository.findById(announcementId)
                .orElseThrow(()-> new ResourceNotFoundException("Announcement not found with ID: "+announcementId));
        announcement.setTitle(announcementsRequestDto.getTitle());
        announcement.setMessage(announcementsRequestDto.getMessage());

        if (announcementsRequestDto.getCourseId() != null) {
            Courses course = coursesRepository.findById(announcementsRequestDto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + announcementsRequestDto.getCourseId()));
            announcement.setCourses(course);
        }

        if (announcementsRequestDto.getPostedById() != null) {
            Users postedBy = usersRepository.findById(announcementsRequestDto.getPostedById())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + announcementsRequestDto.getPostedById()));
            announcement.setPostedBy(postedBy);
        }

        return announcementsMapper.toDto(announcementsRepository.save(announcement));

    }


    @Override
    public void deleteAnnouncement(Long announcementId) {
        Announcements announcement = announcementsRepository.findById(announcementId)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with ID: " + announcementId));
        announcementsRepository.delete(announcement);
    }


    @Override
    public List<AnnouncementsResponseDto> searchAnnouncements(String query){
        return announcementsRepository.searchByTitleOrMessage(query).stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<AnnouncementsResponseDto> getRecentAnnouncements() {
        // Fetch last 5 announcements
        return announcementsRepository.findRecentAnnouncements(PageRequest.of(0, 5)).stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<AnnouncementsResponseDto> getAnnouncementsByCourseAndDateAfter(Long courseId, String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return announcementsRepository.findByCourseAndDateAfter(courseId, dateTime)
                .stream()
                .map(announcementsMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public boolean existsById(Long announcementId) {
        return announcementsRepository.existsById(announcementId);
    }

}
