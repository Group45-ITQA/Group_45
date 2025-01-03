package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("DeleteInvalidBook")
public class DeleteBookInvalidIdSteps {

    @When("I send a DELETE request to remove a book with invalid ID {string}")
    public void deleteBookWithInvalidId(String invalidId) {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .delete(BOOKS_ENDPOINT + "/" + invalidId)
        );
    }
}