# Design Considerations and OOP Principles

## Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Design Patterns](#design-patterns)
3. [OOP Principles](#oop-principles)
4. [Class Design](#class-design)
5. [Design Trade-offs](#design-trade-offs)
6. [Extensibility and Maintainability](#extensibility-and-maintainability)

---

## Architecture Overview

### MVC (Model-View-Controller) Pattern

The application follows the MVC architectural pattern to separate concerns:

**Model (Entity Package)**
- Represents the data and business logic
- Classes: `User`, `Student`, `CompanyRepresentative`, `CareerCenterStaff`, `Internship`, `Application`, `WithdrawalRequest`
- Independent of UI and control logic

**View (Boundary Package)**
- Handles user interface and input/output
- Classes: `LoginUI`, `StudentUI`, `CompanyRepUI`, `CareerStaffUI`
- Interacts with controllers to process user actions

**Controller (Control Package)**
- Manages application logic and data flow
- Classes: `AuthenticationController`, `DataController`
- Mediates between Model and View

### Benefits of MVC
- **Separation of Concerns**: Each layer has distinct responsibilities
- **Maintainability**: Changes to UI don't affect business logic
- **Testability**: Each component can be tested independently
- **Reusability**: Model classes can be reused across different views

---

## Design Patterns

### 1. Singleton Pattern

**Implementation**: `DataController` class

**Purpose**: 
- Ensures only one instance of DataController exists
- Provides global access point to application data
- Prevents data inconsistency from multiple instances

**Code Example**:
```java
public class DataController {
    private static DataController instance;
    
    private DataController() {
        // Private constructor prevents instantiation
    }
    
    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }
        return instance;
    }
}
```

**Trade-offs**:
- ✅ **Pros**: Single source of truth, easy access, controlled instantiation
- ❌ **Cons**: Global state can make testing harder, potential for tight coupling

**Alternative Considered**: Dependency Injection
- Would require passing DataController to all classes
- More complex but better for testing
- Singleton chosen for simplicity in CLI application

---

### 2. Factory Method Pattern (Implicit)

**Implementation**: ID generation methods in `DataController`

**Purpose**:
- Centralized creation of unique IDs
- Ensures consistency in ID format

**Code Example**:
```java
public String generateInternshipId() {
    return "INT" + String.format("%04d", nextInternshipId++);
}

public String generateApplicationId() {
    return "APP" + String.format("%04d", nextApplicationId++);
}

public String generateWithdrawalRequestId() {
    return "WDR" + String.format("%04d", nextWithdrawalId++);
}
```

**Benefits**:
- Consistent ID format across the system
- Easy to modify format in one place
- Thread-safe ID generation

---

### 3. Template Method Pattern

**Implementation**: Abstract `User` class with `displayMenu()` method

**Purpose**:
- Define skeleton of menu display algorithm
- Let subclasses override specific steps

**Code Example**:
```java
public abstract class User {
    // Template method
    public abstract void displayMenu();
}

public class Student extends User {
    @Override
    public void displayMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Available Internships");
        // ... student-specific options
    }
}

public class CompanyRepresentative extends User {
    @Override
    public void displayMenu() {
        System.out.println("\n=== Company Representative Menu ===");
        System.out.println("1. Create Internship Opportunity");
        // ... company rep-specific options
    }
}
```

**Benefits**:
- Eliminates code duplication
- Easy to add new user types
- Enforces consistent interface

---

## OOP Principles

### 1. Encapsulation

**Implementation**:
- All entity classes have private attributes
- Public getters and setters control access
- Validation logic within setters and methods

**Example**:
```java
public class Student extends User {
    private int yearOfStudy;
    private String major;
    private List<String> applicationIds;
    
    public int getYearOfStudy() {
        return yearOfStudy;
    }
    
    public boolean addApplication(String applicationId) {
        if (applicationIds.size() >= 3) {
            return false; // Enforce business rule
        }
        if (!applicationIds.contains(applicationId)) {
            applicationIds.add(applicationId);
            return true;
        }
        return false;
    }
    
    // Returns defensive copy, not internal reference
    public List<String> getApplicationIds() {
        return new ArrayList<>(applicationIds);
    }
}
```

**Benefits**:
- Data hiding protects internal state
- Controlled modification prevents invalid states
- Easy to add validation without affecting clients
- Defensive copying prevents external modification

---

### 2. Inheritance

**Implementation**: User hierarchy

**Structure**:
```
User (abstract)
├── Student
├── CompanyRepresentative
└── CareerCenterStaff
```

**Benefits**:
- Code reuse (common attributes in User)
- Hierarchical classification
- Polymorphic behavior
- "Is-a" relationship modeling

**Example**:
```java
public abstract class User implements Serializable {
    private String userId;
    private String name;
    private String password;
    private UserRole role;
    
    // Common methods for all users
    public abstract void displayMenu();
}

public class Student extends User {
    private int yearOfStudy;
    private String major;
    // Student-specific attributes and methods
}
```

**Design Decision**: Why inheritance over composition here?
- Students, Company Reps, and Staff ARE users (not HAS-A user)
- Share common identity attributes (userId, name, password)
- Need polymorphic behavior for menu display
- Natural hierarchical relationship

---

### 3. Polymorphism

**Implementation**: Method overriding and runtime type determination

**Example 1 - Method Overriding**:
```java
User user = authController.login(userId, password);

// Different behavior based on actual type
if (user instanceof Student) {
    StudentUI ui = new StudentUI((Student) user);
    ui.displayMenu(); // Calls Student's displayMenu()
} else if (user instanceof CompanyRepresentative) {
    CompanyRepUI ui = new CompanyRepUI((CompanyRepresentative) user);
    ui.displayMenu(); // Calls CompanyRep's displayMenu()
}
```

**Example 2 - Collection of Different Types**:
```java
List<User> allUsers = dataController.getAllUsers();
// Can contain Students, CompanyReps, and CareerStaff
for (User user : allUsers) {
    user.displayMenu(); // Calls appropriate version
}
```

**Benefits**:
- Single interface, multiple implementations
- Runtime type determination
- Extensible without modifying existing code
- Flexible and maintainable

---

### 4. Abstraction

**Implementation**: Abstract User class

**Purpose**:
- Hide complex implementation details
- Define common interface for all users
- Force subclasses to implement specific behavior

**Example**:
```java
public abstract class User implements Serializable {
    // Common attributes and methods
    
    // Abstract method - must be implemented by subclasses
    public abstract void displayMenu();
}
```

**Benefits**:
- Clients work with User interface, not concrete types
- Implementation details hidden
- Clear contract for subclasses
- Reduces complexity for callers

---

### 5. Composition

**Implementation**: Entity relationships

**Examples**:
- `Student` has List of `Application` IDs (not inheritance)
- `Internship` has List of `Application` IDs
- `User` has `UserRole` enum
- "Has-a" relationship instead of "is-a"

**Why Composition Over Inheritance Here?**:
```java
public class Student extends User {
    // Composition - Student HAS applications
    private List<String> applicationIds;
    
    // Not inheritance - Student is not an Application
}
```

**Benefits**:
- More flexible than inheritance
- Easier to modify relationships
- Better encapsulation
- Avoids deep inheritance hierarchies

---

## Class Design

### Entity Classes

#### User Hierarchy

**Design Decision**: Abstract base class with concrete subclasses

**Rationale**:
- Common attributes (userId, name, password) shared across all users
- Role-specific attributes in subclasses
- Polymorphic behavior through method overriding
- Type safety at compile time

**Alternatives Considered**:

1. **Single User class with role enum**
   - ❌ Would require conditional logic everywhere
   - ❌ Less type-safe
   - ❌ Harder to extend with role-specific behavior
   
2. **Interface-based design**
   - ❌ Would duplicate common code
   - ✅ More flexible for multiple inheritance
   - ❌ Doesn't capture "is-a" relationship well

**Chosen Approach**: Abstract base class
- ✅ Best balance of code reuse and flexibility
- ✅ Clear type hierarchy
- ✅ Enforces common interface

---

#### Internship Class

**Design Decision**: Self-contained entity with embedded business logic

**Key Methods**:
- `isAcceptingApplications()`: Encapsulates complex eligibility logic
- `isEligibleForStudent()`: Validates student eligibility
- `confirmStudent()`: Manages slot allocation

**Example**:
```java
public boolean isAcceptingApplications() {
    LocalDate today = LocalDate.now();
    return status == InternshipStatus.APPROVED 
           && isVisible
           && !today.isBefore(openingDate)
           && !today.isAfter(closingDate)
           && availableSlots > 0;
}
```

**Rationale**:
- Business logic stays with data (high cohesion)
- Easy to maintain and test
- Reduces coupling with controller
- Single Responsibility Principle

---

#### Application Class

**Design Decision**: Separate entity linking Student and Internship

**Rationale**:
- Many-to-many relationship requires junction entity
- Tracks application-specific data (status, date, confirmation)
- Supports application lifecycle management
- Allows querying applications by student or internship

**Alternative Considered**: Store applications directly in Student/Internship
- ❌ Would create tight coupling
- ❌ Harder to query and manage
- ❌ Violates separation of concerns

---

### Enumerations

**Design Decision**: Use enums for fixed sets of values

**Examples**:
```java
public enum UserRole { STUDENT, COMPANY_REP, CAREER_STAFF }
public enum InternshipLevel { BASIC, INTERMEDIATE, ADVANCED }
public enum InternshipStatus { PENDING, APPROVED, REJECTED, FILLED }
public enum ApplicationStatus { PENDING, SUCCESSFUL, UNSUCCESSFUL, WITHDRAWN }
public enum WithdrawalStatus { PENDING, APPROVED, REJECTED }
```

**Benefits**:
- Type safety at compile time
- Prevents invalid values
- Self-documenting code
- IDE auto-completion support
- Easy to add new values

---

## Design Trade-offs

### 1. File-based vs Database Storage

**Decision**: File-based (serialization + text files)

**Rationale**:
- ✅ Assignment requirement (no database)
- ✅ Simpler setup and deployment
- ✅ Demonstrates Java I/O and serialization
- ✅ Suitable for assignment scope
- ❌ Limited query capabilities
- ❌ No concurrent access support
- ❌ No ACID guarantees

**Future Enhancement**: 
Could use Repository pattern to abstract storage, making database migration easier without changing business logic.

---

### 2. CLI vs GUI

**Decision**: Command-line interface

**Rationale**:
- ✅ Assignment requirement
- ✅ Focus on OOP, not UI framework
- ✅ Easier to demonstrate logic flow
- ✅ Faster development
- ❌ Less user-friendly
- ❌ Limited presentation options

**Architecture Advantage**: 
MVC structure makes GUI migration straightforward - only need to replace Boundary classes.

---

### 3. Eager vs Lazy Loading

**Decision**: Eager loading (load all data at startup)

**Rationale**:
- ✅ Simple implementation
- ✅ Fast access during runtime
- ✅ Suitable for small dataset
- ✅ Predictable memory usage
- ❌ Higher startup time
- ❌ All data in memory

**Alternative**: Lazy loading
- Better for large datasets
- More complex implementation
- Not needed for assignment scope

---

### 4. String IDs vs Object References

**Decision**: Store IDs as Strings, not object references

**Example**:
```java
public class Student extends User {
    private List<String> applicationIds; // Store IDs, not Application objects
}
```

**Rationale**:
- ✅ Easier serialization (no circular references)
- ✅ Avoids complex object graphs
- ✅ More flexible for persistence
- ✅ Prevents accidental modification
- ❌ Requires lookup operations
- ❌ Slightly less efficient

**Why Not Direct References?**:
```java
// This would cause serialization issues:
private List<Application> applications; // Each Application references Student
// Creates circular dependency during serialization
```

---

### 5. Input Validation Location

**Decision**: Validation in both UI and Entity layers

**Implementation**:
```java
// UI Layer - Immediate feedback
private void acceptInternshipPlacement() {
    boolean validChoice = false;
    while (!validChoice) {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < 1 || choice > successfulApps.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            validChoice = true;
            // Process...
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}

// Entity Layer - Data integrity
public boolean addApplication(String applicationId) {
    if (applicationIds.size() >= 3) {
        return false; // Enforce business rule
    }
    // Add...
}
```

**Rationale**:
- UI validation provides immediate user feedback
- Entity validation ensures data integrity
- Defense in depth approach
- UI can change without breaking entity rules

---

## Coupling and Cohesion

### Low Coupling Achieved Through:

1. **MVC Separation**: 
   - Views don't directly access entities
   - Changes to UI don't affect business logic

2. **Controller Mediation**: 
   - All data access through DataController
   - Entities don't know about UI

3. **Dependency on Abstractions**:
   - UI classes depend on User interface
   - Not tied to specific implementations

4. **String IDs vs Object References**:
   - Loose coupling between entities
   - No circular dependencies

**Example of Low Coupling**:
```java
public class StudentUI {
    private Student student; // Depends on concrete Student (ok - it's for students)
    private DataController dataController; // Depends on Singleton (ok - single source)
    private AuthenticationController authController; // Depends on controller (ok - its job)
    
    // Does NOT directly create or manipulate Internship/Application objects
    // Goes through controllers instead
}
```

---

### High Cohesion Achieved Through:

1. **Single Responsibility**: Each class has one clear purpose
   - `Student` - Manages student data and behavior
   - `StudentUI` - Handles student interface
   - `AuthenticationController` - Handles authentication only
   - `DataController` - Manages all data operations

2. **Focused Methods**: Methods perform specific tasks
   ```java
   public boolean isAcceptingApplications() {
       // Only checks if internship accepts applications
   }
   
   public boolean isEligibleForStudent(int yearOfStudy, String major) {
       // Only checks eligibility
   }
   ```

3. **Logical Grouping**: Related functionality in same class
   - All internship operations in `Internship` class
   - All authentication in `AuthenticationController`

4. **Package Organization**: Related classes in same package
   - `entity` - All domain models
   - `control` - All controllers
   - `boundary` - All UI classes

---

## Extensibility and Maintainability

### Easy to Extend:

#### Adding New User Role:
```java
// 1. Create new entity class
public class Employer extends User {
    private String industry;
    
    @Override
    public void displayMenu() {
        // Employer-specific menu
    }
}

// 2. Add to UserRole enum
public enum UserRole {
    STUDENT, COMPANY_REP, CAREER_STAFF, EMPLOYER
}

// 3. Create UI class
public class EmployerUI { /* ... */ }

// 4. Update LoginUI
if (user instanceof Employer) {
    new EmployerUI((Employer) user).displayMenu();
}
```

**No changes needed** to existing Student, CompanyRep, or CareerStaff classes!

---

#### Adding New Internship Field:
```java
// 1. Add to Internship class
private String location;

public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }

// 2. Update constructor
public Internship(..., String location) {
    // ...
    this.location = location;
}

// 3. Update UI to display/input
// No changes to other classes needed!
```

---

#### Adding New Report Type:
```java
// In CareerStaffUI, just add new method:
private void filterByLocation(List<Internship> internships) {
    System.out.print("Enter location: ");
    String location = scanner.nextLine().trim();
    
    List<Internship> filtered = internships.stream()
        .filter(i -> i.getLocation().equalsIgnoreCase(location))
        .collect(Collectors.toList());
    
    displayInternshipReport(filtered, "Internships at: " + location);
}

// Add to menu - done!
```

---

### Easy to Maintain:

#### Separation of Concerns:
- UI changes don't affect business logic
- Data structure changes isolated to entity classes
- Control logic centralized in controllers

#### Clear Dependencies:
```
Main
 └─> LoginUI
      └─> AuthenticationController
           └─> DataController
                └─> Entity classes
```

#### Documentation:
- Comprehensive Javadoc comments on all public methods
- Clear method names (`isEligibleForStudent` vs `check`)
- Package-level documentation

#### Error Handling:
```java
// Consistent pattern throughout
try {
    dataController.loadAllData();
    System.out.println("System initialized successfully!");
} catch (Exception e) {
    System.out.println("Warning: Could not load data files.");
    System.out.println("Error: " + e.getMessage());
}
```

---

## Security Considerations

### Current Implementation:
- ✅ Password verification before sensitive operations
- ✅ Role-based access control
- ✅ Approval workflow for company representatives
- ✅ Input validation to prevent invalid states
- ❌ Passwords stored in plain text (demonstration only)
- ❌ No session management
- ❌ No encryption for serialized data

### Production Recommendations:
1. **Password Security**:
   ```java
   // Use BCrypt or similar
   import org.springframework.security.crypto.bcrypt.BCrypt;
   
   public void setPassword(String plainPassword) {
       this.password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
   }
   
   public boolean verifyPassword(String plainPassword) {
       return BCrypt.checkpw(plainPassword, this.password);
   }
   ```

2. **Session Management**: Implement token-based authentication

3. **Audit Logging**: Log all sensitive operations

4. **Input Sanitization**: Prevent injection attacks

5. **Rate Limiting**: Prevent brute force attacks

---

## Performance Considerations

### Current Implementation:
- In-memory data structures (HashMap for O(1) lookups)
- Linear search for filtering (`Stream.filter()`)
- Suitable for small to medium datasets (< 10,000 records)

### Optimization Strategies for Scale:

1. **Indexing**:
   ```java
   // Add indexes for common queries
   private Map<String, List<Application>> applicationsByStudent;
   private Map<String, List<Application>> applicationsByInternship;
   ```

2. **Caching**: Cache frequently accessed data

3. **Pagination**: Don't load all internships at once
   ```java
   public List<Internship> getInternships(int page, int pageSize) {
       return internships.values().stream()
           .skip(page * pageSize)
           .limit(pageSize)
           .collect(Collectors.toList());
   }
   ```

4. **Lazy Loading**: Load related data only when needed

5. **Database**: Use proper database with query optimization

---

## Testing Strategy

### Unit Testing Approach:
```java
@Test
public void testStudentApplicationLimit() {
    Student student = new Student("U1234567A", "Test", "pass", 3, "CSC");
    
    // Add 3 applications
    assertTrue(student.addApplication("APP1"));
    assertTrue(student.addApplication("APP2"));
    assertTrue(student.addApplication("APP3"));
    
    // 4th should fail
    assertFalse(student.addApplication("APP4"));
    assertEquals(3, student.getApplicationIds().size());
}

@Test
public void testInternshipEligibility() {
    Internship internship = new Internship(
        "INT001", "Software Intern", "...", 
        InternshipLevel.INTERMEDIATE, "CSC",
        LocalDate.now(), LocalDate.now().plusDays(30),
        "Tech Corp", "rep@tech.com", 5
    );
    
    // Year 1 student should not be eligible for Intermediate
    assertFalse(internship.isEligibleForStudent(1, "CSC"));
    
    // Year 3 student should be eligible
    assertTrue(internship.isEligibleForStudent(3, "CSC"));
    
    // Wrong major should not be eligible
    assertFalse(internship.isEligibleForStudent(3, "EEE"));
}
```

### Integration Testing:
- Test complete workflows (apply → approve → accept)
- Verify data persistence (save → restart → load)
- Test role-based access control

### System Testing:
- Follow test cases in TESTING_GUIDE.md
- Test all user journeys
- Verify business rules

---

## Reflection on Design Patterns

### Why These Patterns?

**Singleton for DataController**:
- ✅ Perfect fit for centralized data management
- ✅ Prevents data inconsistency
- ✅ Simple to implement and use
- ✅ Natural single source of truth

**MVC for Architecture**:
- ✅ Industry standard for UI applications
- ✅ Clear separation enables parallel development
- ✅ Makes future GUI migration easier
- ✅ Well-understood by team members

**Template Method for User Menu**:
- ✅ Natural fit for role-based behavior
- ✅ Reduces code duplication
- ✅ Easy to extend with new roles
- ✅ Enforces consistent interface

---

### Patterns Not Used (and Why):

**Observer Pattern**:
- ❌ Not needed: No real-time updates required
- ❌ CLI nature makes it unnecessary
- ✅ Could be useful for GUI version with live updates

**Strategy Pattern**:
- ❌ Could be used for different search/filter algorithms
- ❌ Current implementation sufficient for requirements
- ✅ Good candidate for future enhancement

**Factory Pattern** (formal):
- ❌ Implicit use in ID generation sufficient
- ❌ Could formalize for user creation
- ❌ Would add complexity without clear benefit for this scope

**Decorator Pattern**:
- ❌ Not needed: No dynamic behavior modification required
- ✅ Could be used for adding logging/validation layers

---

## Lessons Learned

### What Worked Well:

1. **MVC Architecture**: Clear separation made parallel development easy

2. **Singleton DataController**: Simplified data management significantly

3. **Enum Types**: Type safety prevented many bugs

4. **Defensive Copying**: Returning `new ArrayList<>(list)` prevented external modification

5. **Input Validation with Retry**: Improved user experience significantly

### What We'd Do Differently:

1. **Start with more comprehensive error handling** from the beginning

2. **Implement automated tests** alongside development

3. **Use a proper logging framework** instead of System.out for errors

4. **Consider Repository pattern** for better abstraction of data access

5. **Design for testability** from the start (dependency injection)

---

## Conclusion

This design prioritizes:
1. **Clarity**: Easy to understand and navigate
2. **Maintainability**: Easy to modify and extend
3. **Correctness**: Meets all requirements
4. **Educational Value**: Demonstrates OOP principles clearly

The trade-offs made favor **simplicity and demonstration of core OOP concepts** over enterprise-level features, which is appropriate for an academic project.

The design successfully demonstrates:
- ✅ All four OOP principles (Encapsulation, Inheritance, Polymorphism, Abstraction)
- ✅ Multiple design patterns (Singleton, MVC, Template Method)
- ✅ SOLID principles
- ✅ Separation of concerns
- ✅ High cohesion and low coupling

**Key Takeaway**: Good design at the beginning saves significant time during implementation and maintenance. The principles and patterns learned here are directly applicable to real-world software development.