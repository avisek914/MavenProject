@featureTag
Feature: Login Functionality

   @validCredentials @smoke
  Scenario: Login with valid credentials
#    Given navigate to HRMS login page
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
#    And Quit the browser

    @regression @multipletags
  Scenario: Login with invalid credentials
#    Given navigate to HRMS login page
    When enter invalid credentials
    And click on login button
    Then verify error message
#    And Quit the browser


