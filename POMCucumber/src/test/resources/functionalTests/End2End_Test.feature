Feature: UMS login

Scenario Outline: Successful Login with Valid Credentials
    Given user is on ums loginpage
    When user enters "<username>" and password details
    When user clicks on Sign in button
    Then user lands on dashboard page
Examples:
  | username| 
  | test@usr| 
  | dhdj@fj | 
  | gpu@ums	|