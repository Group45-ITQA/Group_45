package com.qa.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import static com.qa.config.TestConfig.*;

@CucumberOptions(
        // Feature files location - for all team members
        features = "src/test/resources/features",

        // Step definitions package - common for all
        glue = "com.qa.steps",

        // Tags for all API types
        tags = "@GetAllBooks or @GetSingleBook or @CreateBook or @UpdateBook or @DeleteBook",

        // Reporting configuration used by all tests
        plugin = {
                "pretty",
                "html:target/cucumber-reports/library-api-tests.html",
                "json:target/cucumber-reports/library-api-tests.json",
                "testng:target/testng-cucumber-reports/library-api-tests.xml"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    public void setupAPITests() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}