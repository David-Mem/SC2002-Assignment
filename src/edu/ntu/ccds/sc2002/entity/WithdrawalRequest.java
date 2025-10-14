package edu.ntu.ccds.sc2002.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a withdrawal request for an internship application.
 * Implements Serializable for data persistence.
 * 
 * @author Group X
 * @version 1.0
 */
public class WithdrawalRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String requestId;
    private String applicationId;
    private String studentId;
    private String reason;
    private WithdrawalStatus status;
    private LocalDateTime requestDate;
    private boolean isAfterConfirmation;
    
    /**
     * Constructs a WithdrawalRequest with specified details.
     * 
     * @param requestId Unique request identifier
     * @param applicationId Application being withdrawn
     * @param studentId Student making the request
     * @param reason Reason for withdrawal
     * @param isAfterConfirmation Whether this is after placement confirmation
     */
    public WithdrawalRequest(String requestId, String applicationId, String studentId, 
                            String reason, boolean isAfterConfirmation) {
        this.requestId = requestId;
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.reason = reason;
        this.status = WithdrawalStatus.PENDING;
        this.requestDate = LocalDateTime.now();
        this.isAfterConfirmation = isAfterConfirmation;
    }
    
    // Getters
    public String getRequestId() { return requestId; }
    public String getApplicationId() { return applicationId; }
    public String getStudentId() { return studentId; }
    public String getReason() { return reason; }
    public WithdrawalStatus getStatus() { return status; }
    public LocalDateTime getRequestDate() { return requestDate; }
    public boolean isAfterConfirmation() { return isAfterConfirmation; }
    
    // Setters
    public void setStatus(WithdrawalStatus status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("WithdrawalRequest[ID=%s, Student=%s, Status=%s, AfterConfirm=%s]",
                           requestId, studentId, status, isAfterConfirmation);
    }
}
