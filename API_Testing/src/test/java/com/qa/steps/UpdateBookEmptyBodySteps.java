package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import com.qa.utils.StepUtils;
import static com.qa.config.TestConfig.*;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("UpdateBookValidation")
public class UpdateBookEmptyBodySteps {

    @When("I send a PUT request with empty body")
    public void updateBookWithEmptyBody() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .body("{}")
                        .when()
                        .put(BOOKS_ENDPOINT + "/" + UpdateBookSteps.getStoredBookId())
        );
    }

    @And("the response should contain a validation error message")
    public void verifyValidationErrorMessage() {
        StepUtils.validateErrorMessage(BaseSteps.getResponse(),
                "validation error",
                "invalid request body",
                "missing required fields",
                "id is not matched"
        );
    }
}