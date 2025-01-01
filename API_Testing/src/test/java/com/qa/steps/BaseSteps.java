package com.qa.steps;

import com.qa.utils.ResponseValidator;
import com.qa.utils.AuthenticationUtils;
import com.qa.utils.StepUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.*;

@Epic("LibraryAPI")
@Feature("Common")
public class BaseSteps {

    private static RequestSpecification request;
    private static Response response;

    @Given("I am authenticated as {string} user")
    public void authenticate(String userType) {
        switch(userType.toLowerCase()) {
            case "admin":
                request = AuthenticationUtils.getAuthenticatedRequest();
                break;
            case "regular":
                request = AuthenticationUtils.getUserAuthenticatedRequest();
                break;
            case "no":
            case "unauthenticated":
                request = AuthenticationUtils.getUnauthenticatedRequest();
                break;
            default:
                throw new IllegalArgumentException("Unsupported user type: " + userType);
        }
    }

    @Then("The status code of the response should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        ResponseValidator.verifyStatusCode(response, expectedStatusCode);
    }

    @Before
    public void setup() {
        request = null;
        response = null;
    }

    public static RequestSpecification getRequest() {
        return request;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response newResponse) {
        response = newResponse;
        StepUtils.attachResponseToAllure(response);
    }
}