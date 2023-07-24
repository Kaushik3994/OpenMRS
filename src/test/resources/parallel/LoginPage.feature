Feature: Login page feature

@T1
Scenario: Login page validation
Given user is on login page
When user gets the title of the page
Then page title should be "Login"


@T2
Scenario: Login with correct credentials
Given user is on login page
When user enters username "admin"
And user enters password "Admin123"
And user selects location session
And user clicks on Login button
Then user gets the title of the page
And page title should be "Home"
Then the user should be able to view "Find Patient Record"

