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
    public String getApplicationId() { return applicationId; }
    public String getStudentId() { return studentId; }
    public String getInternshipId() { return internshipId; }
    public ApplicationStatus getStatus() { return status; }
    public LocalDateTime getApplicationDate() { return applicationDate; }
    public boolean isPlacementConfirmed() { return placementConfirmed; }
    
    // Setters
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public void setPlacementConfirmed(boolean confirmed) { this.placementConfirmed = confirmed; }
    
    @Override
    public String toString() {
        return String.format("Application[ID=%s, Student=%s, Internship=%s, Status=%s]",
                           applicationId, studentId, internshipId, status);
    }
}