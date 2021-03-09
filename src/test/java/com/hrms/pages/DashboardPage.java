package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(css = "a#welcome")
    public WebElement welcomeAdmin;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    public List<WebElement> tableRows;

    @FindBy(linkText = "PIM")
    public WebElement PIMButton;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployeeBtn;

    @FindBy(xpath = "//div[@class='menu']/ul/li")
    public List<WebElement>dashTabs;


    public void clickOnPIM(){
        jsClick(PIMButton);
    }
    public List<String>getDashTabs(){
        List<String>dashTabsText=new ArrayList<>();
        for (WebElement dashTab:dashTabs) {
            dashTabsText.add(dashTab.getText());


        }
        return dashTabsText;
    }


    public void clickONAddEmployeeBtn(){
        jsClick(addEmployeeBtn);
    }

    public DashboardPage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }

}
