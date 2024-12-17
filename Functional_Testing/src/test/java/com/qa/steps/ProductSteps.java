package com.qa.steps;

import com.qa.pages.LoginPage;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.pages.ProductPage;
import com.qa.utils.DriverManager;


public class ProductSteps {
    private ProductPage productPage;
    LoginPage loginPage = new LoginPage();

    @Before
    public void setup() {
        productPage = new ProductPage();
    }

    @Given("I am on the products page")
    public void i_am_on_products_page() {
        loginPage.login();
    }

    @When("I add a product to the cart")
    public void i_add_product_to_cart() {
        productPage.addProductToCart();
    }

    @Then("the cart count should be {string}")
    public void verify_cart_count(String expectedCount) {
        Assert.assertEquals(productPage.getCartCount(), expectedCount);
    }

    @After
    public void cleanup() {
        DriverManager.quitDriver();
    }
}