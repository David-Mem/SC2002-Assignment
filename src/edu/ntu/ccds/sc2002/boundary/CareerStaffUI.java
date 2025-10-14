package edu.ntu.ccds.sc2002.boundary;

import edu.ntu.ccds.sc2002.control.*;
import edu.ntu.ccds.sc2002.entity.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Boundary class for career center staff interface.
 * 
 * @author Group X
 * @version 1.0
 */
public class CareerStaffUI {
    
    private CareerCenterStaff staff;
    private DataController dataController;
    private AuthenticationController authController;
    private Scanner scanner;
    
    /**
     * Constructs a CareerStaffUI for the specified staff member.
     * 
     * @param staff Career center staff user
     */
    public CareerStaffUI(CareerCenterStaff staff) {
        this.staff = staff;
        this.dataController = DataController.getInstance();
        this.authController = new AuthenticationController();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the career staff menu.
     */
    public void displayMenu() {
        boolean running = true;
        
        while (running) {
            staff.displayMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    authorizeCompanyReps();
                    break;
                case "2":
                    approveInternships();
                    break;
                case "3":
                    processWithdrawalRequests();
                    break;
                case "4":
                    generateReports();
                    break;
                case "5":
                    viewAllInternships();
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
     * Handles authorization of company representatives.
     */
    private void authorizeCompanyReps() {
        System.out.println("\n=== AUTHORIZE COMPANY REPRESENTATIVES ===");
        
        List<CompanyRepresentative> unapprovedReps = dataController.getAllCompanyReps().stream()
            .filter(rep -> !rep.isApproved())
            .collect(Collectors.toList());
        
        if (unapprovedReps.isEmpty()) {
            System.out.println("No pending company representative accounts.");
            return;
        }
        
        System.out.println("\nPending Accounts:");
        for (int i = 0; i < unapprovedReps.size(); i++) {
            CompanyRepresentative rep = unapprovedReps.get(i);
            System.out.println("\n" + (i + 1) + ".");
            System.out.println("   Email: " + rep.getUserId());
            System.out.println("   Name: " + rep.getName());
            System.out.println("   Company: " + rep.getCompanyName());
            System.out.println("   Department: " + rep.getDepartment());
            System.out.println("   Position: " + rep.getPosition());
        }
        
        // Input with retry
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("\nEnter account number to process (0 to cancel): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                if (choice == 0) {
                    System.out.println("Cancelled.");
                    return;
                }
                
                if (choice < 1 || choice > unapprovedReps.size()) {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + unapprovedReps.size());
                    continue;
                }
                
                validChoice = true;
                
                CompanyRepresentative selectedRep = unapprovedReps.get(choice - 1);
                
                // Action selection with retry
                boolean validAction = false;
                while (!validAction) {
                    System.out.println("\n1. Approve");
                    System.out.println("2. Reject");
                    System.out.print("Enter choice: ");
                    String action = scanner.nextLine().trim();
                    
                    if (action.equals("1")) {
                        selectedRep.setApproved(true);
                        System.out.println("\nAccount approved! Representative can now log in.");
                        validAction = true;
                    } else if (action.equals("2")) {
                        dataController.getAllUsers().remove(selectedRep);
                        System.out.println("\nAccount rejected and removed from system.");
                        validAction = true;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                    }
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Handles approval/rejection of internship opportunities.
     */
    private void approveInternships() {
        System.out.println("\n=== APPROVE INTERNSHIP OPPORTUNITIES ===");
        
        List<Internship> pendingInternships = dataController.getAllInternships().stream()
            .filter(i -> i.getStatus() == InternshipStatus.PENDING)
            .collect(Collectors.toList());
        
        if (pendingInternships.isEmpty()) {
            System.out.println("No pending internships to review.");
            return;
        }
        
        System.out.println("\nPending Internships:");
        for (int i = 0; i < pendingInternships.size(); i++) {
            Internship internship = pendingInternships.get(i);
            System.out.println("\n" + (i + 1) + ". " + internship.getTitle());
            System.out.println("   ID: " + internship.getInternshipId());
            System.out.println("   Company: " + internship.getCompanyName());
            System.out.println("   Level: " + internship.getLevel());
            System.out.println("   Major: " + internship.getPreferredMajor());
            System.out.println("   Slots: " + internship.getTotalSlots());
            System.out.println("   Opening: " + internship.getOpeningDate());
            System.out.println("   Closing: " + internship.getClosingDate());
            System.out.println("   Description: " + internship.getDescription());
        }
        
        System.out.print("\nEnter internship number to process (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return;
            
            if (choice < 1 || choice > pendingInternships.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            
            Internship selectedInternship = pendingInternships.get(choice - 1);
            
            System.out.println("\n1. Approve");
            System.out.println("2. Reject");
            System.out.print("Enter choice: ");
            String action = scanner.nextLine().trim();
            
            if (action.equals("1")) {
                selectedInternship.setStatus(InternshipStatus.APPROVED);
                System.out.println("\nInternship approved! Now visible to eligible students.");
            } else if (action.equals("2")) {
                selectedInternship.setStatus(InternshipStatus.REJECTED);
                System.out.println("\nInternship rejected.");
            } else {
                System.out.println("Invalid choice.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
    
    /**
     * Handles processing of withdrawal requests.
     */
    private void processWithdrawalRequests() {
        System.out.println("\n=== PROCESS WITHDRAWAL REQUESTS ===");
        
        List<WithdrawalRequest> pendingRequests = dataController.getAllWithdrawalRequests().stream()
            .filter(r -> r.getStatus() == WithdrawalStatus.PENDING)
            .collect(Collectors.toList());
        
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending withdrawal requests.");
            return;
        }
        
        System.out.println("\nPending Withdrawal Requests:");
        for (int i = 0; i < pendingRequests.size(); i++) {
            WithdrawalRequest request = pendingRequests.get(i);
            Application application = dataController.getApplicationById(request.getApplicationId());
            Student student = (Student) dataController.getUserById(request.getStudentId());
            Internship internship = null;
            
            if (application != null) {
                internship = dataController.getInternshipById(application.getInternshipId());
            }
            
            System.out.println("\n" + (i + 1) + ". Request ID: " + request.getRequestId());
            System.out.println("   Student: " + (student != null ? student.getName() : "N/A"));
            System.out.println("   Internship: " + (internship != null ? internship.getTitle() : "N/A"));
            System.out.println("   After Confirmation: " + (request.isAfterConfirmation() ? "YES" : "NO"));
            System.out.println("   Reason: " + request.getReason());
            System.out.println("   Requested: " + request.getRequestDate());
        }
        
        System.out.print("\nEnter request number to process (0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return;
            
            if (choice < 1 || choice > pendingRequests.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            
            WithdrawalRequest selectedRequest = pendingRequests.get(choice - 1);
            Application application = dataController.getApplicationById(selectedRequest.getApplicationId());
            
            System.out.println("\n1. Approve");
            System.out.println("2. Reject");
            System.out.print("Enter choice: ");
            String action = scanner.nextLine().trim();
            
            if (action.equals("1")) {
                selectedRequest.setStatus(WithdrawalStatus.APPROVED);
                
                if (application != null) {
                    application.setStatus(ApplicationStatus.WITHDRAWN);
                    
                    // If placement was confirmed, free up the slot
                    if (application.isPlacementConfirmed()) {
                        Internship internship = dataController.getInternshipById(application.getInternshipId());
                        if (internship != null) {
                            internship.removeConfirmedStudent(selectedRequest.getStudentId());
                            
                            Student student = (Student) dataController.getUserById(selectedRequest.getStudentId());
                            if (student != null) {
                                student.setConfirmedInternshipId(null);
                            }
                        }
                        application.setPlacementConfirmed(false);
                    }
                    
                    Student student = (Student) dataController.getUserById(selectedRequest.getStudentId());
                    if (student != null) {
                        student.removeApplication(application.getApplicationId());
                    }
                }
                
                System.out.println("\nWithdrawal request approved!");
                
            } else if (action.equals("2")) {
                selectedRequest.setStatus(WithdrawalStatus.REJECTED);
                System.out.println("\nWithdrawal request rejected.");
            } else {
                System.out.println("Invalid choice.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
    
    /**
     * Generates comprehensive reports on internship opportunities.
     */
    private void generateReports() {
        System.out.println("\n=== GENERATE REPORTS ===");
        System.out.println("1. All Internships Report");
        System.out.println("2. Filter by Status");
        System.out.println("3. Filter by Major");
        System.out.println("4. Filter by Level");
        System.out.println("5. Filter by Company");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine().trim();
        List<Internship> internships = dataController.getAllInternships();
        
        switch (choice) {
            case "1":
                displayInternshipReport(internships, "All Internships");
                break;
            case "2":
                filterByStatus(internships);
                break;
            case "3":
                filterByMajor(internships);
                break;
            case "4":
                filterByLevel(internships);
                break;
            case "5":
                filterByCompany(internships);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private void filterByStatus(List<Internship> internships) {
        System.out.println("\nSelect Status:");
        System.out.println("1. PENDING");
        System.out.println("2. APPROVED");
        System.out.println("3. REJECTED");
        System.out.println("4. FILLED");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine().trim();
        final InternshipStatus status;

        switch (choice) {
            case "1": status = InternshipStatus.PENDING; break;
            case "2": status = InternshipStatus.APPROVED; break;
            case "3": status = InternshipStatus.REJECTED; break;
            case "4": status = InternshipStatus.FILLED; break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        List<Internship> filtered = internships.stream()
            .filter(i -> i.getStatus() == status)
            .collect(Collectors.toList());
        
        displayInternshipReport(filtered, "Internships with Status: " + status);
    }
    
    private void filterByMajor(List<Internship> internships) {
        System.out.print("\nEnter Major (e.g., CSC, EEE, MAE, ANY): ");
        String major = scanner.nextLine().trim().toUpperCase();
        
        List<Internship> filtered = internships.stream()
            .filter(i -> i.getPreferredMajor().equalsIgnoreCase(major))
            .collect(Collectors.toList());
        
        displayInternshipReport(filtered, "Internships for Major: " + major);
    }
    
    private void filterByLevel(List<Internship> internships) {
        System.out.println("\nSelect Level:");
        System.out.println("1. BASIC");
        System.out.println("2. INTERMEDIATE");
        System.out.println("3. ADVANCED");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine().trim();
        final InternshipLevel level;
        
        switch (choice) {
            case "1": level = InternshipLevel.BASIC; break;
            case "2": level = InternshipLevel.INTERMEDIATE; break;
            case "3": level = InternshipLevel.ADVANCED; break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        List<Internship> filtered = internships.stream()
            .filter(i -> i.getLevel() == level)
            .collect(Collectors.toList());
        
        displayInternshipReport(filtered, "Internships with Level: " + level);
    }
    
    private void filterByCompany(List<Internship> internships) {
        System.out.print("\nEnter Company Name: ");
        String companyName = scanner.nextLine().trim();
        
        List<Internship> filtered = internships.stream()
            .filter(i -> i.getCompanyName().toLowerCase().contains(companyName.toLowerCase()))
            .collect(Collectors.toList());
        
        displayInternshipReport(filtered, "Internships for Company: " + companyName);
    }
    
    private void displayInternshipReport(List<Internship> internships, String title) {
        System.out.println("\n=== " + title + " ===");
        System.out.println("Total: " + internships.size());
        
        if (internships.isEmpty()) {
            System.out.println("No internships found.");
            return;
        }
        
        internships.sort(Comparator.comparing(Internship::getTitle));
        
        for (Internship internship : internships) {
            System.out.println("\n" + internship.getInternshipId() + ": " + internship.getTitle());
            System.out.println("   Company: " + internship.getCompanyName());
            System.out.println("   Level: " + internship.getLevel());
            System.out.println("   Major: " + internship.getPreferredMajor());
            System.out.println("   Status: " + internship.getStatus());
            System.out.println("   Visibility: " + (internship.isVisible() ? "ON" : "OFF"));
            System.out.println("   Slots: " + internship.getAvailableSlots() + "/" + internship.getTotalSlots());
            System.out.println("   Applications: " + internship.getApplicationIds().size());
            System.out.println("   Confirmed: " + internship.getConfirmedStudentIds().size());
        }
    }
    
    /**
     * Displays all internships in the system.
     */
    private void viewAllInternships() {
        System.out.println("\n=== ALL INTERNSHIPS ===");
        
        List<Internship> allInternships = dataController.getAllInternships();
        
        if (allInternships.isEmpty()) {
            System.out.println("No internships in the system.");
            return;
        }
        
        allInternships.sort(Comparator.comparing(Internship::getTitle));
        
        for (Internship internship : allInternships) {
            System.out.println("\n" + internship.getInternshipId() + ": " + internship.getTitle());
            System.out.println("   Company: " + internship.getCompanyName());
            System.out.println("   Level: " + internship.getLevel());
            System.out.println("   Status: " + internship.getStatus());
            System.out.println("   Slots: " + internship.getAvailableSlots() + "/" + internship.getTotalSlots());
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
        
        if (authController.changePassword(staff, oldPassword, newPassword)) {
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Current password is incorrect.");
        }
    }
}