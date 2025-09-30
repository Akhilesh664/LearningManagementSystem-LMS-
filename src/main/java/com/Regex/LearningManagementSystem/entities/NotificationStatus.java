package com.Regex.LearningManagementSystem.entities;

/**
 * Enum representing the delivery status of a notification.
 * This is used to track the state of each notification sent to a user.
 */
public enum NotificationStatus {

    /**
     * Notification is Unread.
     */
    UNREAD,
    /**
     * Notification is created but not yet sent.
     */
    PENDING,

    /**
     * Notification was successfully delivered.
     */
    SENT,

    /**
     * Notification was opened or seen by the recipient.
     */
    READ,

    /**
     * Notification delivery failed (e.g., email bounced, WhatsApp not delivered).
     */
    FAILED
}

