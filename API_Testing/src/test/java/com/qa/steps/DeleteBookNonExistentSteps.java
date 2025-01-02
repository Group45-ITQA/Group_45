package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.utils.StepUtils;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("DeleteNonExistentBook")
public class DeleteBookNonExistentSteps {

    @When("I send a DELETE request to remove a non-existent book with ID {string}")
    public void deleteNonExistentBook(String id) {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .delete(BOOKS_ENDPOINT + "/" + id)
        );
    }

    @Then("the response should contain book not found message")
    public void verifyBookNotFoundResponse() {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(),
                "not found",
                "Book not found"
        );
    }
}