Feature: Add Employee Functionality


  Background:
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM
    And click on add employee button


   @addEmployeeWithoutLogin
  Scenario: Add employee without login details
    Then enter first and last name
    And click on save button
    Then verify empoyee is added successfully

  @parameter
  Scenario: Add employee with login details
    Then enter first Name "Ali" ,middle Name "Kumar" and last name "Singh"
    And Verify that "Ali Kumar Singh" is added successfully

@DataDrivenTestExample
  Scenario Outline: Adding multiple employees without login details
    When enter "<FirstName>", "<MiddleName>" and "<LastName>"
    And click on save button
    Then Verify "<FirstName>","<MiddleName>" and "<LastName>" is added succesfully

    Examples:
    |FirstName|MiddleName|LastName|
    |Abhishek |j         |Rajdhami|
    |John     |f         |Kennedy |

  @dtWithHeader
  Scenario: Adding multiple employees at one execution
    When add multiple employees and verify they are added successfully
      |FirstName|MiddleName|LastName|EmployeeId|
      |Abhishek |s         |Rajdhami|12345     |
      |John     |g         |Toronto |23456     |


  @excelTask
Scenario: Adding multiple employees from excel
    When add multiple employees from excel "AddEmployee" sheet and verify they are added

    @db @regression
    Scenario: Adding Employee and database validation
    And enter first Name "john" ,middle Name "Kumar" and last name "Singh"
    And capture employeeId
    And click on save button
    Then collect employee data from hrms database
    And verify data from db and ui is matched

