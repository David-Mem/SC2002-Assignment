#!/bin/bash
# Quick setup script - creates all necessary files

echo "Setting up Internship Placement Management System..."

# Create all Java files with touch command (we'll add content after)
touch src/edu/ntu/ccds/sc2002/Main.java

# Entity files
touch src/edu/ntu/ccds/sc2002/entity/User.java
touch src/edu/ntu/ccds/sc2002/entity/UserRole.java
touch src/edu/ntu/ccds/sc2002/entity/Student.java
touch src/edu/ntu/ccds/sc2002/entity/CompanyRepresentative.java
touch src/edu/ntu/ccds/sc2002/entity/CareerCenterStaff.java
touch src/edu/ntu/ccds/sc2002/entity/Internship.java
touch src/edu/ntu/ccds/sc2002/entity/InternshipLevel.java
touch src/edu/ntu/ccds/sc2002/entity/InternshipStatus.java
touch src/edu/ntu/ccds/sc2002/entity/ApplicationStatus.java
touch src/edu/ntu/ccds/sc2002/entity/Application.java
touch src/edu/ntu/ccds/sc2002/entity/WithdrawalRequest.java

# Control files
touch src/edu/ntu/ccds/sc2002/control/AuthenticationController.java
touch src/edu/ntu/ccds/sc2002/control/DataController.java

# Boundary files
touch src/edu/ntu/ccds/sc2002/boundary/LoginUI.java
touch src/edu/ntu/ccds/sc2002/boundary/StudentUI.java
touch src/edu/ntu/ccds/sc2002/boundary/CompanyRepUI.java
touch src/edu/ntu/ccds/sc2002/boundary/CareerStaffUI.java

# Data file
touch data/users.txt

# Documentation
touch DESIGN_CONSIDERATIONS.md
touch TESTING_GUIDE.md
touch QUICK_START_GUIDE.md

echo "✅ File structure created!"
echo "Now copy the code from the artifacts into each file."