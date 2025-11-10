// ApplicationStatus.java
package edu.ntu.ccds.sc2002.entity;

/**
 * Enumeration for application status.
 */
public enum ApplicationStatus {
    /**
     * Application is pending review.
     */
    PENDING,
    /**
     * Application is successful.
     */
    SUCCESSFUL,
    /**
     * Application is unsuccessful.
     */
    UNSUCCESSFUL,
    /**
     * Application is withdrawn.
     */
    WITHDRAWN
}