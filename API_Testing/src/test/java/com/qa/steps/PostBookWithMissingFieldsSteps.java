package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;
import org.testng.Assert;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
public class PostBookWithMissingFieldsSteps {

    private RequestSpecification request;
    private Response response;
    private Book bookDetails;

    @Step("Setting up admin authentication for POST API")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as an admin user")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Providing book details without a title")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have book details without a title")
    public void setupBookDetailsWithoutTitle() {
        bookDetails = new Book();
        bookDetails.setAuthor("Author " + System.currentTimeMillis());
        bookDetails.setTitle("");
    }

    @Step("Providing book details without an author")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have book details without an author")
    public void setupBookDetailsWithoutAuthor() {
        bookDetails = new Book();
        bookDetails.setTitle("Title " + System.currentTimeMillis());
        bookDetails.setAuthor("");
    }

    @Step("I send a request to create a book without title")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to create a book without title")
    @When("I send a request to create a book without author")
    public void createBook() {
        response = request
                .contentType("application/json")
                .body(bookDetails)
                .when()
                .post(BOOKS_ENDPOINT);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Step("Verifying the response status code")
    @Severity(SeverityLevel.CRITICAL)
    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Step("Verifying response contains required field error message")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should indicate a {string} is required")
    public void verifyRequiredFieldErrorMessage(String field) {
        String errorMessage = response.jsonPath().getString("error");
        Assert.assertNotNull(errorMessage, "Error message should not be null");
        Assert.assertTrue(errorMessage.contains(field + " is required"), "Expected error message about missing " + field + ".");
    }
}
