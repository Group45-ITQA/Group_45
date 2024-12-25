package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;
import org.testng.Assert;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
public class PostBookSteps {

    private RequestSpecification request;
    private Response response;
    private Book bookDetails;
    int bookCounter = 4;

    int bookCounter = 4;

    @Step("Setting up admin authentication for POST API")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as an admin user to add a new book")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Setting up user authentication for POST API")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as a regular user to add a new book")
    public void setupUserAuthentication() {
        request = AuthenticationUtils.getUserAuthenticatedRequest();
    }

    @Given("I am not authenticated")
    public void setupNoAuthentication() {
        request = AuthenticationUtils.getUnauthenticatedRequest();
    }

    @Step("Providing valid book details")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have valid book details")
    public void setupBookDetails() {
        String title = "Test Book " + System.currentTimeMillis();
        String author = "Test Author " + System.currentTimeMillis();
        bookDetails = new Book(title, author);
    }

    @Step("Providing invalid book details")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have book details with numeric author")
    public void setupNumericAuthorDetails() {
        bookDetails = new Book("Test Book " + bookCounter, "12345");
        bookCounter++;
    }

    @Step("Adding the book to the system to ensure it exists")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have added the book to the system")
    public void addBookToSystem() {
        response = request
                .contentType("application/json")
                .body(bookDetails)
                .when()
                .post(BOOKS_ENDPOINT);

        Assert.assertEquals(response.getStatusCode(), 201, "Failed to add the book to the system.");
    }

    @Step("Sending POST request to create a new book")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to create a book")
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

    @Step("Sending POST request to create a duplicate book")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to add the same book again")
    public void createDuplicateBook() {
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
    @Then("the create book response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Step("Verifying response contains valid created book details")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should contain the created book details")
    public void verifyCreatedBookDetails() {
        Book createdBook = response.as(Book.class);

        Assert.assertNotNull(createdBook, "Created book should not be null");
        Assert.assertNotNull(createdBook.getId(), "Created book ID should not be null");
        Assert.assertEquals(createdBook.getTitle(), bookDetails.getTitle(), "Book title should match");
        Assert.assertEquals(createdBook.getAuthor(), bookDetails.getAuthor(), "Book author should match");
    }

    @Step("Verifying response status code for duplicate book")
    @Severity(SeverityLevel.CRITICAL)
    @Then("the duplicate book response status code should be {int}")
    public void verifyDuplicateResponseStatusCode(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Expected status code does not match for duplicate book.");
    }

    @Step("Verifying response indicates the book is already added")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should indicate the book is already added")
    public void verifyDuplicateBookResponseMessage() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Book Already Exists") ||
                        responseBody.contains("duplicate"),
                "Response should indicate the book is already added.");
    }

    @Step("Verifying response indicates invalid author input")
    @Severity(SeverityLevel.CRITICAL)
    @Then("the response should indicate invalid author input")
    public void verifyInvalidAuthorInputMessage() {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Invalid") ||
                        responseBody.contains("invalid"),
                "Response should indicate invalid author input");
    }
}
