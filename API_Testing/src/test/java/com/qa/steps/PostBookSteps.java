package com.qa.steps;

import com.qa.utils.AuthenticationUtils;
import com.qa.utils.BookUtils;
import com.qa.utils.StepUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
@Story("CreateBook")
public class PostBookSteps {
    protected Book bookDetails;
    private RequestSpecification request;
    private Response response;
    int bookCounter = 4;

    @Step("Providing valid book details")
    @Severity(SeverityLevel.CRITICAL)
    @Given("I have valid book details")
    public void setupBookDetails() {
        String title = BookUtils.generateUniqueTitle("Test Book");
        String author = "Test Author";
        bookDetails = new Book(title, author);
    }

    @Step("Sending POST request to create a new book")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to create a book")
    public void createBook() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(bookDetails)
                        .when()
                        .post(BOOKS_ENDPOINT)
        );
    }

    @Step("Verifying response contains valid created book details")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should contain the created book details")
    public void verifyCreatedBookDetails() {
        Book createdBook = BaseSteps.getResponse().as(Book.class);

        Assert.assertNotNull(createdBook, "Created book should not be null");
        Assert.assertNotNull(createdBook.getId(), "Created book ID should not be null");
        Assert.assertEquals(createdBook.getTitle(), bookDetails.getTitle(), "Book title should match");
        Assert.assertEquals(createdBook.getAuthor(), bookDetails.getAuthor(), "Book author should match");
    }

    @Step("Verifying response contains required field error message")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should indicate a {string} is required")
    public void verifyRequiredFieldErrorMessage(String field) {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(),
                field + " is required",
                field + " cannot be empty"
        );
    }

    public Book getBookDetails() {
        return bookDetails;
    }

//    @Given("I am not authenticated")
//    public void setupNoAuthentication() {
//        request = AuthenticationUtils.getUnauthenticatedRequest();
//    }
//
//    @Step("Providing invalid book details")
//    @Severity(SeverityLevel.CRITICAL)
//    @Given("I have book details with numeric author")
//    public void setupNumericAuthorDetails() {
//        bookDetails = new Book("Test Book " + bookCounter, "12345");
//        bookCounter++;
//    }
//
//    @Step("Verifying response indicates invalid author input")
//    @Severity(SeverityLevel.CRITICAL)
//    @Then("the response should indicate invalid author input")
//    public void verifyInvalidAuthorInputMessage() {
//        String responseBody = response.getBody().asString();
//        Assert.assertTrue(responseBody.contains("Invalid") ||
//                        responseBody.contains("invalid"),
//                "Response should indicate invalid author input");
//    }
}