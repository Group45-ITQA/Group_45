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
public class UpdateBookSteps {
    private RequestSpecification request;
    private Response response;
    private Integer storedBookId;
    private Book bookToUpdate;
    private static final String TEST_BOOK_AUTHOR = "Test Author For Updates";

    @Given("I am authenticated as an admin user to update a book")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Given("I have a valid book ID to update")
    public void getValidBookId() {
        storedBookId = TestDataSetup.createTestBook(request, "Initial Test Book", TEST_BOOK_AUTHOR);

        // Log the created book details for debugging
        String debugInfo = String.format("Created test book - ID: %d", storedBookId);
        System.out.println(debugInfo);
        Allure.addAttachment("Test Book Creation", debugInfo);
    }

    @When("I send a PUT request to update the book with the stored ID")
    public void updateBookWithStoredId() {
        String uniqueTitle = BookUtils.generateUniqueTitle("Updated Title");
        bookToUpdate = new Book(uniqueTitle, TEST_BOOK_AUTHOR);
        bookToUpdate.setId(storedBookId);

        response = request
                .body(bookToUpdate)
                .when()
                .put(BOOKS_ENDPOINT + "/" + storedBookId);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @When("I send a PUT request to update a non-existent book with ID {string}")
    public void updateNonExistentBook(String id) {
        String uniqueTitle = BookUtils.generateUniqueTitle("Test Title");
        bookToUpdate = new Book(uniqueTitle, "Test Author");
        bookToUpdate.setId(Integer.valueOf(id));

        response = request
                .body(bookToUpdate)
                .when()
                .put(BOOKS_ENDPOINT + "/" + id);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @When("I send a PUT request to update a book with invalid ID {string}")
    public void updateBookWithInvalidId(String invalidId) {
        String uniqueTitle = BookUtils.generateUniqueTitle("Test Title");
        bookToUpdate = new Book(uniqueTitle, "Test Author");
        try {
            bookToUpdate.setId(Integer.valueOf(invalidId));
        } catch (NumberFormatException e) {
            // Expected for invalid ID test case
        }

        response = request
                .body(bookToUpdate)
                .when()
                .put(BOOKS_ENDPOINT + "/" + invalidId);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Then("the update book response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Then("the response should contain updated book details")
    public void verifyUpdatedBookDetails() {
        Book responseBook = response.as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertEquals(responseBook.getId(), bookToUpdate.getId(), "Book ID should match");
        Assert.assertEquals(responseBook.getTitle(), bookToUpdate.getTitle(), "Book title should match");
        Assert.assertEquals(responseBook.getAuthor(), bookToUpdate.getAuthor(), "Book author should match");
    }

    @And("the response should indicate the book was not found")
    public void verifyBookNotFoundResponse() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(
                responseBody.contains("not found") ||
                        responseBody.contains("Book not found"),
                "Response should indicate book was not found"
        );
    }

    @And("the response should indicate invalid input")
    public void verifyInvalidInputResponse() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Invalid") || responseBody.contains("Bad Request"),
                "Response should indicate invalid input");
    }
}