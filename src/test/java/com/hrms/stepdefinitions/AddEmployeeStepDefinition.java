package com.hrms.stepdefinitions;

import com.hrms.pages.DashboardPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReading;
import com.hrms.utils.GlobalVariables;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddEmployeeStepDefinition extends CommonMethods {
    @When("click on PIM")
    public void click_on_PIM() {
   dashboardPage.clickOnPIM();

    }

    @When("click on add employee button")
    public void click_on_add_employee_button() {
    dashboardPage.clickONAddEmployeeBtn();
    }

    @Then("enter first and last name")
    public void enter_first_and_last_name() {
        addEmployeePage.enterFirstAndLastName("Thor","potter");

    }

    @Then("click on save button")
    public void click_on_save_button() {
        addEmployeePage.clickSaveBtn();

    }

    @Then("verify empoyee is added successfully")
    public void verify_empoyee_is_added_successfully() {
        String actualProfileName = personalDetailPage.getUserProfileName();
        Assert.assertEquals("verifying profile name", "Thor Potter", actualProfileName);

    }
    @Then("enter first Name {string} ,middle Name {string} and last name {string}")
    public void enter_first_Name_middle_Name_and_last_name(String firstName , String middleName, String lastName) {
   addEmployeePage.enterFirstMiddleAndLastName(firstName,middleName,lastName);
   GlobalVariables.employeeData=firstName+" "+middleName+" "+lastName;
    }

    @Then("Verify that {string} is added successfully")
    public void verify_that_is_added_successfully(String fullName) {
        String actualProfileName = personalDetailPage.getUserProfileName();
        Assert.assertEquals("Verifying profile name ",fullName,actualProfileName);

    }
    @When("enter {string}, {string} and {string}")
    public void enter_and(String firstName, String middleName, String lastName) {
      addEmployeePage.enterFirstMiddleAndLastName(firstName,middleName,lastName);
    }


    @Then("Verify {string},{string} and {string} is added succesfully")
    public void verify_and_is_added_succesfully(String firstName, String middleName, String lastName) {
        String actualProfileName = personalDetailPage.getUserProfileName();
        String fullName=firstName+" "+middleName+" "+lastName;
        Assert.assertEquals("Verifying profile name ",fullName,actualProfileName);
    }


    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) throws InterruptedException {
        List<Map<String,String>> employeeNames=employees.asMaps();
        for (Map<String,String> employeeName:employeeNames){
          String firstName =  employeeName.get("FirstName");
          String middleName =employeeName.get("MiddleName");
          String lastName=employeeName.get("LastName");
          String employeeId=employeeName.get("EmployeeID");
          addEmployeePage.enterFirstMiddleAndLastName(firstName,middleName,lastName);
          addEmployeePage.enterEmployeeId(employeeId);
          addEmployeePage.clickSaveBtn();
          String actualFullName=personalDetailPage.getUserProfileName();
          String expectedFullName=firstName+" "+middleName+" "+lastName;
          Assert.assertEquals("Verifying profile name",expectedFullName,actualFullName);
          dashboardPage.clickONAddEmployeeBtn();
          Thread.sleep(2000);
        }

    }
    @When("add multiple employees from excel {string} sheet and verify they are added")
    public void add_multiple_employees_from_excel_sheet_and_verify_they_are_added(String sheetName) throws InterruptedException {
        List<Map<String,String>>excelData=ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,sheetName);
        for(Map<String,String >excelEmpName:excelData){
            String firstName=excelEmpName.get("FirstName");
            String middleName=excelEmpName.get("MiddleName");
            String lastName=excelEmpName.get("LastName");
            String employeeId=excelEmpName.get("EmployeeId");
            addEmployeePage.enterFirstMiddleAndLastName(firstName,middleName,lastName);
            addEmployeePage.enterEmployeeId(employeeId);
            addEmployeePage.clickSaveBtn();
            String actualFullName=personalDetailPage.getUserProfileName();
            String expectedFullName=firstName+" "+middleName+" "+lastName;
            Assert.assertEquals("Verifying profile name",expectedFullName,actualFullName);
            dashboardPage.clickONAddEmployeeBtn();
            Thread.sleep(2000);
        }
    }

    @When("capture employeeId")
    public void capture_employeeId() {
      GlobalVariables.emp_Id= addEmployeePage.employeeIdTextBox.getAttribute("value");

    }



    @Then("verify data from db and ui is matched")
    public void verify_data_from_db_and_ui_is_matched() {
        String expectedEmployeeData = GlobalVariables.employeeData;
        String actualEmployeeData = "";
        for (Map<String, String> actualEmployeeDataMap : GlobalVariables.dbList) {
            Set<String> keys = actualEmployeeDataMap.keySet();
            for (String key : keys) {
                actualEmployeeData += actualEmployeeDataMap.get(key);
            }
        }
        actualEmployeeData = actualEmployeeData.trim();
        Assert.assertEquals("Verifying Employee Data", actualEmployeeData, expectedEmployeeData);
    }
    }





