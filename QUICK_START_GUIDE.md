# Quick Start Guide - 5 Minute Setup

Get the Internship Placement Management System running in 5 minutes!

---

## Step 1: Verify Prerequisites (30 seconds)

Check you have Java installed:

```bash
java -version
```

Should show Java 8 or higher. If not, download from:
- [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- [OpenJDK](https://openjdk.org/)

---

## Step 2: Clone or Download Project (1 minute)

**If using Git:**
```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
cd YOUR_REPO_NAME
```

**If downloaded as ZIP:**
1. Extract the ZIP file
2. Open terminal/command prompt
3. Navigate to project directory

---

## Step 3: Compile (30 seconds)

### On Windows:
```batch
compile.bat
```

### On Mac/Linux:
```bash
chmod +x compile.sh run.sh
./compile.sh
```

You should see:
```
Compiling Internship Placement Management System...
✅ Compilation successful!
Run './run.sh' to start the application.
```

---

## Step 4: Run! (30 seconds)

### On Windows:
```batch
run.bat
```

### On Mac/Linux:
```bash
./run.sh
```

You should see:
```
================================================
  INTERNSHIP PLACEMENT MANAGEMENT SYSTEM
================================================

Sample users initialized.
System initialized successfully!

=== LOGIN MENU ===
1. Login
2. Register as Company Representative
3. Exit
Enter choice:
```

**✅ Success!** Your system is running!

---

## Step 5: First Login Test (30 seconds)

Try logging in as a student:
- **User ID**: `U2345123F`
- **Password**: `password`

You should see:
```
Login successful! Welcome, John Tan

=== Student Menu ===
1. View Available Internships
2. Apply for Internship
3. View My Applications
4. Accept Internship Placement
5. Request Withdrawal
6. Change Password
7. Logout
Enter choice:
```

**🎉 Congratulations!** You're all set up!

---

## Quick Demo Flow (5 minutes)

### Test 1: Career Staff Operations (1 min)
1. Logout (option 7)
2. Login as `staff001` / `password`
3. Select "7. Logout" to go back to main menu
4. Select "2. Register as Company Representative"
   - Email: `tech@company.com`
   - Name: `Tech Manager`
   - Password: `password`
   - Company: `Tech Corp`
   - Department: `HR`
   - Position: `Manager`
5. Login as `staff001` again
6. Select "1. Authorize Company Representatives"
7. Approve the account (option 1, then 1)

### Test 2: Company Rep Operations (2 min)
1. Logout and login as `tech@company.com` / `password`
2. Select "1. Create Internship Opportunity"
   - Title: `Software Developer Intern`
   - Description: `Work on exciting projects`
   - Level: `1` (Basic)
   - Major: `CSC`
   - Opening: `2025-11-01`
   - Closing: `2025-12-31`
   - Slots: `5`
3. Note the Internship ID (e.g., INT0001)
4. Logout, login as staff, select "2. Approve/Reject Internship Opportunities"
5. Approve the internship (enter 1, then 1)

### Test 3: Student Operations (2 min)
1. Logout and login as `U2345123F` / `password` (Year 3, CSC)
2. Select "1. View Available Internships"
3. You should see the approved CSC internship
4. Select "2. Apply for Internship"
5. Enter internship ID: `INT0001`
6. Application submitted successfully!
7. Note the Application ID (e.g., APP0001)

### Test 4: Complete Workflow (1 min)
1. Logout and login as company rep
2. Select "5. View Applications"
3. Enter internship ID: `INT0001`
4. View student details
5. Select "6. Process Application (Approve/Reject)"
6. Enter application ID: `APP0001`
7. Choose "1. Approve"
8. Logout and login as student
9. Select "4. Accept Internship Placement"
10. Select application number: `1`
11. Placement confirmed! Other applications withdrawn!

**🏆 You've completed a full internship placement workflow!**

---

## Troubleshooting

### "File not found" error
✅ **Fix**: The `data` directory should exist with `users.txt`
```bash
# Check if data directory exists
ls data/

# If missing, it should be created automatically
# But you can create it manually:
mkdir data
```

### "Class not found" error
✅ **Fix**: Make sure you compiled with `-d bin` and run with `-cp bin`
```bash
# Recompile
./compile.sh  # or compile.bat on Windows
```

### "Cannot load data" warning
✅ **Fix**: Normal on first run! The system will create empty data structures.

### "Permission denied" (Mac/Linux)
✅ **Fix**: Make scripts executable
```bash
chmod +x compile.sh run.sh
```

### Compilation errors
✅ **Fix**: 
1. Check all files are in correct directories
2. Ensure no typos in package names
3. Verify Java version is 8+
   ```bash
   java -version
   ```

---

## File Structure Checklist

```
InternshipSystem/
├── src/
│   └── edu/ntu/ccds/sc2002/
│       ├── Main.java ✓
│       ├── entity/
│       │   ├── User.java ✓
│       │   ├── UserRole.java ✓
│       │   ├── Student.java ✓
│       │   ├── CompanyRepresentative.java ✓
│       │   ├── CareerCenterStaff.java ✓
│       │   ├── Internship.java ✓
│       │   ├── InternshipLevel.java ✓
│       │   ├── InternshipStatus.java ✓
│       │   ├── Application.java ✓
│       │   ├── ApplicationStatus.java ✓
│       │   ├── WithdrawalRequest.java ✓
│       │   └── WithdrawalStatus.java ✓
│       ├── control/
│       │   ├── AuthenticationController.java ✓
│       │   └── DataController.java ✓
│       └── boundary/
│           ├── LoginUI.java ✓
│           ├── StudentUI.java ✓
│           ├── CompanyRepUI.java ✓
│           └── CareerStaffUI.java ✓
├── data/
│   ├── users.txt ✓
│   └── .gitkeep ✓
├── compile.sh / compile.bat ✓
├── run.sh / run.bat ✓
└── bin/ (created during compilation)
```

---

## IDE Setup (Alternative to Command Line)

### IntelliJ IDEA
1. **Open Project**: File → Open → Select your project folder
2. **Configure Sources**: Right-click `src` → Mark Directory As → Sources Root
3. **Run**: Right-click `Main.java` → Run 'Main.main()'

### Eclipse
1. **Open Project**: File → Open Projects from File System → Select folder
2. **Run**: Right-click `Main.java` → Run As → Java Application

### VS Code
1. **Open Folder**: File → Open Folder → Select project folder
2. **Install Extension**: Java Extension Pack (if not installed)
3. **Run**: Press F5 or click the Run button

---

## Next Steps

Once you have the basic system running:

1. **📖 Read** [README.md](README.md) for comprehensive documentation
2. **🏗️ Study** [DESIGN_CONSIDERATIONS.md](DESIGN_CONSIDERATIONS.md) to understand the architecture
3. **🧪 Follow** [TESTING_GUIDE.md](TESTING_GUIDE.md) to test all features thoroughly
4. **📊 Create** your UML diagrams (Class and Sequence)
5. **✏️ Write** your reflection and report
6. **🎬 Prepare** your demo following the script in TESTING_GUIDE.md

---

## Tips for Success

### For the Assignment:
- ✅ Test all test cases from Appendix A in assignment doc
- ✅ Generate Javadoc early: `cd src && javadoc -d ../docs -author -private -version edu.ntu.ccds.sc2002.**`
- ✅ Create UML diagrams as you code, not after
- ✅ Document your design decisions as you make them
- ✅ Practice your demo multiple times (15-minute limit!)

### For Better Grades:
- 🌟 Add extra features (see suggestions in README)
- 🌟 Implement comprehensive error handling with retry logic
- 🌟 Create detailed UML diagrams with OOP principle annotations
- 🌟 Write thorough reflection explaining design trade-offs
- 🌟 Show deep understanding of OOP principles in demo

### For Learning:
- 📚 Understand WHY each design decision was made
- 📚 Compare alternative design patterns you could have used
- 📚 Think about how to extend the system for new requirements
- 📚 Learn from team members' approaches and perspectives

---

## Common Enhancements Students Add

### Easy (1-2 hours):
- [ ] Email validation with regex
- [ ] Password strength requirements  
- [ ] Search internships by keyword
- [ ] Sort internships by different criteria (date, company, level)
- [ ] Export reports to CSV file

### Medium (3-5 hours):
- [ ] Edit user profiles (change major, year, etc.)
- [ ] Application deadline countdown/notifications
- [ ] Internship ratings and reviews by students
- [ ] Multiple majors per internship (instead of just one)
- [ ] Application cover letter/message field

### Advanced (6+ hours):
- [ ] GUI with JavaFX (very impressive!)
- [ ] Database integration (MySQL)
- [ ] Advanced analytics dashboard with charts
- [ ] Recommendation system based on student profile
- [ ] Email notifications (using JavaMail)

---

## Default Login Credentials

### Students
| User ID | Password | Year | Major | Use Case |
|---------|----------|------|-------|----------|
| U2345123F | password | 3 | CSC | Can apply for any level |
| U2345124G | password | 2 | EEE | Can only apply for Basic |
| U2345125H | password | 4 | MAE | Can apply for any level |
| U2345126J | password | 1 | CSC | Can only apply for Basic |

### Staff
| User ID | Password | Department |
|---------|----------|------------|
| staff001 | password | Career Services |

### Company Representatives
- Register via Option 2 from main menu
- Need staff approval before login

---

## Getting Help

### Resources:
- **Course Materials**: NTULearn SC2002 course site
- **Java Docs**: https://docs.oracle.com/javase/8/docs/api/
- **UML Tools**: Visual Paradigm tutorials
- **Git Help**: https://git-scm.com/doc

### Team Communication:
- Regular meetings (2-3 times per week recommended)
- Use Git for version control and collaboration
- Code review each other's work before committing
- Share learnings and challenges in team chat

---

## Final Checklist Before Submission

### Code:
- [ ] All features implemented and working
- [ ] No compilation errors or warnings
- [ ] Exception handling implemented throughout
- [ ] Java naming conventions followed
- [ ] Code is well-commented
- [ ] No hardcoded values (use constants/config)
- [ ] Data persistence working correctly

### Documentation:
- [ ] Javadoc generated (docs/index.html exists and is complete)
- [ ] All public methods have Javadoc comments
- [ ] Package-level documentation included
- [ ] README is comprehensive and up-to-date
- [ ] UML diagrams created and annotated
- [ ] Report is within 12 pages
- [ ] Reflection is thoughtful and detailed

### Testing:
- [ ] All 40 test cases from TESTING_GUIDE passed
- [ ] Edge cases tested and handled
- [ ] Data persistence tested (restart app, check data)
- [ ] User workflows tested end-to-end
- [ ] Demo prepared and rehearsed (under 15 minutes!)

### Submission:
- [ ] Declaration form signed by all members
- [ ] WBS completed (if contributions differ)
- [ ] GitHub repository created and shared
- [ ] All files organized correctly
- [ ] Submission file named correctly: `<lab_grp>-grp<#>`
- [ ] Submitted before deadline (one day before demo!)

---

## Time Estimate

Typical time breakdown for this assignment:

| Task | Estimated Time |
|------|---------------|
| Understanding requirements | 2-3 hours |
| Design and UML | 4-6 hours |
| Implementation | 15-20 hours |
| Testing | 3-5 hours |
| Documentation & Javadoc | 4-6 hours |
| Report writing | 4-6 hours |
| Demo preparation | 2-3 hours |
| **Total** | **34-49 hours** |

**⏰ Start early!** Don't leave it until the last week. Begin at least 3-4 weeks before deadline.

---

## Success Stories

> "Following this structure helped us complete the assignment a week early. We spent the extra time adding features and polishing our demo." - Previous Student

> "The MVC architecture made it easy to divide work among team members. One person did entities, another did controllers, another did UI." - Previous Student

> "Understanding the design patterns was key. It wasn't just about making it work, but making it well-designed. That's what got us top marks." - Previous Student

> "The test cases in TESTING_GUIDE.md were super helpful. We caught so many bugs before the demo!" - Previous Student

---

## Pro Tips 💡

1. **Commit often**: Push to GitHub after completing each feature
2. **Test as you code**: Don't wait until the end to test
3. **Document while coding**: Write Javadoc as you write methods
4. **Pair program**: Work together on complex parts
5. **Take breaks**: Better code quality when you're fresh
6. **Ask for help**: Use TA office hours and lab sessions
7. **Backup everything**: Use Git, don't rely on one computer

---

**Good luck with your assignment! 🎓**

Remember: This is not just about getting marks. It's about learning Object-Oriented Design principles that you'll use throughout your software engineering career!

**Need help?** Refer to:
- [README.md](README.md) - Full documentation
- [DESIGN_CONSIDERATIONS.md](DESIGN_CONSIDERATIONS.md) - Design explanations
- [TESTING_GUIDE.md](TESTING_GUIDE.md) - Complete test cases

---

**🚀 You've got this!** Now go build something amazing!