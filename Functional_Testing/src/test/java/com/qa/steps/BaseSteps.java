package com.qa.steps;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.en.*;
import io.qameta.allure.*;

@Epic("Base Features")
public class BaseSteps {

    private LoginPage loginPage = new LoginPage();
    private ProductPage productPage = new ProductPage();

    @Given("I am logged in and on the products page for filtering")
    @Given("I am logged in and on the products page for cart management")
    @Given("I am logged in and on the products page for product navigation")
    @Given("I am logged in and on the products page for image verification")

    @Given("I am logged in and on the products page")
    public void i_am_logged_in_and_on_products_page() {
        loginPage.login();
        if (!productPage.verifyProductPageIsDisplayed()) {
            DriverManager.quitDriver();
            throw new AssertionError("Product Page is not displayed after login.");
        }
    }
}