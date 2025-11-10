package edu.ntu.ccds.sc2002.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student user in the system.
 * Extends User class demonstrating inheritance.
 */
public class Student extends User {
    private static final long serialVersionUID = 1L;
    
    private int yearOfStudy;
    private String major;
    private List<String> applicationIds;
    private String confirmedInternshipId;
    
    /**
     * Constructs a Student with specified details.
     */
    public Student(String userId, String name, String password, int yearOfStudy, String major) {
        super(userId, name, password, UserRole.STUDENT);
        this.yearOfStudy = yearOfStudy;
        this.major = major;
        this.applicationIds = new ArrayList<>();
        this.confirmedInternshipId = null;
    }
    
    /**
     * Gets the student's year of study.
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    
    /**
     * Sets the student's year of study.
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
    
    /**
     * Gets the student's major.
     */
    public String getMajor() {
        return major;
    }
    
    /**
     * Sets the student's major.
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    /**
     * Gets the list of application IDs.
     */
    public List<String> getApplicationIds() {
        return new ArrayList<>(applicationIds);
    }
    
    /**
     * Adds an application ID to the student's applications.
     */
    public boolean addApplication(String applicationId) {
        if (applicationIds.size() >= 3) {
            return false;
        }
        if (!applicationIds.contains(applicationId)) {
            applicationIds.add(applicationId);
            return true;
        }
        return false;
    }
    
    /**
     * Removes an application from the student's applications.
     */
    public boolean removeApplication(String applicationId) {
        return applicationIds.remove(applicationId);
    }
    
    /**
     * Checks if student has reached maximum application limit.
     */
    public boolean hasReachedApplicationLimit() {
        return applicationIds.size() >= 3;
    }
    
    /**
     * Gets the confirmed internship ID.
     */
    public String getConfirmedInternshipId() {
        return confirmedInternshipId;
    }
    
    /**
     * Sets the confirmed internship ID.
     */
    public void setConfirmedInternshipId(String confirmedInternshipId) {
        this.confirmedInternshipId = confirmedInternshipId;
    }
    
    /**
     * Checks if student has a confirmed internship.
     */
    public boolean hasConfirmedInternship() {
        return confirmedInternshipId != null;
    }
    
    /**
     * Clears all internship applications for the student.
     */
    public void clearApplications() {
        applicationIds.clear();
    }
    
    @Override
    public void displayMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Available Internships");
        System.out.println("2. Apply for Internship");
        System.out.println("3. View My Applications");
        System.out.println("4. Accept Internship Placement");
        System.out.println("5. Request Withdrawal");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
    }
    
    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s, Year=%d, Major=%s]", 
                           getUserId(), getName(), yearOfStudy, major);
    }
}