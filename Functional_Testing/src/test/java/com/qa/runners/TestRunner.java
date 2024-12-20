package com.qa.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.qa.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
//        tags = "@FirstNameBugTest"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
