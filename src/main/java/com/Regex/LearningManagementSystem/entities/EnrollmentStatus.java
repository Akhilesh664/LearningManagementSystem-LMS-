package com.Regex.LearningManagementSystem.entities;

/**
 * Enum representing the enrollment status of a student in a course or batch.
 * Used in the Enrollment entity with @Enumerated(EnumType.STRING).
 */
public enum EnrollmentStatus {

    /**
     * Enrollment is pending and awaiting approval.
     */
    PENDING,

    /**
     * Enrollment has been approved.
     */
    APPROVED,

    /**
     * Enrollment request has been rejected.
     */
    REJECTED,

    /**
     * Student has withdrawn from the course or batch after enrollment.
     */
    WITHDRAWN
}
