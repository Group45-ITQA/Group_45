package com.qa.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.models.Book;
import com.qa.testdata.TestDataSetup;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("GetValidBook")
public class GetBookByIdSteps {
    private Integer storedBookId;

    @Given("I have a valid book ID from the created books")
    public void getValidBookId() {
        storedBookId = TestDataSetup.getFirstBookId();
        Assert.assertNotNull(storedBookId, "No test books were created successfully");
    }

    @When("I send a request to get the book with the stored ID")
    public void getBookById() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .get(BOOKS_ENDPOINT + "/" + storedBookId)
        );
    }

    @Then("the response should contain valid book details")
    public void verifyBookDetails() {
        Book responseBook = BaseSteps.getResponse().as(Book.class);

        Assert.assertNotNull(responseBook, "Book should not be null");
        Assert.assertNotNull(responseBook.getId(), "Book ID should not be null");
        Assert.assertEquals(responseBook.getId(), storedBookId, "Book ID should match the requested ID");
        Assert.assertEquals(responseBook.getTitle(), "Test Book 1", "Book title should match");
        Assert.assertEquals(responseBook.getAuthor(), "Test Author 1", "Book author should match");
    }
}