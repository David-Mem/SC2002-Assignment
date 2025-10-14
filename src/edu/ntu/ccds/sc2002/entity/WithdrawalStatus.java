package edu.ntu.ccds.sc2002.entity;

/**
 * Enumeration for withdrawal request status.
 * Represents the approval state of a student's withdrawal request.
 * 
 * @author Group X
 * @version 1.0
 */
public enum WithdrawalStatus {
    /**
     * Request is pending review by Career Center Staff
     */
    PENDING,
    
    /**
     * Request has been approved by Career Center Staff
     */
    APPROVED,
    
    /**
     * Request has been rejected by Career Center Staff
     */
    REJECTED
}