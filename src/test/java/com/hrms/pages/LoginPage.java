package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods{

    @FindBy(id = "txtUsername")
    public WebElement usernameTextbox;

    @FindBy(xpath = "//input[@name='txtPassword']")
    public WebElement passwordTextbox;

    @FindBy(css = "input#btnLogin")
    public WebElement loginBtn;

    @FindBy(id = "spanMessage")
    public WebElement errorMsg;

    public LoginPage() {
        PageFactory.initElements(driver, this);

    }
    public void login(String username,String password){
        sendText(usernameTextbox,username);
        sendText(passwordTextbox,password);

    }
    public void clickONLoginButton(){
        click(loginBtn);
    }
    public  String getErrorMessage(){
       return errorMsg.getText();
    }
}