package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",//give a path for out feature file
                  glue = "com/hrms/stepdefinitions",//glue the step definitions
                   dryRun = false , //when set true, will run over the feature steps and reveal uniplemented steps
                  tags = "@db", //{""@smoke , "@regression"}multiple tags
                    strict = false, //when set as true, will fail the excecution when undefined step is on scenario
                 plugin = {"pretty", //will print executed steps inside console
                            "html:target/cucumber-default-reports"//generates default html report
                            ,"rerun:target/FailedTests.txt",//generates a txt file with failed tests only
                             "json:target/cucumber.json"//generates json report
                             }

                          )

public class TestRunner {
}
