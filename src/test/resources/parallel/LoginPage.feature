Feature: Login page feature

@t1
Scenario: Login page validation
Given user is on login page
When user gets the title of the page
Then page title should be "Login"

#Scenario: Forgot Password link
#Given user is on login page
#Then forgot your password link should be displayed
#
@T2
Scenario: Login with correct credentials
Given user is on login page
When user enters username "admin"
And user enters password "Admin123"
And user selects location session
And user clicks on Login button
Then user gets the title of the page
And page title should be "Home"
Then user gets home section "Find Patient Record"

#  @T2
#  Scenario: Login with correct credentials
#    Given user is on login page
#    When user enters username "admin"
#    And user enters password "Admin123"
#    And user selects location “Impatient”session
#    And user clicks on Login button
#    Then the user inspects the “Home” page
#    And page title should be "Home"
#    Then the user should be able to view "Find Patient Record"