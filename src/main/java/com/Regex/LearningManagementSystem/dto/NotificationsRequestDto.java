package com.Regex.LearningManagementSystem.dto;

import com.Regex.LearningManagementSystem.entities.NotificationStatus;
import com.Regex.LearningManagementSystem.entities.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsRequestDto {

    private String message;
    private NotificationType type;    // EMAIL, SMS, WHATSAPP, etc.

    // 1. Admin sets this manually (OR) 2. Can be Set by backend Service Automatically Sent
    private NotificationStatus status; // SENT, PENDING, FAILED, etc.
    private Long userId; // recipient user ID

}
