# Testing Guide

## Overview
This document provides comprehensive testing instructions for the Internship Placement Management System, covering all functionality and edge cases.

## Pre-requisites
1. Application compiled successfully
2. Sample data loaded (`data/users.txt`)
3. Clean data directory (delete `.dat` files for fresh start if needed)

## Test Case Structure

Each test case includes:
- **Test ID**: Unique identifier
- **Description**: What is being tested
- **Pre-conditions**: Setup required
- **Steps**: Detailed execution steps
- **Expected Result**: What should happen
- **Actual Result**: To be filled during testing
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
2. Select "3. Exit" to go back to main menu
3. Select "2. Register as Company Representative"
   - Email: tech@company.com
   - Name: Tech Manager
   - Password: password
   - Company: Tech Corp
   - Department: HR
   - Position: Manager
4. Login as staff again
5. Select "1. Authorize Company Representatives"
6. Approve the account
7. Logout

**Steps**:
1. Select "1. Login"
2. Enter User ID: `tech@company.com`
3. Enter Password: `password`

**Expected Result**:
- Login successful
- Company Representative menu displayed with 9 options

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
- Login failed message: "Login failed. Invalid credentials or account not approved."
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
5. Select "7. Logout"
6. Login with User ID: `U2345123F` and Password: `newpass123`

**Expected Result**:
- Password change successful message
- Can logout and login with new password
- Cannot login with old password

**Cleanup**: Change password back to `password` for other tests

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
- Message: "You will be able to login once approved"
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
3. View pending account details (should show hr@techcorp.com)
4. Enter account number: `1`
5. Choose "1. Approve"
6. Logout

**Expected Result**:
- Account approved message
- Company rep can now login

**Verify**: Try logging in as hr@techcorp.com/password - should succeed

---

### TC009: Internship Opportunity Creation

**Description**: Verify company rep can create internship

**Pre-conditions**:
- Logged in as approved company rep (hr@techcorp.com or tech@company.com)

**Steps**:
1. Select "1. Create Internship Opportunity"
2. Enter title: `Software Development Intern`
3. Enter description: `Work on web applications using React and Node.js`
4. Select level: `1` (Basic)
5. Enter major: `CSC`
6. Enter opening date: `2025-11-01`
7. Enter closing date: `2025-12-31`
8. Enter slots: `5`

**Expected Result**:
- Internship created successfully
- Internship ID generated (e.g., INT0001)
- Status: PENDING
- Message: "Status: PENDING (awaiting Career Center Staff approval)"

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
4. Enter internship number: `1`
5. Choose "1. Approve"

**Expected Result**:
- "Internship approved! Now visible to eligible students."
- Status changes to APPROVED

---

### TC011: View Internships - Student Profile Match

**Description**: Verify students only see eligible internships

**Pre-conditions**:
- Multiple internships created with different requirements
- Logged in as Year 3 CSC student (U2345123F)

**Test Data Setup** (create these as company rep and get them approved):
- Internship A: CSC, Basic, Visible=ON, Approved
- Internship B: EEE, Basic, Visible=ON, Approved
- Internship C: CSC, Advanced, Visible=ON, Approved

**Steps**:
1. Login as U2345123F/password (Year 3, CSC)
2. Select "1. View Available Internships"

**Expected Result**:
- Student sees Internship A (CSC, Basic - eligible)
- Student sees Internship C (CSC, Advanced - Year 3 can apply)
- Student does NOT see Internship B (EEE - wrong major)

---

### TC012: View Internships - Year Restriction

**Description**: Verify Year 1-2 students only see Basic level

**Pre-conditions**:
- Logged in as Year 2 student (U2345124G, EEE)
- Internships from TC011 exist

**Test Data Setup** (create these):
- Internship D: EEE, Basic, Visible=ON, Approved
- Internship E: EEE, Intermediate, Visible=ON, Approved

**Steps**:
1. Login as U2345124G/password (Year 2, EEE)
2. Select "1. View Available Internships"

**Expected Result**:
- Student sees Internship D (EEE, Basic - eligible)
- Student does NOT see Internship E (Intermediate not allowed for Year 2)

---

### TC013: Apply for Internship - Success

**Description**: Verify student can apply for eligible internship

**Pre-conditions**:
- Logged in as student (U2345123F)
- Eligible approved internship available
- Student has < 3 applications

**Steps**:
1. Select "1. View Available Internships"
2. Note an internship ID (e.g., INT0001)
3. Select "2. Apply for Internship"
4. Enter internship ID: `INT0001`

**Expected Result**:
- "Application submitted successfully!"
- Application ID generated (e.g., APP0001)
- Message showing "Application ID: APP0001"

---

### TC014: Apply for Internship - Maximum Limit

**Description**: Verify student cannot exceed 3 applications

**Pre-conditions**:
- Student has already applied for 3 internships

**Setup**:
1. Create 3 different internships
2. Apply for all 3 (repeat TC013 three times)

**Steps**:
1. Try to apply for a 4th internship
2. Select "2. Apply for Internship"
3. Enter valid internship ID

**Expected Result**:
- Error message: "You have reached the maximum of 3 applications."
- No new application created

---

### TC015: Apply for Ineligible Internship

**Description**: Verify student cannot apply for ineligible internship

**Pre-conditions**:
- Year 2 student logged in (U2345124G)
- Intermediate level internship available

**Steps**:
1. Login as U2345124G/password (Year 2, EEE)
2. Select "2. Apply for Internship"
3. Enter ID of Intermediate level EEE internship (e.g., INT0005)

**Expected Result**:
- Error message: "You are not eligible for this internship."
- No application created

---

### TC016: View My Applications

**Description**: Verify student can view their applications

**Pre-conditions**:
- Student has submitted applications (from TC013)

**Steps**:
1. Login as student who has applications
2. Select "3. View My Applications"

**Expected Result**:
- List of all applications shown
- Each application shows:
  - Application ID
  - Internship Title
  - Company Name
  - Status (PENDING)
  - Application Date

---

### TC017: View Applications After Visibility Off

**Description**: Verify student can see applications even when visibility is off

**Pre-conditions**:
- Student applied for internship (from TC013)

**Steps**:
1. Login as company rep who created the internship
2. Select "7. Toggle Internship Visibility"
3. Enter internship ID
4. Confirm visibility toggled to OFF
5. Logout
6. Login as student
7. Select "3. View My Applications"

**Expected Result**:
- Application still visible to student
- All details accessible
- Can still see internship title and company

---

### TC018: Company Rep Approve Application

**Description**: Verify company rep can approve applications

**Pre-conditions**:
- Student application received (from TC013)
- Logged in as company rep

**Steps**:
1. Login as company rep
2. Select "5. View Applications"
3. Enter internship ID (e.g., INT0001)
4. View student details
5. Select "6. Process Application (Approve/Reject)"
6. Enter application ID (e.g., APP0001)
7. Choose "1. Approve"

**Expected Result**:
- "Application approved successfully!"
- Application status changes to SUCCESSFUL

---

### TC019: Student Accept Placement

**Description**: Verify student can accept successful placement

**Pre-conditions**:
- Student has successful application (from TC018)
- No confirmed placement yet

**Steps**:
1. Login as student
2. Select "4. Accept Internship Placement"
3. View successful applications
4. Enter application number to accept: `1`

**Expected Result**:
- "Internship placement confirmed successfully!"
- "All other applications have been withdrawn."
- Other applications status = WITHDRAWN
- Available slots decremented in internship

---

### TC020: Single Placement Per Student

**Description**: Verify student can only accept one placement

**Pre-conditions**:
- Student has confirmed a placement (from TC019)

**Steps**:
1. Login as student with confirmed placement
2. Select "4. Accept Internship Placement"

**Expected Result**:
- Message: "You have already confirmed an internship placement."
- Cannot accept another

---

### TC021: Withdrawal Request - Before Confirmation

**Description**: Verify student can request withdrawal before confirmation

**Pre-conditions**:
- Student has pending application
- No placement confirmed yet

**Steps**:
1. Login as student
2. Select "5. Request Withdrawal"
3. View active applications
4. Select application number: `1`
5. Enter reason: `Found better opportunity`

**Expected Result**:
- "Withdrawal request submitted successfully!"
- Request ID generated (e.g., WDR0001)
- Message: "Pending approval from Career Center Staff"

---

### TC022: Withdrawal Request - After Confirmation

**Description**: Verify student can request withdrawal after confirmation

**Pre-conditions**:
- Student has confirmed placement (from TC019)

**Steps**:
1. Login as student with confirmed placement
2. Select "5. Request Withdrawal"
3. Select confirmed application
4. Enter reason: `Personal circumstances changed`

**Expected Result**:
- Withdrawal request created
- Marked as "After Confirmation"
- Pending staff approval

---

### TC023: Process Withdrawal Request - Approve

**Description**: Verify staff can approve withdrawal

**Pre-conditions**:
- Withdrawal request pending (from TC021 or TC022)
- Logged in as staff

**Steps**:
1. Login as staff001/password
2. Select "3. Process Withdrawal Requests"
3. View request details
4. Enter request number: `1`
5. Choose "1. Approve"

**Expected Result**:
- "Withdrawal request approved!"
- Application status = WITHDRAWN
- If after confirmation: slot freed up, student can apply again
- Student's confirmedInternshipId cleared

---

### TC024: Internship Status Filled

**Description**: Verify internship status changes to FILLED when all slots taken

**Pre-conditions**:
- Internship with 2 slots created and approved

**Steps**:
1. Create internship with 2 slots
2. Have 2 students apply
3. Approve both applications
4. Both students accept placement
5. Check internship status

**Expected Result**:
- Status changes to FILLED
- Available slots = 0
- No new applications allowed
- Visibility doesn't matter - still can't apply

---

### TC025: Toggle Visibility

**Description**: Verify company rep can toggle internship visibility

**Pre-conditions**:
- Company rep has created internship
- Currently visible

**Steps**:
1. Login as company rep
2. Select "7. Toggle Internship Visibility"
3. Enter internship ID

**Expected Result**:
- "Visibility toggled to: OFF" message
- Students cannot see internship in available list (verify by logging in as student)
- Students with existing applications can still view their applications

**Verify**:
1. Toggle back to ON
2. Message: "Visibility toggled to: ON"
3. Students can now see it again

---

### TC026: Generate Report - All Internships

**Description**: Verify staff can generate comprehensive report

**Pre-conditions**:
- Multiple internships exist
- Logged in as staff

**Steps**:
1. Login as staff
2. Select "4. Generate Reports"
3. Choose "1. All Internships Report"

**Expected Result**:
- Lists all internships
- Shows: ID, Title, Company, Level, Major, Status, Visibility, Slots, Applications, Confirmed
- Sorted alphabetically by title
- Total count displayed

---

### TC027: Generate Report - Filter by Status

**Description**: Verify report filtering by status works

**Steps**:
1. Login as staff
2. Select "4. Generate Reports"
3. Choose "2. Filter by Status"
4. Select status: `2` (APPROVED)

**Expected Result**:
- Only internships with APPROVED status shown
- Accurate count displayed
- Sorted alphabetically

---

### TC028: Generate Report - Filter by Major

**Description**: Verify report filtering by major works

**Steps**:
1. Login as staff
2. Select "4. Generate Reports"
3. Choose "3. Filter by Major"
4. Enter major: `CSC`

**Expected Result**:
- Only CSC internships shown
- Accurate count
- All details displayed

---

### TC029: Edit Internship - Before Approval

**Description**: Verify company rep can edit before approval

**Pre-conditions**:
- Internship created but not approved (PENDING)
- Logged in as company rep

**Steps**:
1. Select "3. Edit Internship Opportunity"
2. Enter internship ID
3. Enter new title: `Updated Software Intern Title`
4. Enter new description: `Updated description text`

**Expected Result**:
- "Internship updated successfully!"
- Changes saved
- Can view updated details in "View My Internships"

---

### TC030: Edit Internship - After Approval (Negative Test)

**Description**: Verify cannot edit after approval

**Pre-conditions**:
- Internship approved by staff

**Steps**:
1. Login as company rep
2. Select "3. Edit Internship Opportunity"
3. Enter ID of approved internship

**Expected Result**:
- Error message: "Cannot edit internship after it has been approved."
- No changes allowed
- Internship remains unchanged

---

### TC031: Delete Internship - Before Approval

**Description**: Verify can delete before approval

**Pre-conditions**:
- Internship created but not approved

**Steps**:
1. Login as company rep
2. Select "4. Delete Internship Opportunity"
3. Enter internship ID
4. Confirm: `yes`

**Expected Result**:
- "Internship deleted successfully!"
- Removed from system
- Cannot view in "View My Internships"
- Not visible to students

---

### TC032: Delete Internship - After Approval (Negative Test)

**Description**: Verify cannot delete after approval

**Pre-conditions**:
- Internship approved by staff

**Steps**:
1. Login as company rep
2. Select "4. Delete Internship Opportunity"
3. Enter ID of approved internship

**Expected Result**:
- Error message: "Cannot delete internship after it has been approved."
- Internship remains in system

---

### TC033: Data Persistence Test

**Description**: Verify data saves and loads correctly

**Steps**:
1. Create internship, apply for it, approve application
2. Select "3. Exit" from main menu
3. Verify message: "Data saved successfully. Goodbye!"
4. Restart application
5. Login and check data

**Expected Result**:
- All data preserved after restart
- Internship still exists with same ID
- Application still exists with same status
- User data unchanged
- No data loss

---

### TC034: Company Rep Login Before Approval (Negative Test)

**Description**: Verify company rep cannot login before approval

**Pre-conditions**:
- Company rep registered but NOT approved

**Steps**:
1. Register new company rep
2. Do NOT approve as staff
3. Try to login with company rep credentials

**Expected Result**:
- Login fails
- Message: "Your account is pending approval from Career Center Staff."
- Returns to login menu

---

### TC035: Apply After Closing Date (Negative Test)

**Description**: Verify cannot apply after closing date

**Pre-conditions**:
- Internship with closing date in the past

**Steps**:
1. Create internship with closing date: `2025-01-01` (past date)
2. Get it approved
3. Login as student
4. Try to apply

**Expected Result**:
- Error message: "This internship is not currently accepting applications."
- No application created

---

### TC036: Input Validation - Invalid Date Format

**Description**: Verify system handles invalid date input gracefully

**Steps**:
1. Login as company rep
2. Select "1. Create Internship Opportunity"
3. Enter all details correctly until date
4. Enter opening date: `invalid-date`

**Expected Result**:
- Error message: "Invalid date format. Please use yyyy-MM-dd (e.g., 2025-11-01)"
- Prompts to re-enter
- System doesn't crash

---

### TC037: Input Validation - Invalid Number

**Description**: Verify system handles non-numeric input

**Steps**:
1. Login as company rep
2. Select "1. Create Internship Opportunity"
3. Enter all details until slots
4. Enter slots: `abc`

**Expected Result**:
- Error message: "Invalid number. Please enter a number between 1 and 10."
- Prompts to re-enter
- System doesn't crash

---

### TC038: Concurrent Application Limit

**Description**: Verify application limit enforced correctly

**Setup**:
1. Student has 2 applications

**Steps**:
1. Apply for 3rd internship (should succeed)
2. Try to apply for 4th internship

**Expected Result**:
- 3rd application succeeds
- 4th application fails with message: "You have reached the maximum of 3 applications."

---

### TC039: Cannot Apply While Having Confirmed Placement

**Description**: Verify student cannot apply after accepting placement

**Pre-conditions**:
- Student has accepted a placement

**Steps**:
1. Login as student with confirmed placement
2. Select "2. Apply for Internship"
3. Enter valid internship ID

**Expected Result**:
- Error message: "You have already confirmed an internship placement."
- Cannot apply

---

### TC040: Company Rep Internship Limit

**Description**: Verify company rep cannot exceed 5 internships

**Pre-conditions**:
- Company rep has created 5 internships

**Steps**:
1. Login as company rep
2. Try to create 6th internship

**Expected Result**:
- Error message: "You have reached the maximum of 5 internship opportunities."
- Cannot create more

---

## Test Execution Summary

### Test Report Template

```
Test Execution Report
Date: __________
Tester: __________

Total Test Cases: 40
Passed: ___
Failed: ___
Pass Rate: ___%

Failed Test Cases:
1. TC___ - Description - Reason
2. TC___ - Description - Reason

Known Issues:
1. Issue description
2. Issue description

Recommendations:
1. Recommendation
2. Recommendation
```

---

## Common Issues and Solutions

### Issue 1: "File not found" error
**Solution**: Ensure `data` directory exists, create manually if needed

### Issue 2: Serialization Error
**Solution**: Delete all `.dat` files in `data/` folder and restart

### Issue 3: Login Fails for New Company Rep
**Solution**: Ensure staff has approved the account first

### Issue 4: Cannot See Internships
**Solution**: Check visibility is ON and status is APPROVED

### Issue 5: Date Format Issues
**Solution**: Use strict format: yyyy-MM-dd (e.g., 2025-11-01)

---

## Automated Testing Suggestions

For future enhancement, consider:
1. JUnit tests for business logic
2. Mockito for mocking dependencies
3. Integration tests for workflows
4. Continuous integration setup
5. Test coverage analysis with JaCoCo

---

## Demo Script

Recommended flow for 15-minute demonstration:

### **Minutes 0-2: Setup & Introduction**
- Introduce team and project
- Show system architecture
- Explain MVC pattern

### **Minutes 2-5: Career Staff Operations**
- Authorize company representative
- Approve internship opportunities
- Show approval workflow

### **Minutes 5-9: Company Rep Operations**
- Login as company rep
- Create internship
- View applications
- Process (approve) application
- Toggle visibility

### **Minutes 9-13: Student Operations**
- Login as student
- View filtered internships (show eligibility)
- Apply for internship
- View application status
- Accept placement (show withdrawal of others)

### **Minutes 13-14: Edge Cases**
- Show application limit (3)
- Show eligibility restrictions (Year 2 → Basic only)
- Show filled internship status

### **Minutes 14-15: Data Persistence & Q&A**
- Exit and restart application
- Show data retained
- Answer questions

---

## Performance Testing

### Load Testing Scenarios:

1. **Many Users**: Test with 100+ users
2. **Many Internships**: Test with 50+ internships
3. **Many Applications**: Test with 200+ applications
4. **Concurrent Operations**: Multiple users operating simultaneously

### Expected Performance:
- Login: < 1 second
- View internships: < 2 seconds
- Apply: < 1 second
- Data load/save: < 3 seconds

---

## Security Testing

### Test Cases:

1. **SQL Injection** (N/A - no database)
2. **Password Security**: Verify passwords not displayed
3. **Access Control**: Verify role-based access
4. **Data Validation**: Verify all inputs validated

---

## Final Checklist

Before submission:
- [ ] All 40 test cases executed
- [ ] Test report completed
- [ ] All critical bugs fixed
- [ ] Data persistence verified
- [ ] All user roles tested
- [ ] Edge cases handled
- [ ] Error messages user-friendly
- [ ] Demo script practiced
- [ ] Screenshots taken
- [ ] Test data prepared

---

**Remember**: The goal is not just to pass tests, but to demonstrate a robust, well-designed system that handles both normal and edge cases gracefully! 🎯