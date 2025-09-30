package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.AnnouncementsRequestDto;
import com.Regex.LearningManagementSystem.dto.AnnouncementsResponseDto;

import java.util.List;

public interface AnnouncementsService {

    AnnouncementsResponseDto createAnnouncement(AnnouncementsRequestDto announcementsRequestDto);
    AnnouncementsResponseDto getAnnouncementById(Long announcementId);
    List<AnnouncementsResponseDto> getAllAnnouncements();
    List<AnnouncementsResponseDto> getAnnouncementsByCourseId(Long courseId);
    List<AnnouncementsResponseDto> getAnnouncementsByUserId(Long userId);
    AnnouncementsResponseDto updateAnnouncement(Long announcementId, AnnouncementsRequestDto announcementsRequestDto);
    void deleteAnnouncement(Long announcementId);

    List<AnnouncementsResponseDto> searchAnnouncements(String query);

    List<AnnouncementsResponseDto> getRecentAnnouncements();
    List<AnnouncementsResponseDto> getAnnouncementsByCourseAndDateAfter(Long courseId, String date);

    boolean existsById(Long announcementId);

}
