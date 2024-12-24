package com.qa.steps;

import com.qa.pages.CartPage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import com.qa.utils.DriverManager;
import io.qameta.allure.*;

@Epic("Shopping Cart Features")
@Feature("Cart Checkout Management")
@Story("Empty Cart Checkout Functionality")
public class CartSteps {
    private CartPage cartPage;

    public CartSteps() {
        cartPage = new CartPage();
    }

    @When("my shopping cart is empty")
    @Step("Verifying cart is empty")
    public void my_shopping_cart_is_empty() {
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
    }

    @When("I click on the checkout button")
    @Step("Attempting to checkout")
    public void i_click_on_the_checkout_button() {
        cartPage.attemptToCheckout();
    }

    @Then("I should not be able to proceed with checkout")
    @Step("Verifying unable to proceed with checkout")
    public void i_should_not_be_able_to_proceed_with_checkout() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html"),
                "User should remain on cart page");
    }
}