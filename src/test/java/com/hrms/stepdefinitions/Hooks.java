package com.hrms.stepdefinitions;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before
    public static void startTest(){
        BaseClass.setUp();
    }

    @After

    public static void endTest(Scenario scenario){

        byte[] screenshots;
        if (scenario.isFailed()){
           screenshots = CommonMethods.takeScreenshot("failed/"+scenario.getName());
        }else{
            screenshots = CommonMethods.takeScreenshot("passed/"+scenario.getName());

        }
        scenario.attach(screenshots,"image/png",scenario.getName());


        BaseClass.tearDown();

    }
}
