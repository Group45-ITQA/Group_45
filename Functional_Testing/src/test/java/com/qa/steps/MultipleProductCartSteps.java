package com.qa.steps;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.utils.DriverManager;

public class MultipleProductCartSteps {
    private ProductPage productPage;
    private LoginPage loginPage;

    @Before
    public void setup() {
        productPage = new ProductPage();
        loginPage = new LoginPage();
    }

    @Given("I am logged in and on the products page")
    public void i_am_logged_in_on_products_page() {
        loginPage.login();
    }

    @When("I add {int} products to the cart")
    public void i_add_multiple_products_to_cart(int numberOfProducts) {
        productPage.addMultipleProductsToCart(numberOfProducts);
    }

    @Then("I should see {int} products in the cart")
    public void verify_multiple_products_in_cart(int expectedCount) {
        Assert.assertTrue(productPage.areProductsAddedToCart(expectedCount),
                "Expected " + expectedCount + " products in cart");
        Assert.assertEquals(productPage.getCartCount(), String.valueOf(expectedCount));
    }

    @After
    public void cleanup() {
        DriverManager.quitDriver();
    }
}