package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage extends CommonMethods {
    @FindBy(id = "firstName")
    public WebElement firstNameTextbox;

    @FindBy(id = "middleName")
    public WebElement middleNameTextBox;

    @FindBy(id = "lastName")
    public WebElement lastNameTextBox;

    @FindBy(id = "employeeId")
    public WebElement employeeIdTextBox;

    @FindBy(id = "btnSave")
    public WebElement saveBtn;

    @FindBy(id = "chkLogin")
    public WebElement createLoginCheckbox;

    @FindBy(id = "photofile")
    public WebElement photograph;

    @FindBy (id = "chkLogin")
    public WebElement createLoginDetails;

    @FindBy (id = "user_name")
    public WebElement usernameCreate;

    @FindBy (id = "user_password")
    public WebElement userPassword;

    @FindBy (id = "re_password")
    public WebElement rePassword;

    public void enterFirstAndLastName(String firstName,String lastName){
        sendText(firstNameTextbox,firstName);
        sendText(lastNameTextBox,lastName);

    }
    public  void enterFirstMiddleAndLastName(String firstName,String middleName,String lastName){
        sendText(firstNameTextbox,firstName);
        sendText(middleNameTextBox,middleName);
        sendText(lastNameTextBox,lastName);
    }

    public void enterEmployeeId(String employeeId){
        sendText(employeeIdTextBox,employeeId);
    }
    public void clickSaveBtn(){
        jsClick(saveBtn);
    }

    public AddEmployeePage(){
        PageFactory.initElements(driver,this);
    }

}
