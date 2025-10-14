package edu.ntu.ccds.sc2002;

import edu.ntu.ccds.sc2002.boundary.LoginUI;
import edu.ntu.ccds.sc2002.control.AuthenticationController;
import edu.ntu.ccds.sc2002.control.DataController;

/**
 * Main entry point for the Internship Placement Management System.
 * Initializes the system and starts the login process.
 * 
 * @author Group X
 * @version 1.0
 */
public class Main {
    
    /**
     * Main method to start the application.
     * Loads initial data and displays login screen.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("  INTERNSHIP PLACEMENT MANAGEMENT SYSTEM");
        System.out.println("================================================\n");
        
        // Initialize data from files
        DataController dataController = DataController.getInstance();
        try {
            dataController.loadAllData();
            System.out.println("System initialized successfully!\n");
        } catch (Exception e) {
            System.out.println("Warning: Could not load data files. Starting with empty system.");
            System.out.println("Error: " + e.getMessage() + "\n");
        }
        
        // Start authentication process
        AuthenticationController authController = new AuthenticationController();
        LoginUI loginUI = new LoginUI(authController);
        loginUI.displayLoginMenu();
        
        // Save data before exit
        try {
            dataController.saveAllData();
            System.out.println("\nData saved successfully. Goodbye!");
        } catch (Exception e) {
            System.out.println("\nWarning: Could not save data: " + e.getMessage());
        }
    }
}