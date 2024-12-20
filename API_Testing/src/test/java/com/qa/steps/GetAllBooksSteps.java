package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
public class GetAllBooksSteps {

    private RequestSpecification request;
    private Response response;

    @Step("Setting up admin authentication")
    @Given("I am authenticated as an admin user with credentials")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
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
    @Then("the get all books response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }
}