package com.qa.steps;

import com.qa.utils.StepUtils;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("GetInvalidBook")
public class GetBookByInvalidIdSteps {

    @When("I send a request to get the book with a non-integer ID {string}")
    public void getBookByInvalidId(String invalidId) {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .get(BOOKS_ENDPOINT + "/" + invalidId)
        );
    }

}