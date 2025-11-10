package edu.ntu.ccds.sc2002.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a company representative user in the system.
 * Extends User class demonstrating inheritance.
 */
public class CompanyRepresentative extends User {
    private static final long serialVersionUID = 1L;
    
    private String companyName;
    private String department;
    private String position;
    private boolean isApproved;
    private List<String> internshipIds;
    
    /**
     * Constructs a CompanyRepresentative with specified details.
     */
    public CompanyRepresentative(String userId, String name, String password, 
                                String companyName, String department, String position) {
        super(userId, name, password, UserRole.COMPANY_REP);
        this.companyName = companyName;
        this.department = department;
        this.position = position;
        this.isApproved = false;
        this.internshipIds = new ArrayList<>();
    }
    
    /**
     * Gets the company name.
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * Sets the company name.
     * @param companyName the new company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * Gets the department.
     * @return the department
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * Sets the department.
     * @param department the new department
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * Gets the position.
     * @return the position
     */
    public String getPosition() {
        return position;
    }
    
    /**
     * Sets the position.
     * @param position the new position
     */
    public void setPosition(String position) {
        this.position = position;
    }
    
    /**
     * Checks if the representative is approved.
     * @return true, if is approved
     */
    public boolean isApproved() {
        return isApproved;
    }
    
    /**
     * Sets the approval status.
     * @param approved the new approved
     */
    public void setApproved(boolean approved) {
        this.isApproved = approved;
    }
    
    /**
     * Gets the list of internship IDs created by this representative.
     * @return the internship ids
     */
    public List<String> getInternshipIds() {
        return new ArrayList<>(internshipIds);
    }
    
    /**
     * Adds an internship ID to the representative's list.
     * @param internshipId the internship id
     * @return true, if successful
     */
    public boolean addInternship(String internshipId) {
        if (internshipIds.size() >= 5) {
            return false;
        }
        if (!internshipIds.contains(internshipId)) {
            internshipIds.add(internshipId);
            return true;
        }
        return false;
    }
    
    /**
     * Removes an internship ID from the representative's list.
     * @param internshipId the internship id
     * @return true, if successful
     */
    public boolean removeInternship(String internshipId) {
        return internshipIds.remove(internshipId);
    }
    
    /**
     * Checks if representative has reached maximum internship creation limit.
     */
    public boolean hasReachedInternshipLimit() {
        return internshipIds.size() >= 5;
    }
    
    @Override
    public void displayMenu() {
        System.out.println("\n=== Company Representative Menu ===");
        System.out.println("1. Create Internship Opportunity");
        System.out.println("2. View My Internship Opportunities");
        System.out.println("3. Edit Internship Opportunity");
        System.out.println("4. Delete Internship Opportunity");
        System.out.println("5. View Applications");
        System.out.println("6. Process Application (Approve/Reject)");
        System.out.println("7. Toggle Internship Visibility");
        System.out.println("8. Change Password");
        System.out.println("9. Logout");
    }
    
    @Override
    public String toString() {
        return String.format("CompanyRep[ID=%s, Name=%s, Company=%s, Position=%s, Approved=%s]", 
                           getUserId(), getName(), companyName, position, isApproved);
    }
}