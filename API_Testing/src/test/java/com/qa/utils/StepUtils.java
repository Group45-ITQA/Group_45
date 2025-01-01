package com.qa.utils;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;

public class StepUtils {

    public static void attachResponseToAllure(Response response) {
        if (response != null && response.getBody() != null) {
            Allure.addAttachment("Response Body", response.getBody().asString());
        }
    }

    public static void validateErrorMessage(Response response, String... expectedPhrases) {
        String responseBody = response.getBody().asString().toLowerCase();
        boolean foundMatch = Arrays.stream(expectedPhrases)
                .map(String::toLowerCase)
                .anyMatch(responseBody::contains);

        Assert.assertTrue(
                foundMatch,
                "Response should contain one of these phrases: '" + String.join("', '", expectedPhrases) + "'"
        );
    }
}