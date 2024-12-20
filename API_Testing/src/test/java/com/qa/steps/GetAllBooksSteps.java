package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;
import static com.qa.config.TestConfig.*;

@Epic("Library API Testing")
@Feature("Get Books API")
public class GetAllBooksSteps {
    private RequestSpecification request;
    private Response response;

    @Step("Setting up admin authentication")
    @Given("I am authenticated as an admin user with credentials")
    public void setupAdminAuthentication() {
        request = given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .contentType("application/json");
    }

    @Step("Sending GET request to fetch all books")
    @When("I send a request to get all books from the library")
    public void getAllBooks() {
        response = request
                .when()
                .get(BOOKS_ENDPOINT);

        // Only attach response body
        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Step("Verifying response status code is {expectedStatusCode}")
    @Then("the API response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()
        );
    }
}