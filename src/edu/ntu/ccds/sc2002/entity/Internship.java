package edu.ntu.ccds.sc2002.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an internship opportunity in the system.
 * Implements Serializable for data persistence.
 */
public class Internship implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String internshipId;
    private String title;
    private String description;
    private InternshipLevel level;
    private String preferredMajor;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private InternshipStatus status;
    private String companyName;
    private String companyRepId;
    private int totalSlots;
    private int availableSlots;
    private boolean isVisible;
    private List<String> applicationIds;
    private List<String> confirmedStudentIds;
    
    /**
     * Constructs an Internship with specified details.
     */
    public Internship(String internshipId, String title, String description, 
                     InternshipLevel level, String preferredMajor,
                     LocalDate openingDate, LocalDate closingDate,
                     String companyName, String companyRepId, int totalSlots) {
        this.internshipId = internshipId;
        this.title = title;
        this.description = description;
        this.level = level;
        this.preferredMajor = preferredMajor;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.status = InternshipStatus.PENDING;
        this.companyName = companyName;
        this.companyRepId = companyRepId;
        this.totalSlots = Math.min(totalSlots, 10);
        this.availableSlots = this.totalSlots;
        this.isVisible = true;
        this.applicationIds = new ArrayList<>();
        this.confirmedStudentIds = new ArrayList<>();
    }
    
    // Getters
    /**
     * Gets the internship ID.
     * @return the internship ID
     */
    public String getInternshipId() { return internshipId; }
    /**
     * Gets the title.
     * @return the title
     */
    public String getTitle() { return title; }
    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() { return description; }
    /**
     * Gets the level.
     * @return the level
     */
    public InternshipLevel getLevel() { return level; }
    /**
     * Gets the preferred major.
     * @return the preferred major
     */
    public String getPreferredMajor() { return preferredMajor; }
    /**
     * Gets the opening date.
     * @return the opening date
     */
    public LocalDate getOpeningDate() { return openingDate; }
    /**
     * Gets the closing date.
     * @return the closing date
     */
    public LocalDate getClosingDate() { return closingDate; }
    /**
     * Gets the status.
     * @return the status
     */
    public InternshipStatus getStatus() { return status; }
    /**
     * Gets the company name.
     * @return the company name
     */
    public String getCompanyName() { return companyName; }
    /**
     * Gets the company rep ID.
     * @return the company rep ID
     */
    public String getCompanyRepId() { return companyRepId; }
    /**
     * Gets the total slots.
     * @return the total slots
     */
    public int getTotalSlots() { return totalSlots; }
    /**
     * Gets the available slots.
     * @return the available slots
     */
    public int getAvailableSlots() { return availableSlots; }
    /**
     * Checks if is visible.
     * @return true, if is visible
     */
    public boolean isVisible() { return isVisible; }
    /**
     * Gets the application ids.
     * @return the application ids
     */
    public List<String> getApplicationIds() { return new ArrayList<>(applicationIds); }
    /**
     * Gets the confirmed student ids.
     * @return the confirmed student ids
     */
    public List<String> getConfirmedStudentIds() { return new ArrayList<>(confirmedStudentIds); }
    
    // Setters
    /**
     * Sets the title.
     * @param title the new title
     */
    public void setTitle(String title) { this.title = title; }
    /**
     * Sets the description.
     * @param description the new description
     */
    public void setDescription(String description) { this.description = description; }
    /**
     * Sets the level.
     * @param level the new level
     */
    public void setLevel(InternshipLevel level) { this.level = level; }
    /**
     * Sets the preferred major.
     * @param preferredMajor the new preferred major
     */
    public void setPreferredMajor(String preferredMajor) { this.preferredMajor = preferredMajor; }
    /**
     * Sets the opening date.
     * @param openingDate the new opening date
     */
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }
    /**
     * Sets the closing date.
     * @param closingDate the new closing date
     */
    public void setClosingDate(LocalDate closingDate) { this.closingDate = closingDate; }
    /**
     * Sets the status.
     * @param status the new status
     */
    public void setStatus(InternshipStatus status) { this.status = status; }
    /**
     * Sets the visible.
     * @param visible the new visible
     */
    public void setVisible(boolean visible) { this.isVisible = visible; }
    
    /**
     * Adds an application ID to this internship.
     */
    public void addApplication(String applicationId) {
        if (!applicationIds.contains(applicationId)) {
            applicationIds.add(applicationId);
        }
    }
    
    /**
     * Removes an application ID from this internship.
     */
    public void removeApplication(String applicationId) {
        applicationIds.remove(applicationId);
    }
    
    /**
     * Confirms a student for this internship and decreases available slots.
     */
    public boolean confirmStudent(String studentId) {
        if (availableSlots > 0 && !confirmedStudentIds.contains(studentId)) {
            confirmedStudentIds.add(studentId);
            availableSlots--;
            if (availableSlots == 0) {
                status = InternshipStatus.FILLED;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Removes a confirmed student and increases available slots.
     */
    public boolean removeConfirmedStudent(String studentId) {
        if (confirmedStudentIds.remove(studentId)) {
            availableSlots++;
            if (status == InternshipStatus.FILLED) {
                status = InternshipStatus.APPROVED;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Checks if internship is currently accepting applications.
     */
    public boolean isAcceptingApplications() {
        LocalDate today = LocalDate.now();
        return status == InternshipStatus.APPROVED 
               && isVisible
               && !today.isBefore(openingDate)
               && !today.isAfter(closingDate)
               && availableSlots > 0;
    }
    
    /**
     * Checks if a student is eligible to apply based on year and level.
     */
    public boolean isEligibleForStudent(int yearOfStudy, String major) {
        // Check major match
        if (!preferredMajor.equalsIgnoreCase(major) && !preferredMajor.equalsIgnoreCase("ANY")) {
            return false;
        }
        
        // Check level eligibility based on year
        if (yearOfStudy <= 2 && level != InternshipLevel.BASIC) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("Internship[ID=%s, Title=%s, Company=%s, Level=%s, Status=%s, Slots=%d/%d]",
                           internshipId, title, companyName, level, status, availableSlots, totalSlots);
    }
}