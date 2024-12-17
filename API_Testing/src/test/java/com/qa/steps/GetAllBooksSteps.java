package com.qa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static com.qa.config.TestConfig.*;

public class GetAllBooksSteps {
    private RequestSpecification request;
    private Response response;


    @Given("I am authenticated as an admin user with credentials")
    public void setupAdminAuthentication() {
        // Setting up authentication - example for other team members
        request = given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .contentType("application/json");
    }

    @When("I send a request to get all books from the library")
    public void getAllBooks() {
        // Making the API request - example of using BOOKS_ENDPOINT
        response = request
                .when()
                .get(BOOKS_ENDPOINT);
    }

    @Then("the API response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        // Verifying response code - common for all API tests
        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()
        );
    }

    @And("the response should contain a list of all books")
    public void verifyResponseContainsList() {
        // Example of response validation - useful for GET and POST responses
        List<Map<String, Object>> books = response.jsonPath().getList("$");
        Assert.assertNotNull(books, "Response should contain a list of books");
    }

    @And("each book in the response should have the required fields")
    public void verifyBookFields() {
        // Example of detailed response validation - pattern for other tests
        List<Map<String, Object>> books = response.jsonPath().getList("$");

        books.forEach(book -> {
            Assert.assertNotNull(book.get("id"), "Book must have an ID");
            Assert.assertNotNull(book.get("title"), "Book must have a title");
            Assert.assertNotNull(book.get("author"), "Book must have an author");
        });
    }
}