package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.testdata.TestDataSetup;
import org.testng.Assert;

import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("DeleteBook")
public class DeleteBookSteps {
    private Integer storedBookId;
    private static final String TEST_BOOK_AUTHOR = "Test Author For Deletion";

    @Given("I have a valid book ID to delete")
    public void getValidBookId() {
        storedBookId = TestDataSetup.getFirstBookId();
        Assert.assertNotNull(storedBookId, "No test books found in database");
    }

    @When("I send a DELETE request to remove the book with the stored ID")
    public void deleteBookWithStoredId() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .delete(BOOKS_ENDPOINT + "/" + storedBookId)
        );
    }
}