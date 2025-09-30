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
public class NotificationsResponseDto {

    private Long id;
    private String message;
    private NotificationType type;    // EMAIL, SMS, WHATSAPP, etc.
    private NotificationStatus status; // SENT, PENDING, FAILED, etc.
    private LocalDateTime sentAt;

    private Long userId;
    private String userName; // Optional: useful for admin views

}
