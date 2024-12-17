package com.qa.steps;

import com.qa.pages.CartPage;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.utils.DriverManager;

public class CartSteps {
    private CartPage cartPage;

    @Before
    public void setup() {
        cartPage = new CartPage();
    }

    @Then("the checkout button should be disabled")
    public void the_checkout_button_should_be_disabled() {
        Assert.assertFalse(cartPage.isCheckoutButtonEnabled(), "Checkout button should be disabled when cart is empty");
    }

    @Then("I should not be able to proceed to checkout")
    public void i_should_not_be_able_to_proceed_to_checkout() {
        cartPage.attemptToCheckout();
        Assert.assertTrue(cartPage.isCartEmptyErrorMessageDisplayed(), "Cart empty error message should be displayed");
    }

    @After
    public void cleanup() {
        DriverManager.quitDriver();
    }
}