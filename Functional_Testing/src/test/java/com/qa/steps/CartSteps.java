package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.LoginPage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import com.qa.utils.DriverManager;

public class CartSteps {
    private CartPage cartPage = new CartPage();

    @When("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        cartPage.goToCartPage();
        // Ensure the cart is empty (if not, remove all items)
        if (cartPage.isItemInCart("Sauce Labs Backpack")) {
            cartPage.removeItemFromCart("Sauce Labs Backpack");
        }
        if (cartPage.isItemInCart("Sauce Labs Bike Light")) {
            cartPage.removeItemFromCart("Sauce Labs Bike Light");
        }
    }

    @When("I click on the checkout button")
    public void i_click_on_the_checkout_button() {
        cartPage.attemptToCheckout();
    }

    @Then("I should be able to proceed to the checkout information page")
    public void i_should_be_able_to_proceed_to_the_checkout_information_page() {
        // Verify the URL or the presence of specific elements on the checkout information page
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-one.html"), "Failed to proceed to checkout information page");
    }
}
