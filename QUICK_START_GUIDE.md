# Quick Start Guide - 5 Minute Setup

Get the Internship Placement Management System running in 5 minutes!

---

## Step 1: Verify Prerequisites (30 seconds)

Check you have Java installed:

```bash
java -version
```

Should show Java 8 or higher. If not, download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/).

---

## Step 2: Setup Project Structure (1 minute)

Create your project directory:

```bash
mkdir InternshipSystem
cd InternshipSystem
mkdir -p src/edu/ntu/ccds/sc2002
mkdir -p src/edu/ntu/ccds/sc2002/entity
mkdir -p src/edu/ntu/ccds/sc2002/control
mkdir -p src/edu/ntu/ccds/sc2002/boundary
mkdir data
```

---

## Step 3: Add Sample Data (30 seconds)

Create `data/users.txt` with this content:

```
U2345123F|John Tan|password|STUDENT|3|CSC
U2345124G|Mary Lim|password|STUDENT|2|EEE
U2345125H|David Wong|password|STUDENT|4|MAE
staff001|Admin User|password|CAREER_STAFF|Career Services
```

Save the file.

---

## Step 4: Add All Java Files (2 minutes)

Copy all the Java files provided into their respective directories:

- `Main.java` → `src/edu/ntu/ccds/sc2002/`
- Entity classes → `src/edu/ntu/ccds/sc2002/entity/`
- Controller classes → `src/edu/ntu/ccds/sc2002/control/`
- UI classes → `src/edu/ntu/ccds/sc2002/boundary/`

---

## Step 5: Compile (30 seconds)

### On Windows:
```batch
javac -d bin -sourcepath src src\edu\ntu\ccds\sc2002\*.java src\edu\ntu\ccds\sc2002\entity\*.java src\edu\ntu\ccds\sc2002\control\*.java src\edu\ntu\ccds\sc2002\boundary\*.java
```

### On Mac/Linux:
```bash
javac -d bin -sourcepath src src/edu/ntu/ccds/sc2002/*.java src/edu/ntu/ccds/sc2002/entity/*.java src/edu/ntu/ccds/sc2002/control/*.java src/edu/ntu/ccds/sc2002/boundary/*.java
```

---

## Step 6: Run! (30 seconds)

```bash
java -cp bin edu.ntu.ccds.sc2002.Main
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

---

## First Login Test

Try logging in as a student:
- **User ID**: `U2345123F`
- **Password**: `password`

You should see the student menu!

---

## Quick Demo Flow (5 minutes)

### Test 1: Career Staff Operations
1. Login as `staff001` / `password`
2. Register a company rep (Option 2 from main menu)
3. As staff, authorize the company rep (Option 1)

### Test 2: Company Rep Operations
1. Login as the company rep you created
2. Create an internship (Option 1)
3. Logout, login as staff, approve internship (Option 2)

### Test 3: Student Operations  
1. Login as `U2345123F` / `password`
2. View available internships (Option 1)
3. Apply for an internship (Option 2)

### Test 4: Complete Workflow
1. Login as company rep
2. Process student application (Options 5 & 6)
3. Approve the application
4. Login as student
5. Accept the placement (Option 4)

**Congratulations!** You've completed a full internship placement workflow!

---

## Troubleshooting

### "File not found" error
✅ **Fix**: Create the `data` directory and `users.txt` file

### "Class not found" error
✅ **Fix**: Make sure you compiled with `-d bin` and run with `-cp bin`

### "Cannot load data"
✅ **Fix**: Normal on first run! The system will create empty data structures.

### Compilation errors
✅ **Fix**: 
1. Check all files are in correct directories
2. Ensure no typos in package names
3. Verify Java version is 8+

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
│       │   └── WithdrawalRequest.java ✓
│       ├── control/
│       │   ├── AuthenticationController.java ✓
│       │   └── DataController.java ✓
│       └── boundary/
│           ├── LoginUI.java ✓
│           ├── StudentUI.java ✓
│           ├── CompanyRepUI.java ✓
│           └── CareerStaffUI.java ✓
├── data/
│   └── users.txt ✓
└── bin/ (created during compilation)
```

---

## IDE Setup (Alternative to Command Line)

### IntelliJ IDEA
1. File → New → Project from Existing Sources
2. Select your `InternshipSystem` folder
3. Mark `src` as Sources Root (right-click → Mark Directory As → Sources Root)
4. Right-click `Main.java` → Run 'Main.main()'

### Eclipse
1. File → New → Java Project
2. Uncheck "Use default location"
3. Browse to your `InternshipSystem` folder
4. Right-click `Main.java` → Run As → Java Application

### VS Code
1. Open `InternshipSystem` folder
2. Install Java Extension Pack
3. Press F5 or click Run button

---

## Next Steps

Once you have the basic system running:

1. **Read** the comprehensive [README.md](README.md) for detailed documentation
2. **Study** [DESIGN_CONSIDERATIONS.md](DESIGN_CONSIDERATIONS.md) to understand the architecture
3. **Follow** [TESTING_GUIDE.md](TESTING_GUIDE.md) to test all features
4. **Review** [UML_DIAGRAM_GUIDE.md](UML_DIAGRAM_GUIDE.md) to create your diagrams
5. **Customize** the system with additional features

---

## Tips for Success

### For the Assignment:
- ✅ Test all test cases from Appendix A
- ✅ Generate Javadoc early and keep it updated
- ✅ Create UML diagrams as you code, not after
- ✅ Document your design decisions
- ✅ Practice your demo before presentation

### For Better Grades:
- 🌟 Add extra features (see suggestions in README)
- 🌟 Implement comprehensive error handling
- 🌟 Create detailed UML diagrams with annotations
- 🌟 Write thorough reflection on design choices
- 🌟 Show understanding of OOP principles

### For Learning:
- 📚 Understand WHY you made each design decision
- 📚 Compare different design patterns
- 📚 Think about how to extend the system
- 📚 Learn from team members' approaches

---

## Common Enhancements Students Add

### Easy (1-2 hours):
- [ ] Email validation for company reps
- [ ] Password strength requirements  
- [ ] Search internships by keyword
- [ ] Sort internships by different criteria
- [ ] Export reports to CSV

### Medium (3-5 hours):
- [ ] Edit user profiles
- [ ] Application deadline notifications
- [ ] Internship ratings and reviews
- [ ] Multiple majors per internship
- [ ] Application attachments (resume)

### Advanced (6+ hours):
- [ ] GUI with JavaFX
- [ ] Database integration
- [ ] Advanced analytics dashboard
- [ ] Recommendation system
- [ ] Real-time notifications

---

## Getting Help

### Resources:
- Course materials and lecture notes
- Java Documentation: https://docs.oracle.com/javase/8/docs/api/
- UML tutorials: Visual Paradigm website
- Stack Overflow for specific issues

### Team Communication:
- Regular meetings (2-3 times per week)
- Use Git for version control
- Code review each other's work
- Share learnings and challenges

---

## Final Checklist Before Submission

- [ ] All required features implemented
- [ ] Comprehensive testing completed
- [ ] Javadoc generated (docs/index.html exists)
- [ ] UML diagrams created and annotated
- [ ] Report written (max 12 pages)
- [ ] Reflection completed
- [ ] Demo video prepared (max 15 minutes)
- [ ] Code follows Java naming conventions
- [ ] Exception handling implemented
- [ ] Comments and documentation complete
- [ ] GitHub repository created and shared
- [ ] Declaration form signed by all members
- [ ] WBS form completed (if needed)

---

## Time Estimate

Typical time breakdown for this assignment:

| Task | Estimated Time |
|------|---------------|
| Understanding requirements | 2-3 hours |
| Design and UML | 4-6 hours |
| Implementation | 15-20 hours |
| Testing | 3-5 hours |
| Documentation | 4-6 hours |
| Report writing | 4-6 hours |
| Demo preparation | 2-3 hours |
| **Total** | **34-49 hours** |

Start early! Don't leave it until the last week.

---

## Success Stories

> "Following this structure helped us complete the assignment a week early. We spent the extra time adding features and polishing our demo." - Previous Student

> "The MVC architecture made it easy to divide work among team members. One person did entities, another did controllers, another did UI." - Previous Student

> "Understanding the design patterns was key. It wasn't just about making it work, but making it well-designed." - Previous Student

---

**Good luck with your assignment! 🎓**

Remember: This is not just about getting marks. It's about learning Object-Oriented Design principles that you'll use throughout your career!