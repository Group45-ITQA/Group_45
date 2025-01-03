package com.qa.steps;

import com.qa.utils.StepUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
@Story("CreateBookWithoutTitle")
public class PostBookMissingTitleSteps {
    private Book bookDetails;

    @Given("I have book details without a title")
    public void setupBookDetailsWithoutTitle() {
        bookDetails = new Book();
        bookDetails.setAuthor("Author " + System.currentTimeMillis());
        bookDetails.setTitle("");
    }

    @When("I send a request to create a book without title")
    public void createBookWithoutTitle() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(bookDetails)
                        .when()
                        .post(BOOKS_ENDPOINT)
        );
    }
}