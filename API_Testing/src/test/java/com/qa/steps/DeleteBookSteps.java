package com.qa.steps;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import io.restassured.response.Response;

import java.util.List;

import static com.qa.config.TestConfig.*;
import static io.restassured.RestAssured.given;

@Epic("LibraryAPITesting")
@Feature("BookManagement")
@Story("DeleteBook")
public class DeleteBookSteps {
    private Integer lastBookId;

    @Given("I have a valid book ID to delete")
    public void getLastBookId() {
        Response getResponse = given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .when()
                .get(BOOKS_ENDPOINT);

        List<Integer> bookIds = getResponse.jsonPath().getList("id");
        lastBookId = bookIds.get(bookIds.size() - 1);
    }

    @When("I send a DELETE request to remove the book with the stored ID")
    public void deleteBookWithStoredId() {
        BaseSteps.setResponse(
                BaseSteps.getRequest()
                        .when()
                        .delete(BOOKS_ENDPOINT + "/" + lastBookId)
        );
    }
}