package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.NotificationStatus;
import com.Regex.LearningManagementSystem.entities.NotificationType;
import com.Regex.LearningManagementSystem.entities.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findByUserId(Long userId);

    List<Notifications> findByStatus(NotificationStatus status);

    List<Notifications> findByType(NotificationType type);

    List<Notifications> findBySentAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Notifications> findByUserIdAndStatus(Long userId, NotificationStatus status);

    long countByUserIdAndStatus(Long userId, NotificationStatus status);

}
