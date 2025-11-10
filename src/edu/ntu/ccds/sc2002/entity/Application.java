package edu.ntu.ccds.sc2002.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a student's internship application.
 * Implements Serializable for data persistence.
 */
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String applicationId;
    private String studentId;
    private String internshipId;
    private ApplicationStatus status;
    private LocalDateTime applicationDate;
    private boolean placementConfirmed;
    
    /**
     * Constructs an Application with specified details.
     */
    public Application(String applicationId, String studentId, String internshipId) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.internshipId = internshipId;
        this.status = ApplicationStatus.PENDING;
        this.applicationDate = LocalDateTime.now();
        this.placementConfirmed = false;
    }
    
    // Getters
    /**
     * Gets the application id.
     * @return the application id
     */
    public String getApplicationId() { return applicationId; }
    /**
     * Gets the student id.
     * @return the student id
     */
    public String getStudentId() { return studentId; }
    /**
     * Gets the internship id.
     * @return the internship id
     */
    public String getInternshipId() { return internshipId; }
    /**
     * Gets the status.
     * @return the status
     */
    public ApplicationStatus getStatus() { return status; }
    /**
     * Gets the application date.
     * @return the application date
     */
    public LocalDateTime getApplicationDate() { return applicationDate; }
    /**
     * Checks if is placement confirmed.
     * @return true, if is placement confirmed
     */
    public boolean isPlacementConfirmed() { return placementConfirmed; }
    
    // Setters
    /**
     * Sets the status.
     * @param status the new status
     */
    public void setStatus(ApplicationStatus status) { this.status = status; }
    /**
     * Sets the placement confirmed.
     * @param confirmed the new placement confirmed
     */
    public void setPlacementConfirmed(boolean confirmed) { this.placementConfirmed = confirmed; }
    
    @Override
    public String toString() {
        return String.format("Application[ID=%s, Student=%s, Internship=%s, Status=%s]",
                           applicationId, studentId, internshipId, status);
    }
}