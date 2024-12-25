package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.models.Book;
import com.qa.testdata.TestDataSetup;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
public class DeleteBookSteps {
    private RequestSpecification request;
    private Response response;
    private Integer storedBookId;

    @Given("I am authenticated as a regular user to delete a book")
    public void setupUserAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequestAsUser();
    }

    @Given("I am authenticated as an admin user to delete a book")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Given("I have a valid book ID to delete")
    public void getValidBookId() {
        storedBookId = TestDataSetup.getFirstBookId();
        Assert.assertNotNull(storedBookId, "No test books were created successfully");
    }

    @When("I send a request to delete the book")
    public void deleteBook() {
        response = request
                .when()
                .delete(BOOKS_ENDPOINT + "/" + storedBookId);

        Allure.addAttachment("Response Body", response.getBody().asString());
    }

    @When("I send a request to delete a book with ID {string}")
    public void deleteBookWithId(String bookId) {
        response = request
                .when()
                .delete(BOOKS_ENDPOINT + "/" + bookId);

        Allure.addAttachment("Response Body", response.getBody().asString());
    }

    @Then("the delete book response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Then("the response should contain a permission denied message")
    public void verifyPermissionDeniedMessage() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("forbidden") ||
                        responseBody.contains("Forbidden") ||
                        responseBody.contains("Access denied"),
                "Response should contain a permission denied message");
    }

    @Then("the response should contain a book not found message")
    public void verifyBookNotFoundMessage() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("not found") ||
                        responseBody.contains("Not Found"),
                "Response should contain a not found message");
    }

    @Then("the response should contain an invalid ID format message")
    @Step("Verify invalid ID format message")
    public void verifyInvalidIdFormatMessage() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Invalid") ||
                        responseBody.contains("invalid") ||
                        responseBody.contains("Bad Request"),
                "Response should contain an invalid format message");

        Allure.addAttachment("Response Body", responseBody);
    }

    @Then("the book should no longer exist in the system")
    @Step("Verify book deletion")
    public void verifyBookNoLongerExists() {
        // Try to get the deleted book
        Response getResponse = AuthenticationUtils.getAuthenticatedRequest()
                .when()
                .get(BOOKS_ENDPOINT + "/" + storedBookId);

        Allure.addAttachment("Get Deleted Book Response", getResponse.getBody().asString());

        // Verify that the book is not found (404)
        Assert.assertEquals(getResponse.getStatusCode(), 404,
                "Deleted book should not be retrievable");

        String responseBody = getResponse.getBody().asString();
        Assert.assertTrue(responseBody.contains("not found") ||
                        responseBody.contains("Not Found"),
                "Get request for deleted book should return not found message");
    }
}