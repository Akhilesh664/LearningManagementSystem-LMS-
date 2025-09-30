package com.Regex.LearningManagementSystem.entities;

/**
 * Enum representing possible attendance statuses for a student.
 * This will be used with @Enumerated(EnumType.STRING) in the Attendance entity.
 */
public enum AttendanceStatus {
    /**
     * Student was present on the given day.
     */
    PRESENT,

    /**
     * Student was absent on the given day.
     */
    ABSENT,

    /**
     * Student was late to the session.
     */
    LATE,

    /**
     * Student was at leave.
     */
    LEAVE,

    /**
     * Student was excused from the session.
     */
    EXCUSED
}
