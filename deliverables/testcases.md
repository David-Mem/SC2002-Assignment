| Test ID | Test Case Description | Expected Result | Status |
|---|---|---|---|
| **Authentication & General** |
| TC001 | Verify a user (Student, Staff, Company Rep) can log in with correct credentials. | Login is successful, and the user is directed to their role-specific dashboard/menu. | PASS |
| TC002 | Verify a user cannot log in with incorrect credentials. | An "Invalid credentials" error message is displayed. | PASS |
| TC003 | Verify a pending Company Representative cannot log in. | An "Account not approved" error message is displayed. | PASS |
| TC004 | Verify a user can successfully change their password. | The password is updated, and the user can log in with the new password. | PASS |
| TC005 | Verify the system rejects invalid email/ID formats during registration/login. | An "Invalid email/ID format" error message is displayed. | PASS |
| TC006 | Verify all data (users, internships, applications) is saved upon exit and reloaded upon start. | Data remains consistent across application restarts. | PASS |
| **Company Representative Workflow** |
| TC007 | Verify a new Company Representative can register for an account. | Registration is successful, and the account is set to "PENDING" for staff approval. | PASS |
| TC008 | Verify Career Center Staff can approve a pending Company Representative account. | The account status is changed to "APPROVED", and the representative can now log in. | PASS |
| TC009 | Verify an approved Company Representative can create a new internship opportunity. | The internship is created with a "PENDING" status for staff approval. | PASS |
| TC010 | Verify a Company Representative cannot create more than 5 internships. | An error message "You have reached the maximum of 5 internship opportunities" is shown. | PASS |
| TC011 | Verify Career Center Staff can approve a pending internship. | The internship status is changed to "APPROVED" and becomes visible to eligible students. | PASS |
| TC012 | Verify a Company Rep can view all internships they have created. | A list of their created internships with details (ID, Title, Status) is displayed. | PASS |
| TC013 | Verify a Company Rep can edit an internship *before* it is approved. | The internship details are successfully updated. | PASS |
| TC014 | Verify a Company Rep *cannot* edit an internship *after* it has been approved. | An error message is displayed, and no changes are made. | PASS |
| TC015 | Verify a Company Rep can toggle the visibility of an approved internship. | The internship's visibility is successfully changed (e.g., from ON to OFF). | PASS |
| TC016 | Verify a Company Rep can view applications for their internships. | A list of student applicants for a selected internship is displayed. | PASS |
| TC017 | Verify a Company Rep can approve a student's application. | The application's status changes to "SUCCESSFUL". | PASS |
| **Student Workflow** |
| TC018 | Verify a student can view available internships filtered by their profile (major, year). | Only internships matching the student's eligibility are displayed. | PASS |
| TC019 | Verify a student can apply for an eligible internship. | The application is submitted successfully and its status is set to "PENDING". | PASS |
| TC020 | Verify a student cannot apply for more than 3 internships concurrently. | An error message "You have reached the maximum of 3 applications" is shown. | PASS |
| TC021 | Verify a student cannot apply for an internship after accepting another. | An error message is displayed, preventing new applications. | PASS |
| TC022 | Verify a student can view the status of all their applications. | A list of their applications with current statuses (Pending, Successful, etc.) is shown. | PASS |
| TC023 | Verify a student can accept a "SUCCESSFUL" internship offer. | The placement is confirmed. All other pending applications are automatically withdrawn. | PASS |
| TC024 | Verify a student can only have one confirmed internship placement. | An error message is displayed if the student tries to accept a second offer. | PASS |
| TC025 | Verify a student can request to withdraw an application *before* accepting an offer. | A withdrawal request is created with "PENDING" status for staff approval. | PASS |
| **Career Center Staff Workflow** |
| TC026 | Verify staff can approve a student's withdrawal request. | The application status is changed to "WITHDRAWN". If the student had accepted, the slot is freed. | PASS |
| TC027 | Verify staff can generate a report of all internships, with an option to filter by status or major. | A correctly filtered list of internships with all details is displayed. | PASS |
| TC028 | Verify staff can generate a statistical report. | The report displays key metrics (e.g., total internships, counts for PENDING, APPROVED, FILLED). | PASS |
| **System** |
| TC029 | Verify an internship's status automatically changes to "FILLED" when all slots are taken. | The status is updated, and no more applications are accepted for that internship. | PASS |
| TC030 | Verify the system handles invalid user input gracefully (e.g., non-numeric where number expected, invalid date formats). | Error messages are shown for invalid formats (e.g., dates, numbers) without crashing. | PASS |
| TC031 | Verify application limits (e.g., student applications, company internships) are enforced. | Error messages are displayed when a user tries to exceed a defined limit. | PASS |