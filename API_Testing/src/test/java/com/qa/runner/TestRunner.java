package com.qa.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import static com.qa.config.TestConfig.*;
import io.qameta.allure.restassured.AllureRestAssured;
import com.qa.testdata.TestDataSetup;

@CucumberOptions(
        // Feature files location - for all team members
        features = "src/test/resources/features",

        // Step definitions package - common for all
        glue = "com.qa.steps",

        // Tags for all API types
        tags = "@GetAllBooks or @GetSingleBook",

        // Reporting configuration used by all tests
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty"
        },
        monochrome = true,
        dryRun = false,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    public void setupAPITests() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(new AllureRestAssured());
        TestDataSetup.setupTestData();

        System.setProperty("allure.results.directory", "target/allure-results");
    }
}