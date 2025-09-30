package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.NotificationsRequestDto;
import com.Regex.LearningManagementSystem.dto.NotificationsResponseDto;
import com.Regex.LearningManagementSystem.entities.NotificationStatus;
import com.Regex.LearningManagementSystem.entities.NotificationType;
import com.Regex.LearningManagementSystem.entities.Notifications;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.NotificationsMapper;
import com.Regex.LearningManagementSystem.repositories.NotificationsRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UsersRepository usersRepository;
    private final NotificationsMapper notificationsMapper;

    @Override
    public NotificationsResponseDto createNotification(NotificationsRequestDto notificationsRequestDto) {
        Users user = usersRepository.findById(notificationsRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + notificationsRequestDto.getUserId()));

        Notifications notification = new Notifications();
        notification.setMessage(notificationsRequestDto.getMessage());
        notification.setType(notificationsRequestDto.getType());
        notification.setStatus(notificationsRequestDto.getStatus());
        notification.setUser(user);

        return notificationsMapper.toDto(notificationsRepository.save(notification));
    }

    @Override
    public NotificationsResponseDto getNotificationById(Long id) {
        Notifications notification = notificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + id));
        return notificationsMapper.toDto(notification);
    }

    @Override
    public List<NotificationsResponseDto> getNotificationsByUser(Long userId) {
        return notificationsRepository.findByUserId(userId).stream()
                .map(notificationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationsResponseDto> getNotificationsByStatus(NotificationStatus status) {
        return notificationsRepository.findByStatus(status).stream()
                .map(notificationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationsResponseDto> getNotificationsByType(NotificationType type) {
        return notificationsRepository.findByType(type).stream()
                .map(notificationsMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public NotificationsResponseDto updateNotificationStatus(Long id, NotificationStatus status) {
        Notifications notification = notificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + id));
        notification.setStatus(status);
        return notificationsMapper.toDto(notificationsRepository.save(notification));
    }

    @Override
    public NotificationsResponseDto markAsRead(Long id) {
        Notifications notification = notificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + id));

        notification.setStatus(NotificationStatus.READ);
        return notificationsMapper.toDto(notificationsRepository.save(notification));
    }

    @Override
    public long getUnreadCount(Long userId) {
        return notificationsRepository.countByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public List<NotificationsResponseDto> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return notificationsRepository.findBySentAtBetween(startDate, endDate).stream()
                .map(notificationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationsResponseDto> getUserNotificationsByStatus(Long userId, NotificationStatus status) {
        return notificationsRepository.findByUserIdAndStatus(userId, status).stream()
                .map(notificationsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Long id) {
        Notifications notification = notificationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with ID: " + id));
        notificationsRepository.deleteById(id);
    }


}
