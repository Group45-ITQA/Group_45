package com.qa.steps;

import com.qa.pages.HeaderComponent;
import com.qa.pages.CartPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class CommonCartSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private HeaderComponent headerComponent = new HeaderComponent(driver);;
    private CartPage cartPage = new CartPage();

    @When("I navigate to the cart page")
    public void i_navigate_to_cart_page() {
        headerComponent.navigateToCart();
    }

    @When("I proceed to the checkout page")
    @Step("Attempting to checkout")
    public void i_click_on_the_checkout_button() {
        cartPage.attemptToCheckout();
    }

    @Given("I have added {string} to the cart")
    public void i_have_added_item_to_cart(String itemName) {
        cartPage.addItemToCart(itemName);
    }
}