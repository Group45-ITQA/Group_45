package com.qa.utils;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

public class LoginUtils {

    private static LoginPage loginPage = new LoginPage();
    private static ProductPage productPage = new ProductPage();


    // Login method (Reusable across tests)
    public static void loginAndNavigateToProductPage() {
        loginPage.login(); // Logs in and lands on the Product Page
        verifyProductPage(); // Ensure user is on Product Page
    }

    // Verify if the user is on the Product Page
    public static void verifyProductPage() {
        if (!productPage.verifyProductPageIsDisplayed()) {
            throw new AssertionError("Product Page is not displayed after login.");
        }
    }
}
