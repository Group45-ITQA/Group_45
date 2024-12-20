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
@Owner("malithi")
public class GetBookByIdSteps {
    private RequestSpecification request;
    private Response response;
    private Integer storedBookId; // Add this to store the ID

    @Given("I am authenticated as an admin user to get a specific book")
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Given("I have a valid book ID from the created books")
    public void getValidBookId() {
        storedBookId = TestDataSetup.getFirstBookId();
        Assert.assertNotNull(storedBookId, "No test books were created successfully");
    }

    @When("I send a request to get the book with the stored ID")
    public void getBookById() {
        response = request
                .when()
                .get(BOOKS_ENDPOINT + "/" + storedBookId);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Then("the get book by id response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Then("the response should contain valid book details")
    public void verifyBookDetails() {
        Book responseBook = response.as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertNotNull(responseBook.getId(), "Book ID should not be null");
        Assert.assertEquals(responseBook.getId(), storedBookId, "Book ID should match the requested ID");
        Assert.assertEquals(responseBook.getTitle(), "Test Book 1", "Book title should match");
        Assert.assertEquals(responseBook.getAuthor(), "Test Author 1", "Book author should match");
    }
}