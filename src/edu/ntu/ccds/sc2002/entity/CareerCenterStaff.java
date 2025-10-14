package edu.ntu.ccds.sc2002.entity;

/**
 * Represents a career center staff user in the system.
 * Extends User class demonstrating inheritance.
 * 
 * @author Group X
 * @version 1.0
 */
public class CareerCenterStaff extends User {
    private static final long serialVersionUID = 1L;
    
    private String staffDepartment;
    
    /**
     * Constructs a CareerCenterStaff with specified details.
     * 
     * @param userId NTU account ID
     * @param name Staff's name
     * @param password Staff's password
     * @param staffDepartment Department where staff works
     */
    public CareerCenterStaff(String userId, String name, String password, String staffDepartment) {
        super(userId, name, password, UserRole.CAREER_STAFF);
        this.staffDepartment = staffDepartment;
    }
    
    /**
     * Gets the staff department.
     * 
     * @return Staff department
     */
    public String getStaffDepartment() {
        return staffDepartment;
    }
    
    /**
     * Sets the staff department.
     * 
     * @param staffDepartment Staff department
     */
    public void setStaffDepartment(String staffDepartment) {
        this.staffDepartment = staffDepartment;
    }
    
    @Override
    public void displayMenu() {
        System.out.println("\n=== Career Center Staff Menu ===");
        System.out.println("1. Authorize Company Representatives");
        System.out.println("2. Approve/Reject Internship Opportunities");
        System.out.println("3. Process Withdrawal Requests");
        System.out.println("4. Generate Reports");
        System.out.println("5. View All Internships");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
    }
    
    @Override
    public String toString() {
        return String.format("CareerStaff[ID=%s, Name=%s, Department=%s]", 
                           getUserId(), getName(), staffDepartment);
    }
}