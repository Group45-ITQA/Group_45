package com.qa.steps;

import com.qa.utils.StepUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import com.qa.utils.BookUtils;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("UpdateNonExistentBook")
public class UpdateBookNonExistentSteps {
    private Book bookToUpdate;

    @When("I send a PUT request to update a non-existent book with ID {string}")
    public void updateNonExistentBook(String id) {
        String uniqueTitle = BookUtils.generateUniqueTitle("Non existent Test Title");
        bookToUpdate = new Book(uniqueTitle, "Test Author");
        bookToUpdate.setId(Integer.valueOf(id));

        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .body(bookToUpdate)
                        .when()
                        .put(BOOKS_ENDPOINT + "/" + id)
        );
    }

    @Then("the response should indicate the book was not found")
    public void verifyBookNotFoundResponse() {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(),
                "not found",
                "Book not found"
        );
    }
}