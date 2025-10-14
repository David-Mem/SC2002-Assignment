# Testing Guide

## Overview
This document provides comprehensive testing instructions for the Internship Placement Management System, covering all test cases mentioned in the assignment.

## Pre-requisites
1. Application compiled successfully
2. Sample data loaded (users.txt)
3. Clean data directory (delete .dat files for fresh start if needed)

## Test Case Structure

### Test Case Format
- **Test ID**: Unique identifier
- **Description**: What is being tested
- **Pre-conditions**: Setup required
- **Steps**: Detailed execution steps
- **Expected Result**: What should happen
- **Actual Result**: What actually happened (to be filled during testing)
- **Status**: PASS/FAIL

---

## Test Cases

### TC001: Valid User Login - Student

**Description**: Verify that a valid student can login successfully

**Pre-conditions**:
- Application is running
- Student account exists (U2345123F)

**Steps**:
1. Run the application
2. Select "1. Login"
3. Enter User ID: `U2345123F`
4. Enter Password: `password`

**Expected Result**:
- Login successful message displayed
- Student name shown in welcome message
- Student menu displayed with 7 options

**Pass Criteria**:
- No error messages
- Correct menu for student role

---

### TC002: Valid User Login - Company Representative

**Description**: Verify that an approved company representative can login

**Pre-conditions**:
- Company rep registered and approved by staff

**Setup**:
1. Login as staff (staff001/password)
2. Register company rep via option 2 on login screen
   - Email: tech@company.com
   - Name: Tech Manager
   - Password: password
   - Company: Tech Corp
   - Department: HR
   - Position: Manager
3. As staff, authorize the account (option 1)
4. Logout

**Steps**:
1. Select "1. Login"
2. Enter User ID: `tech@company.com`
3. Enter Password: `password`

**Expected Result**:
- Login successful
- Company Representative menu displayed

---

### TC003: Valid User Login - Career Center Staff

**Description**: Verify that career center staff can login

**Pre-conditions**:
- Staff account exists (staff001)

**Steps**:
1. Select "1. Login"
2. Enter User ID: `staff001`
3. Enter Password: `password`

**Expected Result**:
- Login successful
- Career Center Staff menu displayed with 7 options

---

### TC004: Invalid User ID

**Description**: Verify system rejects invalid user ID

**Steps**:
1. Select "1. Login"
2. Enter User ID: `INVALID123`
3. Enter Password: `password`

**Expected Result**:
- Login failed message
- Error message: "Login failed. Invalid credentials or account not approved."
- Return to login menu

---

### TC005: Incorrect Password

**Description**: Verify system rejects incorrect password

**Steps**:
1. Select "1. Login"
2. Enter User ID: `U2345123F`
3. Enter Password: `wrongpassword`

**Expected Result**:
- Login failed message
- Return to login menu
- Account not locked (can retry)

---

### TC006: Password Change Functionality

**Description**: Verify user can change password

**Pre-conditions**:
- Logged in as student (U2345123F)

**Steps**:
1. Select "6. Change Password"
2. Enter current password: `password`
3. Enter new password: `newpass123`
4. Confirm new password: `newpass123`
5. Logout
6. Login with User ID: `U2345123F` and Password: `newpass123`

**Expected Result**:
- Password change successful message
- Can logout and login with new password
- Cannot login with old password

---

### TC007: Company Representative Registration

**Description**: Verify company representative registration process

**Pre-conditions**:
- At login menu

**Steps**:
1. Select "2. Register as Company Representative"
2. Enter email: `hr@techcorp.com`
3. Enter name: `John Smith`
4. Enter password: `password`
5. Enter company: `Tech Corporation`
6. Enter department: `Human Resources`
7. Enter position: `HR Manager`

**Expected Result**:
- Registration successful message
- Message: "Your account is pending approval from Career Center Staff"
- Cannot login until approved

---

### TC008: Company Representative Authorization

**Description**: Verify staff can authorize company representatives

**Pre-conditions**:
- Company rep registered (from TC007)
- Logged in as staff

**Steps**:
1. Login as staff001/password
2. Select "1. Authorize Company Representatives"
3. View pending account details
4. Select account number
5. Choose "1. Approve"

**Expected Result**:
- Account approved message
- Company rep can now login

---

### TC009: Internship Opportunity Creation

**Description**: Verify company rep can create internship

**Pre-conditions**:
- Logged in as approved company rep

**Steps**:
1. Select "1. Create Internship Opportunity"
2. Enter title: `Software Development Intern`
3. Enter description: `Work on web applications`
4. Select level: `1` (Basic)
5. Enter major: `CSC`
6. Enter opening date: `2025-11-01`
7. Enter closing date: `2025-12-31`
8. Enter slots: `5`

**Expected Result**:
- Internship created successfully
- Internship ID generated (e.g., INT0001)
- Status: PENDING
- Message about awaiting approval

---

### TC010: Internship Opportunity Approval

**Description**: Verify staff can approve internships

**Pre-conditions**:
- Internship created (from TC009)
- Logged in as staff

**Steps**:
1. Login as staff001/password
2. Select "2. Approve/Reject Internship Opportunities"
3. View pending internship details
4. Select internship number
5. Choose "1. Approve"

**Expected Result**:
- Internship approved successfully
- Status changes to APPROVED
- Message: "Now visible to eligible students"

---

### TC011: View Internships - Student Profile Match

**Description**: Verify students only see eligible internships

**Pre-conditions**:
- Multiple internships created with different requirements
- Logged in as Year 3 CSC student (U2345123F)

**Test Data**:
- Internship A: CSC, Basic, Visible=ON, Approved
- Internship B: EEE, Basic, Visible=ON, Approved
- Internship C: CSC, Advanced, Visible=ON, Approved

**Steps**:
1. Select "1. View Available Internships"

**Expected Result**:
- Student sees Internship A (CSC, any level allowed for Year 3)
- Student sees Internship C (CSC, Year 3 can apply for Advanced)
- Student does NOT see Internship B (wrong major)

---

### TC012: View Internships - Year Restriction

**Description**: Verify Year 1-2 students only see Basic level

**Pre-conditions**:
- Logged in as Year 2 student (U2345124G, EEE)

**Test Data**:
- Internship D: EEE, Basic, Visible=ON, Approved
- Internship E: EEE, Intermediate, Visible=ON, Approved

**Steps**:
1. Select "1. View Available Internships"

**Expected Result**:
- Student sees Internship D (Basic)
- Student does NOT see Internship E (Intermediate not allowed for Year 2)

---

### TC013: Apply for Internship - Success

**Description**: Verify student can apply for eligible internship

**Pre-conditions**:
- Logged in as student
- Eligible internship available
- Student has < 3 applications

**Steps**:
1. Note an internship ID from "View Available Internships"
2. Select "2. Apply for Internship"
3. Enter the internship ID

**Expected Result**:
- Application submitted successfully
- Application ID generated
- Message showing application ID

---

### TC014: Apply for Internship - Maximum Limit

**Description**: Verify student cannot exceed 3 applications

**Pre-conditions**:
- Student has already applied for 3 internships

**Steps**:
1. Select "2. Apply for Internship"
2. Enter valid internship ID

**Expected Result**:
- Error message: "You have reached the maximum of 3 applications"
- No new application created

---

### TC015: Apply for Ineligible Internship

**Description**: Verify student cannot apply for ineligible internship

**Pre-conditions**:
- Year 2 student logged in
- Intermediate level internship available

**Steps**:
1. Select "2. Apply for Internship"
2. Enter ID of Intermediate level internship

**Expected Result**:
- Error message: "You are not eligible for this internship"
- No application created

---

### TC016: View My Applications

**Description**: Verify student can view their applications

**Pre-conditions**:
- Student has submitted applications

**Steps**:
1. Select "3. View My Applications"

**Expected Result**:
- List of all applications shown
- Shows: Application ID, Internship Title, Company, Status, Date
- Shows both pending and processed applications

---

### TC017: View Applications After Visibility Off

**Description**: Verify student can see applications even when visibility is off

**Pre-conditions**:
- Student applied for internship
- Company rep turns visibility OFF

**Steps**:
1. As company rep: Toggle visibility OFF for internship
2. As student: Select "3. View My Applications"

**Expected Result**:
- Application still visible to student
- All details accessible

---

### TC018: Company Rep Approve Application

**Description**: Verify company rep can approve applications

**Pre-conditions**:
- Student application received
- Logged in as company rep

**Steps**:
1. Select "5. View Applications"
2. Enter internship ID
3. Note student details
4. Select "6. Process Application"
5. Enter application ID
6. Choose "1. Approve"

**Expected Result**:
- Application status changes to SUCCESSFUL
- Message: "Application approved successfully"

---

### TC019: Student Accept Placement

**Description**: Verify student can accept successful placement

**Pre-conditions**:
- Student has successful application
- No confirmed placement yet

**Steps**:
1. Login as student
2. Select "4. Accept Internship Placement"
3. View successful applications
4. Enter number to accept

**Expected Result**:
- Placement confirmed
- Message: "All other applications have been withdrawn"
- Other applications status = WITHDRAWN
- Available slots decremented

---

### TC020: Single Placement Per Student

**Description**: Verify student can only accept one placement

**Pre-conditions**:
- Student has confirmed a placement

**Steps**:
1. Select "4. Accept Internship Placement"

**Expected Result**:
- Message: "You have already confirmed an internship placement"
- Cannot accept another

---

### TC021: Withdrawal Request - Before Confirmation

**Description**: Verify student can request withdrawal before confirmation

**Pre-conditions**:
- Student has pending application
- No placement confirmed yet

**Steps**:
1. Select "5. Request Withdrawal"
2. Select application number
3. Enter reason: `Found better opportunity`

**Expected Result**:
- Withdrawal request created
- Request ID generated
- Message: "Pending approval from Career Center Staff"

---

### TC022: Withdrawal Request - After Confirmation

**Description**: Verify student can request withdrawal after confirmation

**Pre-conditions**:
- Student has confirmed placement

**Steps**:
1. Select "5. Request Withdrawal"
2. Select confirmed application
3. Enter reason: `Personal circumstances`

**Expected Result**:
- Withdrawal request created
- Marked as "After Confirmation"
- Pending staff approval

---

### TC023: Process Withdrawal Request - Approve

**Description**: Verify staff can approve withdrawal

**Pre-conditions**:
- Withdrawal request pending
- Logged in as staff

**Steps**:
1. Select "3. Process Withdrawal Requests"
2. View request details
3. Select request number
4. Choose "1. Approve"

**Expected Result**:
- Request approved
- Application status = WITHDRAWN
- If after confirmation, slot freed up
- Student can apply again

---

### TC024: Internship Status Filled

**Description**: Verify internship status changes to FILLED when all slots taken

**Pre-conditions**:
- Internship with 2 slots
- 2 students applied and approved

**Steps**:
1. Both students accept placement
2. Check internship status

**Expected Result**:
- Status changes to FILLED
- Available slots = 0
- No new applications allowed

---

### TC025: Toggle Visibility

**Description**: Verify company rep can toggle internship visibility

**Pre-conditions**:
- Company rep has created internship
- Currently visible

**Steps**:
1. Select "7. Toggle Internship Visibility"
2. Enter internship ID

**Expected Result**:
- Visibility toggled to OFF
- Message confirms change
- Students cannot see internship in available list
- Students with existing applications can still view

---

### TC026: Generate Report - All Internships

**Description**: Verify staff can generate comprehensive report

**Pre-conditions**:
- Multiple internships exist
- Logged in as staff

**Steps**:
1. Select "4. Generate Reports"
2. Choose "1. All Internships Report"

**Expected Result**:
- Lists all internships
- Shows: ID, Title, Company, Level, Major, Status, Slots, Applications
- Sorted alphabetically

---

### TC027: Generate Report - Filter by Status

**Description**: Verify report filtering by status works

**Steps**:
1. Select "4. Generate Reports"
2. Choose "2. Filter by Status"
3. Select status (e.g., APPROVED)

**Expected Result**:
- Only internships with selected status shown
- Count displayed

---

### TC028: Generate Report - Filter by Major

**Description**: Verify report filtering by major works

**Steps**:
1. Select "4. Generate Reports"
2. Choose "3. Filter by Major"
3. Enter major: `CSC`

**Expected Result**:
- Only CSC internships shown
- Accurate count

---

### TC029: Edit Internship - Before Approval

**Description**: Verify company rep can edit before approval

**Pre-conditions**:
- Internship created but not approved (PENDING)

**Steps**:
1. Select "3. Edit Internship Opportunity"
2. Enter internship ID
3. Update title and description

**Expected Result**:
- Changes saved successfully
- Can view updated details

---

### TC030: Edit Internship - After Approval (Negative Test)

**Description**: Verify cannot edit after approval

**Pre-conditions**:
- Internship approved

**Steps**:
1. Select "3. Edit Internship Opportunity"
2. Enter internship ID

**Expected Result**:
- Error message: "Cannot edit internship after it has been approved"
- No changes allowed

---

### TC031: Delete Internship - Before Approval

**Description**: Verify can delete before approval

**Pre-conditions**:
- Internship created but not approved

**Steps**:
1. Select "4. Delete Internship Opportunity"
2. Enter internship ID
3. Confirm: `yes`

**Expected Result**:
- Internship deleted
- Removed from system
- Cannot view anymore

---

### TC032: Data Persistence Test

**Description**: Verify data saves and loads correctly

**Steps**:
1. Create internship, apply for it
2. Exit application (option 3 from login)
3. Restart application
4. Login and check data

**Expected Result**:
- All data preserved
- Internship still exists
- Application still exists
- No data loss

---

## Test Execution Checklist

### Before Testing
- [ ] Clean installation
- [ ] Data directory created
- [ ] Sample users loaded
- [ ] No existing .dat files (fresh start)

### During Testing
- [ ] Document actual results
- [ ] Take screenshots of key screens
- [ ] Note any unexpected behavior
- [ ] Record error messages exactly

### After Testing
- [ ] Calculate pass/fail rate
- [ ] Document all failures
- [ ] Identify patterns in failures
- [ ] Plan fixes for failed tests

## Test Report Template

```
Test Execution Report
Date: __________
Tester: __________

Total Test Cases: 32
Passed: ___
Failed: ___
Pass Rate: ___%

Failed Test Cases:
1. TC___ - Description - Reason

Known Issues:
1. Issue description
2. Issue description

Recommendations:
1. Recommendation
2. Recommendation
```

## Common Issues and Solutions

### Issue 1: File Not Found
**Solution**: Ensure data directory exists, create manually if needed

### Issue 2: Serialization Error
**Solution**: Delete all .dat files and restart

### Issue 3: Login Fails for New Company Rep
**Solution**: Ensure staff has approved the account first

### Issue 4: Cannot See Internships
**Solution**: Check visibility is ON and status is APPROVED

## Automated Testing Suggestions

For future enhancement, consider:
1. JUnit tests for business logic
2. Selenium for UI testing (if GUI added)
3. Continuous integration setup
4. Test coverage analysis

## Demo Script

Recommended flow for demonstration:

1. **Setup Phase** (2 min)
   - Show clean system
   - Login as staff
   - Show initial data

2. **Company Rep Journey** (3 min)
   - Register
   - Get approved
   - Create internship
   - Wait for approval

3. **Student Journey** (4 min)
   - View internships
   - Apply for internship
   - View status
   - Accept placement

4. **Staff Operations** (3 min)
   - Approve internships
   - Process withdrawals
   - Generate reports

5. **Edge Cases** (2 min)
   - Show application limit
   - Show eligibility checks
   - Show visibility toggle

6. **Q&A** (1 min)

---

**Total Time**: 15 minutes (matches requirement)