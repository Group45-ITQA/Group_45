package com.qa.utils;

import io.restassured.response.Response;
import org.testng.Assert;
import io.qameta.allure.Step;

public class ResponseValidator {
    @Step("Verifying response status code is {expectedStatusCode}")
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode()
        );
    }
}