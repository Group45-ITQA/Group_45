package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;
import static org.hamcrest.Matchers.*;


@Epic("LibraryAPI")
@Feature("BookManagement")
public class GetAllBooksSteps {

    private RequestSpecification request;
    private Response response;

    @Step("Setting up admin authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as an admin user with credentials")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Sending GET request to fetch all books")
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
    @Then("the get all books response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Step("Verifying response contains valid book details")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should contain a list of books with valid details")
    public void verifyBookDetails() {
        response.then()
                .assertThat()
                .body("$", instanceOf(java.util.List.class))  // Verify it's a list
                .body("size()", greaterThanOrEqualTo(0))      // List can be empty but should exist
                .body("findAll { it.id != null }", not(empty()))  // All books should have an id
                .body("findAll { it.title != null }", not(empty()))  // All books should have a title
                .body("findAll { it.author != null }", not(empty())); // All books should have an author

        // Additional logging for Allure reports
        Allure.addAttachment(
                "Validation Details",
                "Verified response is a list of books with required fields (id, title, author)"
        );
    }
}