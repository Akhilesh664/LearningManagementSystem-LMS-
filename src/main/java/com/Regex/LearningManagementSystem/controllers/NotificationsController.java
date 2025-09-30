package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.NotificationsRequestDto;
import com.Regex.LearningManagementSystem.dto.NotificationsResponseDto;
import com.Regex.LearningManagementSystem.entities.NotificationStatus;
import com.Regex.LearningManagementSystem.entities.NotificationType;
import com.Regex.LearningManagementSystem.services.NotificationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsService;

    @PostMapping
    public ResponseEntity<NotificationsResponseDto> createNotification(@RequestBody NotificationsRequestDto requestDto) {
        log.info("Creating notification for userId: {} with type: {}", requestDto.getUserId(), requestDto.getType());
        NotificationsResponseDto response = notificationsService.createNotification(requestDto);
        log.info("Notification created successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationsResponseDto> getNotificationById(@PathVariable Long id) {
        log.info("Fetching notification with ID: {}", id);
        NotificationsResponseDto response = notificationsService.getNotificationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationsResponseDto>> getNotificationsByUser(@PathVariable Long userId) {
        log.info("Fetching notifications for userId: {}", userId);
        List<NotificationsResponseDto> notifications = notificationsService.getNotificationsByUser(userId);
        log.info("Total notifications fetched for userId {}: {}", userId, notifications.size());
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotificationsResponseDto>> getNotificationsByStatus(@PathVariable NotificationStatus status) {
        log.info("Fetching notifications with status: {}", status);
        List<NotificationsResponseDto> notifications = notificationsService.getNotificationsByStatus(status);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<NotificationsResponseDto>> getNotificationsByType(@PathVariable NotificationType type) {
        log.info("Fetching notifications with type: {}", type);
        List<NotificationsResponseDto> notifications = notificationsService.getNotificationsByType(type);
        return ResponseEntity.ok(notifications);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<NotificationsResponseDto> updateNotificationStatus(@PathVariable Long id,
                                                                             @RequestParam NotificationStatus status) {
        log.info("Updating notification status for ID: {} to {}", id, status);
        NotificationsResponseDto response = notificationsService.updateNotificationStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationsResponseDto> markAsRead(@PathVariable Long id) {
        log.info("Marking notification as read for ID: {}", id);
        NotificationsResponseDto response = notificationsService.markAsRead(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadCount(@PathVariable Long userId) {
        log.info("Fetching unread notifications count for userId: {}", userId);
        long count = notificationsService.getUnreadCount(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<NotificationsResponseDto>> getNotificationsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        log.info("Fetching notifications from {} to {}", startDate, endDate);
        List<NotificationsResponseDto> notifications = notificationsService.getNotificationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<NotificationsResponseDto>> getUserNotificationsByStatus(@PathVariable Long userId,
                                                                                       @PathVariable NotificationStatus status) {
        log.info("Fetching notifications for userId: {} with status: {}", userId, status);
        List<NotificationsResponseDto> notifications = notificationsService.getUserNotificationsByStatus(userId, status);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        log.info("Deleting notification with ID: {}", id);
        notificationsService.deleteNotification(id);
        log.info("Notification deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}


// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.