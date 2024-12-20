package com.qa.utils;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static com.qa.config.TestConfig.*;

public class AuthenticationUtils {
    public static RequestSpecification getAuthenticatedRequest() {
        return given()
                .auth()
                .basic(ADMIN_USERNAME, PASSWORD)
                .contentType("application/json");
    }
}