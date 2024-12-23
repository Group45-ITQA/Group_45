package com.qa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.qa.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:target/failed_scenarios.txt",
                "timeline:target/timeline-results",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
          monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}