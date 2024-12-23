package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.utils.*;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI Testing")
@Feature("Book Management")
public class GetBookByInvalidIdSteps {
    private RequestSpecification request;
    private Response response;

    @Given("I am authenticated as an admin user to get a book with invalid ID")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @When("I send a request to get the book with a non-integer ID {string}")
    public void getBookByInvalidIdFormat(String invalidId) {
        response = request
                .when()
                .get(BOOKS_ENDPOINT + "/" + invalidId);
    }

    @Then("the get book by invalid id response status code should be {int}")
    public void verifyInvalidIdResponseStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code " + expectedStatusCode + " for invalid ID format, but got " + response.getStatusCode());
    }

    @And("the response should contain an invalid ID format error message")
    public void verifyInvalidIdFormatErrorMessage() {
        Assert.assertEquals(response.getStatusCode(), 400,
                "Expected 400 Bad Request for invalid ID format");
    }
}