package edu.ntu.ccds.sc2002.control;

import edu.ntu.ccds.sc2002.entity.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton controller class for managing all system data.
 * Demonstrates Singleton design pattern and centralized data management.
 * 
 * @author Group X
 * @version 1.0
 */
public class DataController {
    
    private static DataController instance;
    
    private Map<String, User> users;
    private Map<String, Internship> internships;
    private Map<String, Application> applications;
    private Map<String, WithdrawalRequest> withdrawalRequests;
    
    private int nextInternshipId = 1;
    private int nextApplicationId = 1;
    private int nextWithdrawalId = 1;
    
    /**
     * Private constructor for Singleton pattern.
     */
    private DataController() {
        users = new HashMap<>();
        internships = new HashMap<>();
        applications = new HashMap<>();
        withdrawalRequests = new HashMap<>();
    }
    
    /**
     * Gets the singleton instance of DataController.
     * 
     * @return DataController instance
     */
    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }
    
    // User Management
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }
    
    public User getUserById(String userId) {
        return users.get(userId);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    public List<Student> getAllStudents() {
        return users.values().stream()
                   .filter(u -> u instanceof Student)
                   .map(u -> (Student) u)
                   .collect(Collectors.toList());
    }
    
    public List<CompanyRepresentative> getAllCompanyReps() {
        return users.values().stream()
                   .filter(u -> u instanceof CompanyRepresentative)
                   .map(u -> (CompanyRepresentative) u)
                   .collect(Collectors.toList());
    }
    
    public List<CareerCenterStaff> getAllCareerStaff() {
        return users.values().stream()
                   .filter(u -> u instanceof CareerCenterStaff)
                   .map(u -> (CareerCenterStaff) u)
                   .collect(Collectors.toList());
    }
    
    // Internship Management
    public void addInternship(Internship internship) {
        internships.put(internship.getInternshipId(), internship);
    }
    
    public Internship getInternshipById(String internshipId) {
        return internships.get(internshipId);
    }
    
    public List<Internship> getAllInternships() {
        return new ArrayList<>(internships.values());
    }
    
    public boolean removeInternship(String internshipId) {
        return internships.remove(internshipId) != null;
    }
    
    public String generateInternshipId() {
        return "INT" + String.format("%04d", nextInternshipId++);
    }
    
    // Application Management
    public void addApplication(Application application) {
        applications.put(application.getApplicationId(), application);
    }
    
    public Application getApplicationById(String applicationId) {
        return applications.get(applicationId);
    }
    
    public List<Application> getAllApplications() {
        return new ArrayList<>(applications.values());
    }
    
    public List<Application> getApplicationsByStudentId(String studentId) {
        return applications.values().stream()
                          .filter(a -> a.getStudentId().equals(studentId))
                          .collect(Collectors.toList());
    }
    
    public List<Application> getApplicationsByInternshipId(String internshipId) {
        return applications.values().stream()
                          .filter(a -> a.getInternshipId().equals(internshipId))
                          .collect(Collectors.toList());
    }
    
    public boolean removeApplication(String applicationId) {
        return applications.remove(applicationId) != null;
    }
    
    public String generateApplicationId() {
        return "APP" + String.format("%04d", nextApplicationId++);
    }
    
    // Withdrawal Request Management
    public void addWithdrawalRequest(WithdrawalRequest request) {
        withdrawalRequests.put(request.getRequestId(), request);
    }
    
    public WithdrawalRequest getWithdrawalRequestById(String requestId) {
        return withdrawalRequests.get(requestId);
    }
    
    public List<WithdrawalRequest> getAllWithdrawalRequests() {
        return new ArrayList<>(withdrawalRequests.values());
    }
    
    public String generateWithdrawalRequestId() {
        return "WDR" + String.format("%04d", nextWithdrawalId++);
    }
    
    /**
     * Loads all data from files.
     * 
     * @throws IOException if file reading fails
     */
    public void loadAllData() throws IOException {
        // Try to load users from .dat file first, fall back to .txt if not found
        if (new File("data/users.dat").exists()) {
            loadUsersFromDatFile("data/users.dat");
        } else {
            loadUsersFromFile("data/users.txt");
        }
        
        loadInternshipsFromFile("data/internships.dat");
        loadApplicationsFromFile("data/applications.dat");
        loadWithdrawalRequestsFromFile("data/withdrawals.dat");
    }
    
    /**
     * Saves all data to files.
     * 
     * @throws IOException if file writing fails
     */
    public void saveAllData() throws IOException {
        new File("data").mkdirs();
        saveUsersToFile("data/users.dat");  // ADD THIS LINE
        saveInternshipsToFile("data/internships.dat");
        saveApplicationsToFile("data/applications.dat");
        saveWithdrawalRequestsToFile("data/withdrawals.dat");
    }
    
    private void loadUsersFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            initializeSampleUsers();
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;
                
                String userId = parts[0].trim();
                String name = parts[1].trim();
                String password = parts[2].trim();
                String type = parts[3].trim();
                
                if (type.equals("STUDENT") && parts.length >= 6) {
                    int year = Integer.parseInt(parts[4].trim());
                    String major = parts[5].trim();
                    addUser(new Student(userId, name, password, year, major));
                } else if (type.equals("COMPANY_REP") && parts.length >= 7) {
                    String company = parts[4].trim();
                    String dept = parts[5].trim();
                    String pos = parts[6].trim();
                    addUser(new CompanyRepresentative(userId, name, password, company, dept, pos));
                } else if (type.equals("CAREER_STAFF") && parts.length >= 5) {
                    String dept = parts[4].trim();
                    addUser(new CareerCenterStaff(userId, name, password, dept));
                }
            }
        }
    }
    
    private void initializeSampleUsers() {
        // Sample students
        addUser(new Student("U2345123F", "John Tan", "password", 3, "CSC"));
        addUser(new Student("U2345124G", "Mary Lim", "password", 2, "EEE"));
        addUser(new Student("U2345125H", "David Wong", "password", 4, "MAE"));
        
        // Sample career staff
        addUser(new CareerCenterStaff("staff001", "Admin User", "password", "Career Services"));
        
        System.out.println("Sample users initialized.");
    }

    /**
     * Saves users to binary file for persistence.
     * 
     * @param filename Path to save file
     * @throws IOException if file writing fails
     */
    private void saveUsersToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        }
    }

    /**
     * Loads users from binary file.
     * 
     * @param filename Path to load file
     */
    @SuppressWarnings("unchecked")
    private void loadUsersFromDatFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            users = new HashMap<>();
            // Fall back to text file if binary load fails
            try {
                loadUsersFromFile("data/users.txt");
            } catch (IOException ex) {
                initializeSampleUsers();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadInternshipsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            internships = (Map<String, Internship>) ois.readObject();
            nextInternshipId = internships.size() + 1;
        } catch (Exception e) {
            internships = new HashMap<>();
        }
    }
    
    private void saveInternshipsToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(internships);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadApplicationsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            applications = (Map<String, Application>) ois.readObject();
            nextApplicationId = applications.size() + 1;
        } catch (Exception e) {
            applications = new HashMap<>();
        }
    }
    
    private void saveApplicationsToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(applications);
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadWithdrawalRequestsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            withdrawalRequests = (Map<String, WithdrawalRequest>) ois.readObject();
            nextWithdrawalId = withdrawalRequests.size() + 1;
        } catch (Exception e) {
            withdrawalRequests = new HashMap<>();
        }
    }
    
    private void saveWithdrawalRequestsToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(withdrawalRequests);
        }
    }
}