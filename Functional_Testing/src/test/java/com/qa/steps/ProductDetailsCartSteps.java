package com.qa.steps;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.pages.ProductDetailsPage;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.utils.DriverManager;

public class ProductDetailsCartSteps {
    private ProductPage productPage;
    private LoginPage loginPage;
    private ProductDetailsPage productDetailsPage;

    @Before
    public void setup() {
        productPage = new ProductPage();
        loginPage = new LoginPage();
        productDetailsPage = new ProductDetailsPage();
    }

    @And("I am redirected to the product details page")
    public void i_am_redirected_to_product_details() {
        Assert.assertTrue(productDetailsPage.isProductDetailsPageDisplayed(),
                "Product details page is not displayed");
    }

    @And("I click the Add to Cart button")
    public void i_click_add_to_cart_button() {
        productDetailsPage.clickAddToCartButton();
    }

    @Then("the product should be added to the cart")
    public void verify_product_added_to_cart() {
        Assert.assertTrue(productDetailsPage.isProductAddedToCart(),
                "Product was not added to cart successfully");
    }

    @And("I should see the cart count increase to {string}")
    public void verify_cart_count(String expectedCount) {
        Assert.assertEquals(productDetailsPage.getCartCount(), expectedCount,
                "Cart count does not match expected value");
    }

    @And("the Add to Cart button should change to Remove")
    public void verify_button_changed_to_remove() {
        Assert.assertTrue(productDetailsPage.isRemoveButtonDisplayed(),
                "Add to Cart button did not change to Remove");
    }

    @After
    public void cleanup() {
        DriverManager.quitDriver();
    }
}