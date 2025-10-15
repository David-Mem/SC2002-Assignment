# Internship Placement Management System

## Project Overview
A command-line interface (CLI) application for managing internship placements for students, company representatives, and career center staff at Nanyang Technological University.

## Team Information
- **Course**: SC/CE/CZ2002 - Object-Oriented Design & Programming
- **Semester**: 2025/2026 Semester 1
- **Group**: [Your Group Number]

---

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- Terminal/Command Prompt

### Run in 3 Steps

**Windows:**
```batch
compile.bat
run.bat
```

**Mac/Linux:**
```bash
chmod +x compile.sh run.sh
./compile.sh
./run.sh
```

**Default Login:**
- Student: `U2345123F` / `password`
- Staff: `staff001` / `password`

---

## Features

### For Students
- ✅ View available internship opportunities based on profile (year of study, major)
- ✅ Apply for up to 3 internships simultaneously
- ✅ View application status (Pending, Successful, Unsuccessful)
- ✅ Accept internship placement (only one)
- ✅ Request withdrawal of applications (before/after confirmation)
- ✅ Change password

### For Company Representatives
- ✅ Register and await approval from Career Center Staff
- ✅ Create up to 5 internship opportunities
- ✅ View and manage created internships
- ✅ Edit/delete internships (before approval only)
- ✅ Toggle internship visibility
- ✅ Review student applications with full details
- ✅ Approve/reject applications
- ✅ Change password

### For Career Center Staff
- ✅ Authorize company representative accounts
- ✅ Approve/reject internship opportunities
- ✅ Process student withdrawal requests (before/after placement)
- ✅ Generate comprehensive reports with filters
- ✅ View all internships in the system
- ✅ Change password

---

## System Requirements
- **Java**: JDK 8 or higher
- **IDE** (optional): IntelliJ IDEA, Eclipse, NetBeans, or VS Code
- **OS**: Windows, Mac, or Linux

---

## Project Structure
```
InternshipSystem/
├── src/
│   └── edu/ntu/ccds/sc2002/
│       ├── Main.java                          # Application entry point
│       ├── entity/                            # Entity classes (Model)
│       │   ├── User.java                      # Abstract base user class
│       │   ├── UserRole.java                  # User role enumeration
│       │   ├── Student.java                   # Student entity
│       │   ├── CompanyRepresentative.java     # Company rep entity
│       │   ├── CareerCenterStaff.java         # Career staff entity
│       │   ├── Internship.java                # Internship entity
│       │   ├── InternshipLevel.java           # Internship level enum
│       │   ├── InternshipStatus.java          # Internship status enum
│       │   ├── Application.java               # Application entity
│       │   ├── ApplicationStatus.java         # Application status enum
│       │   ├── WithdrawalRequest.java         # Withdrawal request entity
│       │   └── WithdrawalStatus.java          # Withdrawal status enum
│       ├── control/                           # Controller classes
│       │   ├── AuthenticationController.java  # Authentication logic
│       │   └── DataController.java            # Data management (Singleton)
│       └── boundary/                          # Boundary/UI classes (View)
│           ├── LoginUI.java                   # Login interface
│           ├── StudentUI.java                 # Student interface
│           ├── CompanyRepUI.java              # Company rep interface
│           └── CareerStaffUI.java             # Career staff interface
│
├── data/
│   ├── users.txt                              # Initial user data
│   ├── .gitkeep                               # Ensures directory is tracked
│   └── *.dat                                  # Serialized data (generated)
│
├── scripts/
│   └── setup.sh                               # Setup script
│
├── compile.sh / compile.bat                   # Compilation scripts
├── run.sh / run.bat                           # Execution scripts
├── .gitignore                                 # Git ignore rules
├── .gitattributes                             # Line ending configuration
│
├── README.md                                  # This file
├── QUICK_START_GUIDE.md                       # 5-minute setup guide
├── DESIGN_CONSIDERATIONS.md                   # Design documentation
├── TESTING_GUIDE.md                           # Test cases and guide
└── (other documentation files)
```

---

## Setup Instructions

### Method 1: Using Scripts (Recommended)

**Windows:**
```batch
# Compile
compile.bat

# Run
run.bat
```

**Mac/Linux:**
```bash
# Make scripts executable
chmod +x compile.sh run.sh

# Compile
./compile.sh

# Run
./run.sh
```

---

### Method 2: Manual Compilation

**Compile:**
```bash
# Create bin directory
mkdir bin

# Compile all source files
javac -d bin -sourcepath src src/edu/ntu/ccds/sc2002/**/*.java
```

**Run:**
```bash
java -cp bin edu.ntu.ccds.sc2002.Main
```

---

### Method 3: Using IDE

**IntelliJ IDEA:**
1. File → Open → Select project folder
2. Mark `src` as Sources Root (right-click → Mark Directory As → Sources Root)
3. Right-click `Main.java` → Run 'Main.main()'

**Eclipse:**
1. File → Open Projects from File System
2. Select project folder
3. Right-click `Main.java` → Run As → Java Application

**VS Code:**
1. Open project folder
2. Install Java Extension Pack
3. Press F5 or click Run button

---

## Default Login Credentials

### Students
| User ID | Password | Year | Major |
|---------|----------|------|-------|
| U2345123F | password | 3 | CSC |
| U2345124G | password | 2 | EEE |
| U2345125H | password | 4 | MAE |
| U2345126J | password | 1 | CSC |
| U2345127K | password | 3 | MAE |

### Career Center Staff
| User ID | Password | Department |
|---------|----------|------------|
| staff001 | password | Career Services |
| staff002 | password | Student Affairs |
| staff003 | password | Placement Office |

### Company Representatives
Register through the system (Option 2 from login menu). Requires staff approval before login.

---

## Usage Guide

### First Time Setup
1. Run the application
2. Login as Career Center Staff (`staff001` / `password`)
3. Go back to main menu (Option 7)
4. Register as Company Representative (Option 2)
5. Login as staff again and authorize the company representative (Option 1)
6. Login as the approved company representative
7. Create internship opportunities (Option 1)
8. Login as staff and approve internship opportunities (Option 2)
9. Login as student and apply for internships

---

### Sample User Journeys

#### Student Workflow:
1. ✅ Login with student credentials
2. ✅ View available internships (automatically filtered by profile)
3. ✅ Apply for internships (max 3 at a time)
4. ✅ Wait for company rep approval
5. ✅ Accept placement once approved (automatically withdraws other applications)
6. ✅ Request withdrawal if needed (requires staff approval)

#### Company Representative Workflow:
1. ✅ Register account via main menu
2. ✅ Wait for staff approval
3. ✅ Login after approval
4. ✅ Create internship opportunities (max 5, requires staff approval)
5. ✅ Wait for staff approval of internships
6. ✅ View and process student applications
7. ✅ Toggle internship visibility as needed

#### Career Staff Workflow:
1. ✅ Login with staff credentials
2. ✅ Authorize company representatives
3. ✅ Approve internship opportunities
4. ✅ Process withdrawal requests (before/after placement)
5. ✅ Generate reports with various filters

---

## OOP Concepts Implemented

### 1. Encapsulation
- All entity classes have private attributes with public getters/setters
- Data hiding and controlled access to internal state
- Defensive copying to prevent external modification

### 2. Inheritance
- `Student`, `CompanyRepresentative`, and `CareerCenterStaff` extend abstract `User` class
- Code reuse and hierarchical classification
- Natural "is-a" relationship modeling

### 3. Polymorphism
- Abstract method `displayMenu()` overridden in each user subclass
- Different implementations based on user type
- Runtime type determination

### 4. Abstraction
- Abstract `User` class defines common interface
- Concrete implementations provide specific behavior
- Hides complexity from callers

### 5. Design Patterns

#### Singleton Pattern
- `DataController` implements Singleton to ensure single data source
- Global point of access to application data
- Prevents data inconsistency

#### MVC Pattern
- **Model**: Entity classes (User, Internship, Application, etc.)
- **View**: Boundary classes (LoginUI, StudentUI, etc.)
- **Controller**: Control classes (AuthenticationController, DataController)

#### Template Method Pattern
- Abstract `displayMenu()` in User class
- Each subclass implements their own version

---

## Data Persistence

### File-based Storage:
- **users.txt**: Initial user data (text format)
- **users.dat**: User data (serialized, generated after first run)
- **internships.dat**: Serialized internship data
- **applications.dat**: Serialized application data
- **withdrawals.dat**: Serialized withdrawal request data

### Automatic Operations:
- ✅ Load on application start
- ✅ Save on application exit
- ✅ No manual save required

### Data Location:
All data files are in the `data/` directory.

**To reset data**: Delete all `.dat` files and restart application.

---

## Testing

### Test Coverage
- **40+ comprehensive test cases** covering all functionality
- Unit testing approach for individual components
- Integration testing for complete workflows
- Edge case handling and error scenarios

### Running Tests
Refer to [TESTING_GUIDE.md](TESTING_GUIDE.md) for:
- Detailed test cases
- Expected results
- Test execution procedures
- Demo script

---

## Documentation

### For Development:
- **DESIGN_CONSIDERATIONS.md**: Detailed explanation of design choices, OOP principles, and patterns
- **TESTING_GUIDE.md**: Comprehensive test cases and testing procedures
- **QUICK_START_GUIDE.md**: 5-minute setup and first-time usage

### Generated Documentation:
Generate Javadoc API documentation:

```bash
# Command line
cd src
javadoc -d ../docs -author -private -version \
  edu.ntu.ccds.sc2002 \
  edu.ntu.ccds.sc2002.entity \
  edu.ntu.ccds.sc2002.control \
  edu.ntu.ccds.sc2002.boundary

# View documentation
open ../docs/index.html
```

---

## Known Limitations
- ✅ CLI-based interface (no GUI)
- ✅ Single-threaded operation (suitable for assignment scope)
- ✅ File-based persistence (no database as per requirements)
- ✅ No encryption for stored passwords (demonstration only)
- ✅ Limited error recovery for corrupted data files

---

## Future Enhancements

### Easy Additions:
- Email validation with proper regex
- Password strength requirements
- Search internships by keyword
- Export reports to CSV
- Sort internships by multiple criteria

### Medium Additions:
- Edit user profiles
- Application deadline reminders
- Internship ratings and reviews
- Multiple majors per internship
- Resume/CV upload support

### Advanced Additions:
- GUI implementation using JavaFX
- Database integration (MySQL/PostgreSQL)
- Email notifications via SMTP
- Advanced analytics dashboard
- Recommendation system based on student profile
- Real-time notifications

---

## Troubleshooting

### "File not found" error
**Solution**: Ensure `data` directory exists and contains `users.txt`

### "Cannot load data" warning on first run
**Solution**: Normal behavior - system creates empty data structures

### Serialization errors
**Solution**: 
1. Delete all `.dat` files in `data/` folder
2. Restart application

### Compilation errors
**Solution**:
1. Verify Java version: `java -version` (should be 8+)
2. Check file structure matches documentation
3. Ensure no typos in package names

### Login fails for new company rep
**Solution**: Ensure staff has approved the account via "Authorize Company Representatives"

### Cannot see internships as student
**Solution**: 
1. Check visibility is ON
2. Check status is APPROVED
3. Check eligibility (major, year, level)

---

## Development Team

**Group Members**: [Fill in your team members]

**Contributions**: Refer to WBS (Work Breakdown Structure) if included in submission

---

## Support Resources

### Official Resources:
- Course materials and lecture notes on NTULearn
- Assignment specification document
- Lab sessions and TA office hours

### External Resources:
- [Java Documentation](https://docs.oracle.com/javase/8/docs/api/)
- [Visual Paradigm](https://www.visual-paradigm.com/) for UML diagrams
- [Git Documentation](https://git-scm.com/doc)

---

## License
This project is created for educational purposes as part of SC2002 coursework.

---

## Acknowledgments
- Course Instructor: Dr. Li Fang
- Teaching Assistants
- All team members for their contributions

---

**📝 Note**: Remember to test all functionalities thoroughly before your demonstration! Follow the test cases in TESTING_GUIDE.md.

**🎯 For Assignment Submission**: Ensure all required deliverables are included:
- ✅ Source code with Javadoc
- ✅ UML Class Diagram (with OOP annotations)
- ✅ UML Sequence Diagram (Company Rep workflow)
- ✅ Report (max 12 pages)
- ✅ Declaration of Original Work (signed)
- ✅ Demo video (max 15 minutes)
- ✅ GitHub repository link

---

**Last Updated**: October 2025  
**Version**: 1.0  
**Status**: Complete and Ready for Submission ✅