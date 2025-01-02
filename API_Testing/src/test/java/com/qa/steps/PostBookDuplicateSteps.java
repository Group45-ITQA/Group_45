package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import org.testng.Assert;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPI")
@Feature("BookManagement")
@Story("CreateDuplicateBook")
public class PostBookDuplicateSteps {

    private final PostBookSteps postBookSteps;

    public PostBookDuplicateSteps(PostBookSteps postBookSteps) {
        this.postBookSteps = postBookSteps;
    }

    @Given("I have added the book to the system")
    public void addBookToSystem() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(postBookSteps.getBookDetails())
                        .when()
                        .post(BOOKS_ENDPOINT)
        );

        Assert.assertEquals(BaseSteps.getResponse().getStatusCode(), 201,
                "Failed to add the book to the system.");
    }

    @When("I send a request to add the same book again")
    public void createDuplicateBook() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .contentType("application/json")
                        .body(postBookSteps.getBookDetails())
                        .when()
                        .post(BOOKS_ENDPOINT)
        );
    }
}