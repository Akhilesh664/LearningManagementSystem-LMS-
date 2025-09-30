package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.NotificationsRequestDto;
import com.Regex.LearningManagementSystem.dto.NotificationsResponseDto;
import com.Regex.LearningManagementSystem.entities.NotificationStatus;
import com.Regex.LearningManagementSystem.entities.NotificationType;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationsService {

    NotificationsResponseDto createNotification(NotificationsRequestDto requestDto);
    NotificationsResponseDto getNotificationById(Long id);
    List<NotificationsResponseDto> getNotificationsByUser(Long userId);
    List<NotificationsResponseDto> getNotificationsByStatus(NotificationStatus status);
    List<NotificationsResponseDto> getNotificationsByType(NotificationType type);
    NotificationsResponseDto updateNotificationStatus(Long id, NotificationStatus status);

    NotificationsResponseDto markAsRead(Long id);
    long getUnreadCount(Long userId);
    List<NotificationsResponseDto> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<NotificationsResponseDto> getUserNotificationsByStatus(Long userId, NotificationStatus status);
    void deleteNotification(Long id);

}
