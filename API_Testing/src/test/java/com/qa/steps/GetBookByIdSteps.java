package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.models.Book;
import static io.restassured.RestAssured.given;
import static com.qa.config.TestConfig.*;
import com.qa.utils.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Owner("malithi")
public class GetBookByIdSteps {
    private RequestSpecification request;
    private Response response;

    @Step("Setting up admin authentication for get book by ID")
    @Given("I am authenticated as an admin user to get a specific book")  // Changed the text
    public void setupAdminAuthentication() {
        request = AuthenticationUtils.getAuthenticatedRequest();
    }

    @Step("Sending GET request to fetch book by ID")
    @When("I send a request to get the book with ID {string}")
    public void getBookById(String id) {
        response = request
                .when()
                .get(BOOKS_ENDPOINT + "/" + id);

        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    @Step("Verifying response status code is {expectedStatusCode}")
    @Then("the get book by id response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Step("Verifying book details in response")
    @Then("the response should contain valid book details")
    public void verifyBookDetails() {
        Book responseBook = response.as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertNotNull(responseBook.getId(), "Book ID should not be null");
        Assert.assertNotNull(responseBook.getTitle(), "Book title should not be null");
        Assert.assertNotNull(responseBook.getAuthor(), "Book author should not be null");

        Assert.assertFalse(responseBook.getTitle().isEmpty(), "Book title should not be empty");
        Assert.assertFalse(responseBook.getAuthor().isEmpty(), "Book author should not be empty");
    }
}