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
@Story("UpdateValidBook")
public class UpdateBookSteps {
    private static Integer storedBookId;
    private Book bookToUpdate;
    private static final String TEST_BOOK_AUTHOR = "Test Author For Updates";

    @Given("I have a valid book ID to update")
    public void getValidBookId() {
        storedBookId = TestDataSetup.getFirstBookId();
        Assert.assertNotNull(storedBookId, "No test books found in database");
    }

    @When("I send a PUT request to update the book with the stored ID")
    public void updateBookWithStoredId() {
        String uniqueTitle = BookUtils.generateUniqueTitle("Test Title");
        bookToUpdate = new Book(uniqueTitle, TEST_BOOK_AUTHOR);
        bookToUpdate.setId(storedBookId);

        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .body(bookToUpdate)
                        .when()
                        .put(BOOKS_ENDPOINT + "/" + storedBookId)
        );
    }

    @Then("the response should contain updated book details")
    public void verifyUpdatedBookDetails() {
        Book responseBook = BaseSteps.getResponse().as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertEquals(responseBook.getId(), bookToUpdate.getId(), "Book ID should match");
        Assert.assertEquals(responseBook.getTitle(), bookToUpdate.getTitle(), "Book title should match");
        Assert.assertEquals(responseBook.getAuthor(), bookToUpdate.getAuthor(), "Book author should match");
    }

    public static Integer getStoredBookId() {
        return storedBookId;
    }
}