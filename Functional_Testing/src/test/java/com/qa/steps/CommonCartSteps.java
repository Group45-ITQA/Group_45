package com.qa.steps;

import com.qa.pages.LoginPage;
import com.qa.pages.CartPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonCartSteps {
    private LoginPage loginPage = new LoginPage();
    private CartPage cartPage = new CartPage();

    @Given("I am logged in to the Sauce Demo website")
    public void i_am_logged_in_to_sauce_demo() {
        loginPage.login();
    }

    @When("I navigate to the cart page")
    public void i_navigate_to_cart_page() {
        cartPage.goToCartPage();
    }
}