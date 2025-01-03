package com.qa.steps;

import com.qa.utils.BookUtils;
import com.qa.utils.StepUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
@Story("CreateBookWithoutAuthor")
public class PostBookMissingAuthorSteps {
    private Book bookDetails;

    @Given("I have book details without an author")
    public void setupBookDetailsWithoutAuthor() {
        bookDetails = new Book();
        String title = BookUtils.generateUniqueTitle("Test Book without author");
        bookDetails.setTitle(title);
        bookDetails.setAuthor("");
    }

    @When("I send a request to create a book without author")
    public void createBookWithoutAuthor() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(bookDetails)
                        .when()
                        .post(BOOKS_ENDPOINT)
        );
    }
}