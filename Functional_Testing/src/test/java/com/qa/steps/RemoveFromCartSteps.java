package com.qa.steps;

import com.qa.pages.CartPage;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.utils.DriverManager;

public class RemoveFromCartSteps {
    private CartPage cartPage;

    @Before
    public void setup() {
        cartPage = new CartPage();
    }

    @Given("I have added {string} to the cart")
    public void i_have_added_item_to_cart(String itemName) {
        cartPage.addItemToCart(itemName);
    }

    @When("I remove {string} from the cart")
    public void i_remove_item_from_cart(String itemName) {
        cartPage.removeItemFromCart(itemName);
    }

    @Then("the item {string} should not be visible in the cart")
    public void item_should_not_be_visible_in_cart(String itemName) {
        Assert.assertFalse(cartPage.isItemInCart(itemName),
                "Item '" + itemName + "' should not be visible in cart after removal");
    }

    @After
    public void cleanup() {
        DriverManager.quitDriver();
    }
}