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
public class PostBookSteps {

    private RequestSpecification request;
    private Response response;
    private Book bookDetails;

    @Step("Setting up admin authentication for POST API")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I am authenticated as an admin user to add a new book")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Providing valid book details")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have valid book details")
    public void setupBookDetails() {
        String title = "Test Book " + System.currentTimeMillis();
        String author = "Test Author " + System.currentTimeMillis();
        bookDetails = new Book(title, author);

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

    @Severity(SeverityLevel.CRITICAL)
    @Then("the create book response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, 201);
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
}
