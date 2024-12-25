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
public class PostBookWithoutTitleSteps {

    private RequestSpecification request;
    private Response response;
    private Book bookDetails;

    @Step("Setting up admin authentication for POST API without title")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as an admin user to add a book without a title")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Providing book details without a title")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have book details without a title")
    public void setupBookDetailsWithoutTitle() {
        bookDetails = new Book();
        bookDetails.setAuthor("Author Without Title"+ System.currentTimeMillis());
        bookDetails.setTitle("");
    }

    @Step("Sending POST request to create a book without a title")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to create a book without a title")
    public void createBookWithoutTitle() {
        response = request
                .contentType("application/json")
                .body(bookDetails)
                .when()
                .post(BOOKS_ENDPOINT);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Then("the response status code for missing title should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, 400);
    }

    @Step("Verifying response contains title-required error message")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should indicate a title is required")
    public void verifyTitleRequiredErrorMessage() {
        String errorMessage = response.jsonPath().getString("error");
        Assert.assertNotNull(errorMessage, "Error message should not be null");
        Assert.assertTrue(errorMessage.contains("title is required"), "Expected error message about missing title.");
    }
}
