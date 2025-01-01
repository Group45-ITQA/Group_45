package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.models.Book;
import com.qa.utils.BookUtils;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("UpdateInvalidBook")
public class UpdateBookInvalidIdSteps {
    private Book bookToUpdate;

    @When("I send a PUT request to update a book with invalid ID {string}")
    public void updateBookWithInvalidId(String invalidId) {
        String uniqueTitle = BookUtils.generateUniqueTitle("Invalid Test Title");
        bookToUpdate = new Book(uniqueTitle, "Test Author");

        try {
            bookToUpdate.setId(Integer.valueOf(invalidId));
        } catch (NumberFormatException e) {
            // Expected for invalid ID test case
        }

        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .body(bookToUpdate)
                        .when()
                        .put(BOOKS_ENDPOINT + "/" + invalidId)
        );
    }
}