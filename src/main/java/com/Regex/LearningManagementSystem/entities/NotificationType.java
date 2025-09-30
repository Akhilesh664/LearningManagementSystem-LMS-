package com.Regex.LearningManagementSystem.entities;

/**
 * Enum representing the type of notification delivery method.
 * This is used to define how a message or announcement should be sent to users.
 */
public enum NotificationType {

    /**
     * Notification sent via email.
     */
    EMAIL,

    /**
     * Notification sent via SMS (text message).
     */
    SMS,

    /**
     * Notification shown inside the application dashboard.
     */
    DASHBOARD,

    /**
     * Notification sent via WhatsApp using WhatsApp Business API or similar integration.
     */
    WHATSAPP
}
