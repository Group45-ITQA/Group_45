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
        tags = "@GetAllBooks or @GetSingleBook or @PostBook or @PostWithoutTitle",

        // Reporting configuration used by all tests
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty",
                "rerun:target/failed_scenarios.txt"
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
    }
}