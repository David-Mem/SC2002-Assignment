package edu.ntu.ccds.sc2002.control;

import edu.ntu.ccds.sc2002.entity.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton controller class for managing all system data.
 * Demonstrates Singleton design pattern and centralized data management.
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
     * @return single instance of DataController
     */
    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }
    
    // User Management
    /**
     * Adds the user.
     * @param user the user
     */
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }
    
    /**
     * Gets the user by id.
     * @param userId the user id
     * @return the user by id
     */
    public User getUserById(String userId) {
        return users.get(userId);
    }
    
    /**
     * Gets the all users.
     * @return the all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    /**
     * Gets the all students.
     * @return the all students
     */
    public List<Student> getAllStudents() {
        return users.values().stream()
                   .filter(u -> u instanceof Student)
                   .map(u -> (Student) u)
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets the all company reps.
     * @return the all company reps
     */
    public List<CompanyRepresentative> getAllCompanyReps() {
        return users.values().stream()
                   .filter(u -> u instanceof CompanyRepresentative)
                   .map(u -> (CompanyRepresentative) u)
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets the all career staff.
     * @return the all career staff
     */
    public List<CareerCenterStaff> getAllCareerStaff() {
        return users.values().stream()
                   .filter(u -> u instanceof CareerCenterStaff)
                   .map(u -> (CareerCenterStaff) u)
                   .collect(Collectors.toList());
    }
    
    // Internship Management
    /**
     * Adds the internship.
     * @param internship the internship
     */
    public void addInternship(Internship internship) {
        internships.put(internship.getInternshipId(), internship);
    }
    
    /**
     * Gets the internship by id.
     * @param internshipId the internship id
     * @return the internship by id
     */
    public Internship getInternshipById(String internshipId) {
        return internships.get(internshipId);
    }
    
    /**
     * Gets the all internships.
     * @return the all internships
     */
    public List<Internship> getAllInternships() {
        return new ArrayList<>(internships.values());
    }
    
    /**
     * Removes the internship.
     * @param internshipId the internship id
     * @return true, if successful
     */
    public boolean removeInternship(String internshipId) {
        return internships.remove(internshipId) != null;
    }
    
    /**
     * Generate internship id.
     * @return the string
     */
    public String generateInternshipId() {
        return "INT" + String.format("%04d", nextInternshipId++);
    }
    
    // Application Management
    /**
     * Adds the application.
     * @param application the application
     */
    public void addApplication(Application application) {
        applications.put(application.getApplicationId(), application);
    }
    
    /**
     * Gets the application by id.
     * @param applicationId the application id
     * @return the application by id
     */
    public Application getApplicationById(String applicationId) {
        return applications.get(applicationId);
    }
    
    /**
     * Gets the all applications.
     * @return the all applications
     */
    public List<Application> getAllApplications() {
        return new ArrayList<>(applications.values());
    }
    
    /**
     * Gets the applications by student id.
     * @param studentId the student id
     * @return the applications by student id
     */
    public List<Application> getApplicationsByStudentId(String studentId) {
        return applications.values().stream()
                          .filter(a -> a.getStudentId().equals(studentId))
                          .collect(Collectors.toList());
    }
    
    /**
     * Gets the applications by internship id.
     * @param internshipId the internship id
     * @return the applications by internship id
     */
    public List<Application> getApplicationsByInternshipId(String internshipId) {
        return applications.values().stream()
                          .filter(a -> a.getInternshipId().equals(internshipId))
                          .collect(Collectors.toList());
    }
    
    /**
     * Removes the application.
     * @param applicationId the application id
     * @return true, if successful
     */
    public boolean removeApplication(String applicationId) {
        return applications.remove(applicationId) != null;
    }
    
    /**
     * Generate application id.
     * @return the string
     */
    public String generateApplicationId() {
        return "APP" + String.format("%04d", nextApplicationId++);
    }
    
    // Withdrawal Request Management
    /**
     * Adds the withdrawal request.
     * @param request the request
     */
    public void addWithdrawalRequest(WithdrawalRequest request) {
        withdrawalRequests.put(request.getRequestId(), request);
    }
    
    /**
     * Gets the withdrawal request by id.
     * @param requestId the request id
     * @return the withdrawal request by id
     */
    public WithdrawalRequest getWithdrawalRequestById(String requestId) {
        return withdrawalRequests.get(requestId);
    }
    
    /**
     * Gets the all withdrawal requests.
     * @return the all withdrawal requests
     */
    public List<WithdrawalRequest> getAllWithdrawalRequests() {
        return new ArrayList<>(withdrawalRequests.values());
    }
    
    /**
     * Generate withdrawal request id.
     * @return the string
     */
    public String generateWithdrawalRequestId() {
        return "WDR" + String.format("%04d", nextWithdrawalId++);
    }
    
    /**
     * Loads all data from files.
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
     */
    private void saveUsersToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        }
    }

    /**
     * Loads users from binary file.
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