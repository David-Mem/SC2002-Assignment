package edu.ntu.ccds.sc2002.boundary;

import edu.ntu.ccds.sc2002.control.AuthenticationController;
import edu.ntu.ccds.sc2002.entity.*;
import java.util.Scanner;

/**
 * Boundary class for login interface.
 * Demonstrates the Boundary pattern in MVC architecture.
 * 
 * @author Group X
 * @version 1.0
 */
public class LoginUI {
    
    private AuthenticationController authController;
    private Scanner scanner;
    
    /**
     * Constructs a LoginUI with specified authentication controller.
     * 
     * @param authController Authentication controller for login operations
     */
    public LoginUI(AuthenticationController authController) {
        this.authController = authController;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the main login menu.
     */
    public void displayLoginMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== LOGIN MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Register as Company Representative");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    handleCompanyRegistration();
                    break;
                case "3":
                    System.out.println("Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Handles user login process.
     */
    private void handleLogin() {
        System.out.print("\nEnter User ID: ");
        String userId = scanner.nextLine().trim();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        User user = authController.login(userId, password);
        
        if (user == null) {
            System.out.println("Login failed. Invalid credentials or account not approved.");
            return;
        }
        
        System.out.println("\nLogin successful! Welcome, " + user.getName());
        
        // Redirect to appropriate menu based on role
        if (user instanceof Student) {
            StudentUI studentUI = new StudentUI((Student) user);
            studentUI.displayMenu();
        } else if (user instanceof CompanyRepresentative) {
            CompanyRepUI companyUI = new CompanyRepUI((CompanyRepresentative) user);
            companyUI.displayMenu();
        } else if (user instanceof CareerCenterStaff) {
            CareerStaffUI staffUI = new CareerStaffUI((CareerCenterStaff) user);
            staffUI.displayMenu();
        }
    }
    
    /**
     * Handles company representative registration.
     */
    private void handleCompanyRegistration() {
        System.out.println("\n=== COMPANY REPRESENTATIVE REGISTRATION ===");
        
        System.out.print("Enter Company Email: ");
        String email = scanner.nextLine().trim();
        
        if (!email.contains("@")) {
            System.out.println("Invalid email format.");
            return;
        }
        
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine().trim();
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        
        System.out.print("Enter Position: ");
        String position = scanner.nextLine().trim();
        
        boolean success = authController.registerCompanyRepresentative(
            email, name, password, companyName, department, position
        );
        
        if (success) {
            System.out.println("\nRegistration successful!");
            System.out.println("Your account is pending approval from Career Center Staff.");
            System.out.println("You will be able to login once approved.");
        } else {
            System.out.println("\nRegistration failed. User already exists.");
        }
    }
}