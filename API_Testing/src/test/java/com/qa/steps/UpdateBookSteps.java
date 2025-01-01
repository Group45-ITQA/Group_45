package com.qa.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.models.Book;
import com.qa.testdata.TestDataSetup;
import com.qa.utils.*;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
public class UpdateBookSteps {
    private Integer storedBookId;
    private Book bookToUpdate;
    private static final String TEST_BOOK_AUTHOR = "Test Author For Updates";

    @Given("I have a valid book ID to update")
    public void getValidBookId() {
        storedBookId = TestDataSetup.createTestBook(BaseSteps.getRequest(), "Initial Test Book", TEST_BOOK_AUTHOR);
    }

    @When("I send a PUT request to update the book with the stored ID")
    public void updateBookWithStoredId() {
        sendUpdateRequest(storedBookId.toString(), TEST_BOOK_AUTHOR);
    }

    @When("I send a PUT request to update a non-existent book with ID {string}")
    public void updateNonExistentBook(String id) {
        sendUpdateRequest(id, "Test Author");
    }

    @When("I send a PUT request to update a book with invalid ID {string}")
    public void updateBookWithInvalidId(String invalidId) {
        sendUpdateRequest(invalidId, "Test Author");
    }

    @Then("the response should contain updated book details")
    public void verifyUpdatedBookDetails() {
        Book responseBook = BaseSteps.getResponse().as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertEquals(responseBook.getId(), bookToUpdate.getId(), "Book ID should match");
        Assert.assertEquals(responseBook.getTitle(), bookToUpdate.getTitle(), "Book title should match");
        Assert.assertEquals(responseBook.getAuthor(), bookToUpdate.getAuthor(), "Book author should match");
    }

    @And("the response should indicate the book was not found")
    public void verifyBookNotFoundResponse() {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(), "not found", "Book not found");
    }

    @And("the response should indicate invalid input")
    public void verifyInvalidInputResponse() {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(),
                "Invalid",
                "Bad Request",
                "invalid format",
                "malformed"
        );
    }

    private void sendUpdateRequest(String id, String author) {
        String uniqueTitle = BookUtils.generateUniqueTitle("Test Title");
        bookToUpdate = new Book(uniqueTitle, author);

        try {
            bookToUpdate.setId(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            // Expected for invalid ID test case
        }

        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .body(bookToUpdate)
                        .when()
                        .put(BOOKS_ENDPOINT + "/" + id)
        );
    }
}