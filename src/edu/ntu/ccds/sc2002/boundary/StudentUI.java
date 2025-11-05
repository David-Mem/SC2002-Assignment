package edu.ntu.ccds.sc2002.boundary;

import edu.ntu.ccds.sc2002.control.*;
import edu.ntu.ccds.sc2002.entity.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Boundary class for student interface.
 * 
 * @author Group X
 * @version 1.0
 */
public class StudentUI {
    
    private Student student;
    private DataController dataController;
    private AuthenticationController authController;
    private Scanner scanner;
    
    /**
     * Constructs a StudentUI for the specified student.
     * 
     * @param student Student user
     */
    public StudentUI(Student student) {
        this.student = student;
        this.dataController = DataController.getInstance();
        this.authController = new AuthenticationController();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the student menu.
     */
    public void displayMenu() {
        boolean running = true;
        
        while (running) {
            student.displayMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    viewAvailableInternships();
                    break;
                case "2":
                    applyForInternship();
                    break;
                case "3":
                    viewMyApplications();
                    break;
                case "4":
                    acceptInternshipPlacement();
                    break;
                case "5":
                    requestWithdrawal();
                    break;
                case "6":
                    changePassword();
                    break;
                case "7":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    /**
     * Displays available internships for the student.
     */
    private void viewAvailableInternships() {
        System.out.println("\n=== AVAILABLE INTERNSHIPS ===");
        
        List<Internship> availableInternships = dataController.getAllInternships().stream()
            .filter(i -> i.isVisible() && 
                        i.isEligibleForStudent(student.getYearOfStudy(), student.getMajor()) &&
                        i.getStatus() == InternshipStatus.APPROVED)
            .sorted(Comparator.comparing(Internship::getTitle))
            .collect(Collectors.toList());
        
        if (availableInternships.isEmpty()) {
            System.out.println("No internships available for your profile.");
            return;
        }
        
        for (int i = 0; i < availableInternships.size(); i++) {
            Internship internship = availableInternships.get(i);
            System.out.println("\n" + (i + 1) + ". " + internship.getTitle());
            System.out.println("   Internship ID: " + internship.getInternshipId());
            System.out.println("   Company: " + internship.getCompanyName());
            System.out.println("   Level: " + internship.getLevel());
            System.out.println("   Major: " + internship.getPreferredMajor());
            System.out.println("   Available Slots: " + internship.getAvailableSlots());
            System.out.println("   Opening Date: " + internship.getOpeningDate());
            System.out.println("   Closing Date: " + internship.getClosingDate());
            System.out.println("   Description: " + internship.getDescription());
        }
    }
    
    /**
     * Handles internship application.
     */
    private void applyForInternship() {
        if (student.hasConfirmedInternship()) {
            System.out.println("\nYou have already confirmed an internship placement.");
            return;
        }
        
        if (student.hasReachedApplicationLimit()) {
            System.out.println("\nYou have reached the maximum of 3 applications.");
            return;
        }
        
        System.out.print("\nEnter Internship ID to apply: ");
        String internshipId = scanner.nextLine().trim();
        
        Internship internship = dataController.getInternshipById(internshipId);
        
        if (internship == null) {
            System.out.println("Internship not found.");
            return;
        }
        
        if (!internship.isAcceptingApplications()) {
            System.out.println("This internship is not currently accepting applications.");
            return;
        }
        
        if (!internship.isEligibleForStudent(student.getYearOfStudy(), student.getMajor())) {
            System.out.println("You are not eligible for this internship.");
            return;
        }
        
        // Check if already applied
        List<Application> existingApps = dataController.getApplicationsByStudentId(student.getUserId());
        boolean alreadyApplied = existingApps.stream()
            .anyMatch(a -> a.getInternshipId().equals(internshipId));
        
        if (alreadyApplied) {
            System.out.println("You have already applied for this internship.");
            return;
        }
        
        // Create application
        String appId = dataController.generateApplicationId();
        Application application = new Application(appId, student.getUserId(), internshipId);
        dataController.addApplication(application);
        
        student.addApplication(appId);
        internship.addApplication(appId);
        
        System.out.println("\nApplication submitted successfully!");
        System.out.println("Application ID: " + appId);
    }
    
    /**
     * Displays student's applications.
     */
    private void viewMyApplications() {
        System.out.println("\n=== MY APPLICATIONS ===");
        
        List<Application> myApplications = dataController.getApplicationsByStudentId(student.getUserId());
        
        if (myApplications.isEmpty()) {
            System.out.println("You have no applications.");
            return;
        }
        
        for (Application app : myApplications) {
            Internship internship = dataController.getInternshipById(app.getInternshipId());
            
            System.out.println("\nApplication ID: " + app.getApplicationId());
            System.out.println("Internship: " + (internship != null ? internship.getTitle() : "N/A"));
            System.out.println("Company: " + (internship != null ? internship.getCompanyName() : "N/A"));
            System.out.println("Status: " + app.getStatus());
            System.out.println("Application Date: " + app.getApplicationDate());
            if (app.isPlacementConfirmed()) {
                System.out.println("Placement: CONFIRMED");
            }
        }
    }
    
    /**
     * Handles accepting an internship placement.
     */
    private void acceptInternshipPlacement() {
        if (student.hasConfirmedInternship()) {
            System.out.println("\nYou have already confirmed an internship placement.");
            return;
        }
        
        // Find successful applications
        List<Application> successfulApps = dataController.getApplicationsByStudentId(student.getUserId())
            .stream()
            .filter(a -> a.getStatus() == ApplicationStatus.SUCCESSFUL)
            .collect(Collectors.toList());
        
        if (successfulApps.isEmpty()) {
            System.out.println("\nYou have no successful applications to accept.");
            return;
        }
        
        System.out.println("\n=== SUCCESSFUL APPLICATIONS ===");
        for (int i = 0; i < successfulApps.size(); i++) {
            Application app = successfulApps.get(i);
            Internship internship = dataController.getInternshipById(app.getInternshipId());
            System.out.println((i + 1) + ". " + app.getApplicationId() + " - " + 
                            (internship != null ? internship.getTitle() : "N/A"));
        }
        
        // Input with retry
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("\nEnter application number to accept (0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice == 0) {
                    System.out.println("Cancelled.");
                    return;
                }
                
                if (choice < 1 || choice > successfulApps.size()) {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + successfulApps.size());
                    continue;
                }
                
                validChoice = true; // Valid input
                
                Application selectedApp = successfulApps.get(choice - 1);
                Internship internship = dataController.getInternshipById(selectedApp.getInternshipId());
                
                // Confirm placement
                selectedApp.setPlacementConfirmed(true);
                student.setConfirmedInternshipId(internship.getInternshipId());
                internship.confirmStudent(student.getUserId());
                
                // Withdraw all other applications
                List<Application> otherApps = dataController.getApplicationsByStudentId(student.getUserId());
                for (Application app : otherApps) {
                    if (!app.getApplicationId().equals(selectedApp.getApplicationId())) {
                        app.setStatus(ApplicationStatus.WITHDRAWN);
                        student.removeApplication(app.getApplicationId());
                    }
                }
                
                System.out.println("\nInternship placement confirmed successfully!");
                System.out.println("All other applications have been withdrawn.");
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Handles withdrawal request submission.
     */
    private void requestWithdrawal() {
        System.out.println("\n=== REQUEST WITHDRAWAL ===");
        
        List<Application> myApps = dataController.getApplicationsByStudentId(student.getUserId());
        List<Application> activeApps = myApps.stream()
            .filter(a -> a.getStatus() == ApplicationStatus.PENDING || 
                        a.getStatus() == ApplicationStatus.SUCCESSFUL)
            .collect(Collectors.toList());
        
        if (activeApps.isEmpty()) {
            System.out.println("You have no active applications to withdraw.");
            return;
        }
        
        System.out.println("Active Applications:");
        for (int i = 0; i < activeApps.size(); i++) {
            Application app = activeApps.get(i);
            Internship internship = dataController.getInternshipById(app.getInternshipId());
            System.out.println((i + 1) + ". " + app.getApplicationId() + " - " + 
                            (internship != null ? internship.getTitle() : "N/A") +
                            " [" + app.getStatus() + "]");
        }
        
        // Input with retry
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("\nEnter application number to withdraw (0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice == 0) {
                    System.out.println("Cancelled.");
                    return;
                }
                
                if (choice < 1 || choice > activeApps.size()) {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + activeApps.size());
                    continue;
                }
                
                validChoice = true; // Valid input
                
                Application selectedApp = activeApps.get(choice - 1);
                
                System.out.print("Enter reason for withdrawal: ");
                String reason = scanner.nextLine().trim();
                
                if (reason.isEmpty()) {
                    System.out.println("Reason cannot be empty. Please provide a reason.");
                    validChoice = false;
                    continue;
                }
                
                String requestId = dataController.generateWithdrawalRequestId();
                boolean isAfterConfirmation = selectedApp.isPlacementConfirmed();
                
                WithdrawalRequest request = new WithdrawalRequest(
                    requestId, selectedApp.getApplicationId(), student.getUserId(), 
                    reason, isAfterConfirmation
                );
                
                dataController.addWithdrawalRequest(request);
                
                System.out.println("\nWithdrawal request submitted successfully!");
                System.out.println("Request ID: " + requestId);
                System.out.println("Pending approval from Career Center Staff.");
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
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
        
        if (authController.changePassword(student, oldPassword, newPassword)) {
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Current password is incorrect.");
        }
    }
}