package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.LoginPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;

public class CartSteps {
    private CartPage cartPage;
    LoginPage loginPage = new LoginPage();

    @Before
    public void setup() {
        cartPage = new CartPage();
    }

    @Given("I am logged in to the Sauce Demo website")
    public void i_am_logged_in_to_sauce_demo() {
        loginPage.login();
    }

    @When("I navigate to the cart page")
    public void i_navigate_to_cart_page() {
        cartPage.goToCartPage();
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
