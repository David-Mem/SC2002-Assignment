# Internship Placement Management System

## Project Overview
A command-line interface (CLI) application for managing internship placements for students, company representatives, and career center staff at Nanyang Technological University.

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

## Additional Features
- ✅ Data Persistence: File based storage
- ✅ Email/ID/Account validation with regex
- ✅ Statistical report feature for the Career Center Staff
---

## Project Structure
```
SC2002-Assignment/
├── .gitatributes
├── .gitignore
├── .gitkeep
├── compile.bat
├── compile.sh
├── DESIGN_CONSIDERATIONS.md
├── PROJECT_SUMMARY.md
├── QUICK_START_GUIDE.md
├── README.md
├── run.bat
├── run.sh
├── testcases.md
├── data/
│   ├── .gitkeep
│   └── users.txt
├── docs/
│   └── (generated javadoc)
├── scripts/
│   └── setup.sh
└── src/
    └── edu/
        └── ntu/
            └── ccds/
                └── sc2002/
                    ├── Main.java
                    ├── boundary/
                    │   ├── CareerStaffUI.java
                    │   ├── CompanyRepUI.java
                    │   ├── LoginUI.java
                    │   └── StudentUI.java
                    ├── control/
                    │   ├── AuthenticationController.java
                    │   └── DataController.java
                    └── entity/
                        ├── Application.java
                        ├── ApplicationStatus.java
                        ├── CareerCenterStaff.java
                        ├── CompanyRepresentative.java
                        ├── Internship.java
                        ├── InternshipLevel.java
                        ├── InternshipStatus.java
                        ├── Student.java
                        ├── User.java
                        ├── UserRole.java
                        ├── WithdrawalRequest.java
                        └── WithdrawalStatus.java
```

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

### Entire flow of a successful application
1. Run the application
2. Register as Company Representative
3. Login as staff again and authorize the company representative
4. Login as the approved company representative
5. Create internship opportunities
6. Login as staff and approve internship opportunities (Option 2)
7. Login as student and apply for internships
8. Login as company representative and approve the application
9. Login as student and accept the intership
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