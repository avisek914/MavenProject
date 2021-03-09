#Author: avisek914@gmail.com
  @searchEmployee
  Feature: Search an Employee

    Background:
      Given enter valid credentials
      And click on login button
      When click on PIM
      And click on  employee list button

    @tag
  Scenario: search employee job titles
      When search for job titles
      Then all job title from database are displayed
      And verify data in job title data is matched
