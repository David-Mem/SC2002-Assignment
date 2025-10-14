package edu.ntu.ccds.sc2002.boundary;

import edu.ntu.ccds.sc2002.control.*;
import edu.ntu.ccds.sc2002.entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Boundary class for company representative interface.
 * 
 * @author Group X
 * @version 1.0
 */
public class CompanyRepUI {
    
    private CompanyRepresentative companyRep;
    private DataController dataController;
    private AuthenticationController authController;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Constructs a CompanyRepUI for the specified representative.
     * 
     * @param companyRep Company representative user
     */
    public CompanyRepUI(CompanyRepresentative companyRep) {
        this.companyRep = companyRep;
        this.dataController = DataController.getInstance();
        this.authController = new AuthenticationController();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the company representative menu.
     */
    public void displayMenu() {
        boolean running = true;
        
        while (running) {
            companyRep.displayMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    createInternshipOpportunity();
                    break;
                case "2":
                    viewMyInternships();
                    break;
                case "3":
                    editInternshipOpportunity();
                    break;
                case "4":
                    deleteInternshipOpportunity();
                    break;
                case "5":
                    viewApplications();
                    break;
                case "6":
                    processApplication();
                    break;
                case "7":
                    toggleInternshipVisibility();
                    break;
                case "8":
                    changePassword();
                    break;
                case "9":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Handles creation of internship opportunity with input validation and retry logic.
     */
    private void createInternshipOpportunity() {
        System.out.println("\n=== CREATE INTERNSHIP OPPORTUNITY ===");
        
        if (companyRep.hasReachedInternshipLimit()) {
            System.out.println("You have reached the maximum of 5 internship opportunities.");
            return;
        }
        
        System.out.print("Enter Internship Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        
        // Level selection with retry
        InternshipLevel level = null;
        while (level == null) {
            System.out.println("Select Level (1-Basic, 2-Intermediate, 3-Advanced): ");
            System.out.print("Enter choice: ");
            String levelChoice = scanner.nextLine().trim();
            
            switch (levelChoice) {
                case "1": level = InternshipLevel.BASIC; break;
                case "2": level = InternshipLevel.INTERMEDIATE; break;
                case "3": level = InternshipLevel.ADVANCED; break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
        
        System.out.print("Enter Preferred Major (e.g., CSC, EEE, MAE, or ANY): ");
        String major = scanner.nextLine().trim().toUpperCase();
        
        // Opening date with retry
        LocalDate openingDate = null;
        while (openingDate == null) {
            try {
                System.out.print("Enter Opening Date (yyyy-MM-dd): ");
                openingDate = LocalDate.parse(scanner.nextLine().trim(), dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd (e.g., 2025-11-01)");
            }
        }
        
        // Closing date with retry
        LocalDate closingDate = null;
        while (closingDate == null) {
            try {
                System.out.print("Enter Closing Date (yyyy-MM-dd): ");
                closingDate = LocalDate.parse(scanner.nextLine().trim(), dateFormatter);
                
                if (closingDate.isBefore(openingDate)) {
                    System.out.println("Closing date cannot be before opening date. Please try again.");
                    closingDate = null; // Reset to retry
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd (e.g., 2025-12-31)");
            }
        }
        
        // Slots with retry
        int slots = 0;
        while (slots == 0) {
            try {
                System.out.print("Enter Number of Slots (1-10): ");
                slots = Integer.parseInt(scanner.nextLine().trim());
                
                if (slots < 1 || slots > 10) {
                    System.out.println("Slots must be between 1 and 10. Please try again.");
                    slots = 0; // Reset to retry
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a number between 1 and 10.");
            }
        }
        
        // Create internship
        String internshipId = dataController.generateInternshipId();
        Internship internship = new Internship(
            internshipId, title, description, level, major,
            openingDate, closingDate, companyRep.getCompanyName(),
            companyRep.getUserId(), slots
        );
        
        dataController.addInternship(internship);
        companyRep.addInternship(internshipId);
        
        System.out.println("\nInternship opportunity created successfully!");
        System.out.println("Internship ID: " + internshipId);
        System.out.println("Status: PENDING (awaiting Career Center Staff approval)");
    }


    /**
     * Displays internships created by this representative.
     */
    private void viewMyInternships() {
        System.out.println("\n=== MY INTERNSHIP OPPORTUNITIES ===");
        
        List<String> internshipIds = companyRep.getInternshipIds();
        
        if (internshipIds.isEmpty()) {
            System.out.println("You have not created any internships yet.");
            return;
        }
        
        for (String id : internshipIds) {
            Internship internship = dataController.getInternshipById(id);
            if (internship != null) {
                System.out.println("\n" + internship.getInternshipId() + ": " + internship.getTitle());
                System.out.println("   Level: " + internship.getLevel());
                System.out.println("   Major: " + internship.getPreferredMajor());
                System.out.println("   Status: " + internship.getStatus());
                System.out.println("   Visibility: " + (internship.isVisible() ? "ON" : "OFF"));
                System.out.println("   Slots: " + internship.getAvailableSlots() + "/" + internship.getTotalSlots());
                System.out.println("   Opening: " + internship.getOpeningDate());
                System.out.println("   Closing: " + internship.getClosingDate());
                System.out.println("   Applications: " + internship.getApplicationIds().size());
            }
        }
    }
    
    /**
     * Handles editing of internship opportunity.
     */
    private void editInternshipOpportunity() {
        System.out.println("\n=== EDIT INTERNSHIP OPPORTUNITY ===");
        
        System.out.print("Enter Internship ID to edit: ");
        String internshipId = scanner.nextLine().trim();
        
        Internship internship = dataController.getInternshipById(internshipId);
        
        if (internship == null || !internship.getCompanyRepId().equals(companyRep.getUserId())) {
            System.out.println("Internship not found or you don't have permission to edit it.");
            return;
        }
        
        if (internship.getStatus() == InternshipStatus.APPROVED || 
            internship.getStatus() == InternshipStatus.FILLED) {
            System.out.println("Cannot edit internship after it has been approved.");
            return;
        }
        
        System.out.println("\nCurrent Details:");
        System.out.println("Title: " + internship.getTitle());
        System.out.println("Description: " + internship.getDescription());
        
        System.out.print("\nEnter new title (or press Enter to keep current): ");
        String newTitle = scanner.nextLine().trim();
        if (!newTitle.isEmpty()) {
            internship.setTitle(newTitle);
        }
        
        System.out.print("Enter new description (or press Enter to keep current): ");
        String newDesc = scanner.nextLine().trim();
        if (!newDesc.isEmpty()) {
            internship.setDescription(newDesc);
        }
        
        System.out.println("\nInternship updated successfully!");
    }
    
    /**
     * Handles deletion of internship opportunity.
     */
    private void deleteInternshipOpportunity() {
        System.out.println("\n=== DELETE INTERNSHIP OPPORTUNITY ===");
        
        System.out.print("Enter Internship ID to delete: ");
        String internshipId = scanner.nextLine().trim();
        
        Internship internship = dataController.getInternshipById(internshipId);
        
        if (internship == null || !internship.getCompanyRepId().equals(companyRep.getUserId())) {
            System.out.println("Internship not found or you don't have permission to delete it.");
            return;
        }
        
        if (internship.getStatus() == InternshipStatus.APPROVED || 
            internship.getStatus() == InternshipStatus.FILLED) {
            System.out.println("Cannot delete internship after it has been approved.");
            return;
        }
        
        System.out.print("Are you sure you want to delete this internship? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("yes")) {
            dataController.removeInternship(internshipId);
            companyRep.removeInternship(internshipId);
            System.out.println("Internship deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    /**
     * Displays applications for internships.
     */
    private void viewApplications() {
        System.out.println("\n=== VIEW APPLICATIONS ===");
        
        System.out.print("Enter Internship ID: ");
        String internshipId = scanner.nextLine().trim();
        
        Internship internship = dataController.getInternshipById(internshipId);
        
        if (internship == null || !internship.getCompanyRepId().equals(companyRep.getUserId())) {
            System.out.println("Internship not found or you don't have permission to view it.");
            return;
        }
        
        List<Application> applications = dataController.getApplicationsByInternshipId(internshipId);
        
        if (applications.isEmpty()) {
            System.out.println("No applications for this internship.");
            return;
        }
        
        System.out.println("\nApplications for: " + internship.getTitle());
        for (Application app : applications) {
            Student student = (Student) dataController.getUserById(app.getStudentId());
            System.out.println("\n" + app.getApplicationId() + ":");
            System.out.println("   Student: " + (student != null ? student.getName() : "N/A"));
            System.out.println("   Student ID: " + app.getStudentId());
            if (student != null) {
                System.out.println("   Year: " + student.getYearOfStudy());
                System.out.println("   Major: " + student.getMajor());
            }
            System.out.println("   Status: " + app.getStatus());
            System.out.println("   Applied: " + app.getApplicationDate());
        }
    }
    
    /**
     * Handles processing (approve/reject) of applications.
     */
    private void processApplication() {
        System.out.println("\n=== PROCESS APPLICATION ===");
        
        System.out.print("Enter Application ID: ");
        String applicationId = scanner.nextLine().trim();
        
        Application application = dataController.getApplicationById(applicationId);
        
        if (application == null) {
            System.out.println("Application not found.");
            return;
        }
        
        Internship internship = dataController.getInternshipById(application.getInternshipId());
        
        if (internship == null || !internship.getCompanyRepId().equals(companyRep.getUserId())) {
            System.out.println("You don't have permission to process this application.");
            return;
        }
        
        if (application.getStatus() != ApplicationStatus.PENDING) {
            System.out.println("This application has already been processed.");
            return;
        }
        
        System.out.println("\nApplication Details:");
        Student student = (Student) dataController.getUserById(application.getStudentId());
        System.out.println("Student: " + (student != null ? student.getName() : "N/A"));
        System.out.println("Internship: " + internship.getTitle());
        
        System.out.println("\n1. Approve");
        System.out.println("2. Reject");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine().trim();
        
        if (choice.equals("1")) {
            application.setStatus(ApplicationStatus.SUCCESSFUL);
            System.out.println("\nApplication approved successfully!");
        } else if (choice.equals("2")) {
            application.setStatus(ApplicationStatus.UNSUCCESSFUL);
            System.out.println("\nApplication rejected.");
        } else {
            System.out.println("Invalid choice.");
        }
    }
    
    /**
     * Toggles visibility of an internship.
     */
    private void toggleInternshipVisibility() {
        System.out.println("\n=== TOGGLE INTERNSHIP VISIBILITY ===");
        
        System.out.print("Enter Internship ID: ");
        String internshipId = scanner.nextLine().trim();
        
        Internship internship = dataController.getInternshipById(internshipId);
        
        if (internship == null || !internship.getCompanyRepId().equals(companyRep.getUserId())) {
            System.out.println("Internship not found or you don't have permission to modify it.");
            return;
        }
        
        internship.setVisible(!internship.isVisible());
        System.out.println("\nVisibility toggled to: " + (internship.isVisible() ? "ON" : "OFF"));
    }
    
    /**
     * Handles password change.
     */
    private void changePassword() {
        System.out.println("\n=== CHANGE PASSWORD ===");
        
        System.out.print("Enter current password: ");
        String oldPassword = scanner.nextLine().trim();
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine().trim();
        
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine().trim();
        
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New passwords do not match.");
            return;
        }
        
        if (authController.changePassword(companyRep, oldPassword, newPassword)) {
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Current password is incorrect.");
        }
    }
}