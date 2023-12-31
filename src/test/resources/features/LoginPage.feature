@LoginPage
Feature: Login page feature

@DEPCPS-T2
Scenario: Login page validation
Given user is on login page
When user gets the title of the page
Then page title should be "Patient Medical Record System"

  @DEPCPS-T4
  Scenario Outline: Register an user
    Given user is on login page
    And user clicks on "Register" button
    When user is able to see "First Name"
    And I get the Registration details for "<Parameter>"
    When I enter "FirstName"
    When user is able to see "Last Name"
    When I enter "LastName"
    When user is able to see "Email"
    When I enter "Email"
    When user is able to see "Password"
    When I enter "Password"
    And user clicks on "register user" button
    And validate user is registered
    Examples:
      |Parameter |
      |data01|


  @DEPCPS-T3
Scenario Outline: Login with correct credentials
Given user is on login page
When I enter username and password with "<Parameter>"
And user clicks on "Login" button
Then user gets the title of the page
Then the login should be successful
And page title should be "Patients Report"
  Examples:
    |Parameter |
    |valid credentials|
    |invalid credentials|



 #   When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location "Impatient" session
#    And user clicks on "Login" button
#    Then the user inspects the “Home” page
#    And I click on the "register patient"
#    And I enter details for “patient’s name”
#    And I enter details for “patient’s gender”
#    And I enter details for “patient’s birth date”
#    And I enter details for “patient’s gender”
#    And I enter details for “patient’s address”
#    And I enter details for “patient phone number”
#    And I enter details for “patient relatives”
#    And click on confirm
#    And I enter all the details for the patient
#    And I click on the "Confirm" button
#
#  @T4
#  Scenario: Capture Vitals
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on “Login” button
#    Then the user inspects the “Home” page
#    And I click on the "Capture Vitals"
#    And I enter the “patient ID” or “patient name”
#    And user should be able to see the patient details after we entered the details of patient
#    And click on “Find Another Patient” if user want to find the details of another patient
#    And follow the above-mentioned steps
#    And I enter the “patient ID” or “patient name”
#    And user should be able to see the patient details after we entered the details of patient
#
#
#    And user should be able to search the patient details by entering the ID or name if patient is registered
#
#
#  @T5
#  Scenario: Reports
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on “Login” button
#    Then the user inspects the “Home” page
#    And I click on the reports
#    Then user should be able to see the reports
#
#  @T6
#  Scenario: Data Management
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on “Login” button
#    Then the user inspects the “Home” page
#    And I click on the Data Management
#    And I click on the Merge Patient Electronic Records
#    And I enter details for “Patient IDs” of two electronic records to merge
#    And click on “Continue”
#
#  @T7
#  Scenario: Configure Metadata
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on “Login” button
#    Then the user inspects the “Home” page
#    And I click on the Configure Metadata
#    And I click on the Manage Concept Dictionary option under “Concepts”
#    And I click on the Manage Concepts
#    And I click on the “add new concepts”
#    And I enter the details for “name”
#    And I select the details for “Class”
#    And I check the details for “ Is set”
#    And I select the DataType
#    And I click on Add and enter the “concept name”
#    And I click on “Save Concept”
#
#  @T8
#  Scenario: System Administration
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on “Login” button
#    Then the user inspects the “Home” page
#    And click on manage extensions
#    And I click on the action (Disable) option
#    And user should be able to see the pop-message after disable the Extension ID
