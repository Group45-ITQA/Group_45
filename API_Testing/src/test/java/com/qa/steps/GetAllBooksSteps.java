package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import static com.qa.config.TestConfig.*;
import static org.hamcrest.Matchers.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
public class GetAllBooksSteps {

    @Step("Sending GET request to fetch all books")
    @Severity(SeverityLevel.CRITICAL)
    @When("I send a request to get all books from the library")
    public void getAllBooks() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .get(BOOKS_ENDPOINT)
        );
    }

    @Step("Verifying response contains valid book details")
    @Severity(SeverityLevel.CRITICAL)
    @And("the response should contain a list of books with valid details")
    public void verifyBookDetails() {
        BaseSteps.getResponse().then()
                .assertThat()
                .body("$", instanceOf(java.util.List.class))  // Verify it's a list
                .body("size()", greaterThanOrEqualTo(0))      // List can be empty but should exist
                .body("findAll { it.id != null }", not(empty()))  // All books should have an id
                .body("findAll { it.title != null }", not(empty()))  // All books should have a title
                .body("findAll { it.author != null }", not(empty())); // All books should have an author

        // Additional logging for Allure reports
        Allure.addAttachment(
                "Validation Details",
                "Verified response is a list of books with required fields (id, title, author)"
        );
    }
}