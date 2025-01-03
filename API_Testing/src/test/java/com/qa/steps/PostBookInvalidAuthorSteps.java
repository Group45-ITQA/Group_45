package com.qa.steps;

import com.qa.utils.BookUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import org.testng.Assert;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
@Story("CreateBookInvalidAuthor")
public class PostBookInvalidAuthorSteps {
    private String requestBody;

    @Given("I have book details with numeric author")
    public void setupNumericAuthorDetails() {
        String title = BookUtils.generateUniqueTitle("Test Book with numeric author");
        requestBody = String.format("{\"title\": \"%s\", \"author\": 12345}", title);
    }

    @When("I send a request to create a book with invalid author")
    public void createBookWithInvalidAuthor() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post(BOOKS_ENDPOINT)
        );
    }
}