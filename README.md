# Internship Placement Management System

## Project Overview
A command-line interface (CLI) application for managing internship placements for students, company representatives, and career center staff at Nanyang Technological University.

## Team Information
- **Course**: SC/CE/CZ2002 - Object-Oriented Design & Programming
- **Semester**: 2025/2026 Semester 1
- **Group**: [Your Group Number]

## Features

### For Students
- View available internship opportunities based on profile (year of study, major)
- Apply for up to 3 internships simultaneously
- View application status (Pending, Successful, Unsuccessful)
- Accept internship placement (only one)
- Request withdrawal of applications

### For Company Representatives
- Register and await approval from Career Center Staff
- Create up to 5 internship opportunities
- View and manage created internships
- Toggle internship visibility
- Review student applications
- Approve/reject applications

### For Career Center Staff
- Authorize company representative accounts
- Approve/reject internship opportunities
- Process student withdrawal requests
- Generate comprehensive reports with filters

## System Requirements
- Java Development Kit (JDK) 8 or higher
- Java IDE (IntelliJ IDEA, Eclipse, or NetBeans) or text editor
- Command line/terminal access

## Project Structure
```
src/
└── edu/ntu/ccds/sc2002/
    ├── Main.java                          # Application entry point
    ├── entity/                            # Entity classes
    │   ├── User.java                      # Abstract base user class
    │   ├── UserRole.java                  # User role enumeration
    │   ├── Student.java                   # Student entity
    │   ├── CompanyRepresentative.java     # Company rep entity
    │   ├── CareerCenterStaff.java         # Career staff entity
    │   ├── Internship.java                # Internship entity
    │   ├── InternshipLevel.java           # Internship level enum
    │   ├── InternshipStatus.java          # Internship status enum
    │   ├── Application.java               # Application entity
    │   ├── ApplicationStatus.java         # Application status enum
    │   └── WithdrawalRequest.java         # Withdrawal request entity
    ├── control/                           # Controller classes
    │   ├── AuthenticationController.java  # Authentication logic
    │   └── DataController.java            # Data management (Singleton)
    └── boundary/                          # Boundary/UI classes
        ├── LoginUI.java                   # Login interface
        ├── StudentUI.java                 # Student interface
        ├── CompanyRepUI.java              # Company rep interface
        └── CareerStaffUI.java             # Career staff interface

data/
├── users.txt                              # User data file
├── internships.dat                        # Serialized internships
├── applications.dat                       # Serialized applications
└── withdrawals.dat                        # Serialized withdrawals
```

## Setup Instructions

### 1. Extract the Project
Extract all files to your desired directory.

### 2. Create Data Directory
Create a `data` folder in your project root:
```bash
mkdir data
```

### 3. Create Sample Users File
Create `data/users.txt` with the following content:
```
U2345123F|John Tan|password|STUDENT|3|CSC
U2345124G|Mary Lim|password|STUDENT|2|EEE
U2345125H|David Wong|password|STUDENT|4|MAE
U2345126J|Alice Chen|password|STUDENT|1|CSC
U2345127K|Bob Lee|password|STUDENT|3|MAE
staff001|Admin User|password|CAREER_STAFF|Career Services
staff002|Jane Smith|password|CAREER_STAFF|Student Affairs
```

### 4. Compile the Project

#### Using Command Line:
```bash
cd src
javac edu/ntu/ccds/sc2002/*.java edu/ntu/ccds/sc2002/**/*.java
```

#### Using IDE:
1. Import the project into your IDE
2. Set the source folder to `src`
3. Build the project

### 5. Run the Application

#### Using Command Line:
```bash
java edu.ntu.ccds.sc2002.Main
```

#### Using IDE:
Run the `Main.java` file

## Default Login Credentials

### Students
- **ID**: U2345123F, **Password**: password (Year 3, CSC)
- **ID**: U2345124G, **Password**: password (Year 2, EEE)
- **ID**: U2345125H, **Password**: password (Year 4, MAE)

### Career Center Staff
- **ID**: staff001, **Password**: password
- **ID**: staff002, **Password**: password

### Company Representatives
Register through the system (requires staff approval)

## Usage Guide

### First Time Setup
1. Run the application
2. Login as Career Center Staff (staff001/password)
3. Register as Company Representative (Option 2 from login menu)
4. As staff, authorize the company representative
5. Login as the approved company representative
6. Create internship opportunities
7. As staff, approve the internship opportunities
8. Login as student and apply for internships

### Sample User Journey

#### Student Workflow:
1. Login with student credentials
2. View available internships (filtered by profile)
3. Apply for internships (max 3)
4. Wait for company rep approval
5. Accept placement once approved
6. Request withdrawal if needed

#### Company Representative Workflow:
1. Register account
2. Wait for staff approval
3. Login after approval
4. Create internship opportunities
5. Wait for staff approval of internships
6. View and process student applications
7. Toggle internship visibility as needed

#### Career Staff Workflow:
1. Login with staff credentials
2. Authorize company representatives
3. Approve internship opportunities
4. Process withdrawal requests
5. Generate reports

## OOP Concepts Implemented

### 1. Encapsulation
- All entity classes have private attributes with public getters/setters
- Data hiding and controlled access to internal state

### 2. Inheritance
- `Student`, `CompanyRepresentative`, and `CareerCenterStaff` extend abstract `User` class
- Code reuse and hierarchical classification

### 3. Polymorphism
- Abstract method `displayMenu()` overridden in each user subclass
- Different implementations based on user type

### 4. Abstraction
- Abstract `User` class defines common interface
- Concrete implementations provide specific behavior

### 5. Design Patterns

#### Singleton Pattern
- `DataController` implements Singleton to ensure single data source
- Global point of access to application data

#### MVC Pattern
- **Model**: Entity classes (User, Internship, Application, etc.)
- **View**: Boundary classes (LoginUI, StudentUI, etc.)
- **Controller**: Control classes (AuthenticationController, DataController)

## Data Persistence
- User data loaded from text file (`users.txt`)
- Internships, applications, and withdrawals saved using Java serialization
- Automatic save on application exit
- Automatic load on application start

## Testing

### Test Cases
Refer to Appendix A in the assignment document for comprehensive test cases including:
- Valid/invalid user login
- Password change functionality
- Company representative registration and approval
- Internship creation and approval
- Application eligibility and submission
- Placement confirmation
- Withdrawal requests

### Running Tests
1. Follow the user journeys described above
2. Verify each feature works as expected
3. Test edge cases (e.g., maximum applications, filled internships)
4. Test data persistence (restart application and verify data retained)

## Generating Javadoc

### Using Command Line:
```bash
cd src
javadoc -d ../docs -author -private -version edu.ntu.ccds.sc2002 edu.ntu.ccds.sc2002.entity edu.ntu.ccds.sc2002.control edu.ntu.ccds.sc2002.boundary
```

### Using IDE:
1. In IntelliJ: Tools → Generate JavaDoc
2. In Eclipse: Project → Generate Javadoc
3. Specify output directory as `docs`

## Known Limitations
- CLI-based interface (no GUI)
- Single-threaded operation
- File-based persistence (no database)
- Limited error recovery for corrupted data files

## Future Enhancements
- GUI implementation using JavaFX
- Database integration (MySQL/PostgreSQL)
- Email notifications for application status
- Advanced search and filtering
- Multi-language support
- User profile pictures
- Internship recommendations based on student profile

## Troubleshooting

### "File not found" error
- Ensure `data` directory exists
- Check that `users.txt` is in the correct location

### "Cannot load data" warning
- Normal on first run (creates empty data structures)
- If persistent, check file permissions

### Serialization errors
- Delete `.dat` files in `data/` folder to reset
- Restart application

## Support
For issues or questions, contact:
- Course Instructor: Dr. Li Fang
- Teaching Assistant: [Your TA's name]

## License
This project is created for educational purposes as part of SC2002 coursework.

---

**Note**: Remember to test all functionalities thoroughly before your demonstration!