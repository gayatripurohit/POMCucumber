Feature: UMS login

  Scenario: Successful Login with Valid Credentials
    Given user is on ums loginpage
    When user enters login details
    When user clicks on Sign in button
    Then user lands on dashboard page