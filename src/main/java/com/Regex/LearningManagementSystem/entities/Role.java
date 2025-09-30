package com.Regex.LearningManagementSystem.entities;

/**
 * Enum representing user roles in the system.
 * Used with @Enumerated(EnumType.STRING) in the Users entity.
 */

public enum Role {
    /**
     * System super administrator with full privileges.
     */
    SUPERADMIN,

    /**
     * Admin user with high-level access, but not full system control.
     */
    ADMIN,

    /**
     * Teacher role, responsible for managing courses and evaluating students.
     */
    TEACHER,

    /**
     * Student role, the end user consuming course content.
     */
    STUDENT;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
