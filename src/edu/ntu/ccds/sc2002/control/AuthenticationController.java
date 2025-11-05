package edu.ntu.ccds.sc2002.control;

import edu.ntu.ccds.sc2002.entity.*;

/**
 * Controller class for handling authentication operations.
 * Demonstrates the Controller pattern in MVC architecture.
 */
public class AuthenticationController {
    
    private DataController dataController;
    
    /**
     * Constructs an AuthenticationController.
     */
    public AuthenticationController() {
        this.dataController = DataController.getInstance();
    }
    
    /**
     * Authenticates a user with given credentials.
     */
    public User login(String userId, String password) {
        User user = dataController.getUserById(userId);
        
        if (user == null) {
            return null;
        }
        
        // Check if company rep is approved
        if (user instanceof CompanyRepresentative) {
            CompanyRepresentative rep = (CompanyRepresentative) user;
            if (!rep.isApproved()) {
                System.out.println("Your account is pending approval from Career Center Staff.");
                return null;
            }
        }
        
        // Verify password
        if (user.getPassword().equals(password)) {
            return user;
        }
        
        return null;
    }
    
    /**
     * Registers a new company representative.
     */
    public boolean registerCompanyRepresentative(String email, String name, String password,
                                                String companyName, String department, String position) {
        if (dataController.getUserById(email) != null) {
            return false;
        }
        
        CompanyRepresentative rep = new CompanyRepresentative(email, name, password, 
                                                             companyName, department, position);
        dataController.addUser(rep);
        return true;
    }
    
    /**
     * Changes a user's password.
     */
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (user.getPassword().equals(oldPassword)) {
            user.changePassword(newPassword);
            return true;
        }
        return false;
    }
    
    /**
     * Validates user ID format based on role.
     */
    public boolean validateUserIdFormat(String userId) {
        if (userId == null || userId.isEmpty()) {
            return false;
        }
        
        // Student: U followed by 7 digits and a letter
        if (userId.matches("U\\d{7}[A-Z]")) {
            return true;
        }
        
        // Company Rep: Email format
        if (userId.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return true;
        }
        
        // Career Staff: NTU account (flexible format)
        if (userId.contains("@") || userId.matches("[A-Za-z0-9]+")) {
            return true;
        }
        
        return false;
    }
}