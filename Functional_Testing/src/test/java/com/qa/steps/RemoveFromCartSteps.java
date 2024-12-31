package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import org.testng.Assert;
import com.qa.utils.DriverManager;
import io.qameta.allure.*;

@Epic("Shopping Cart Features")
@Feature("Cart Item Management")
@Story("Remove Items From Cart")
public class RemoveFromCartSteps {
    private CartPage cartPage;

    public RemoveFromCartSteps() {
        cartPage = new CartPage();
    }


    @When("I remove {string} from the cart")
    @Step("Removing item from cart: {0}")
    public void i_remove_item_from_cart(String itemName) {
        cartPage.removeItemFromCart(itemName);
    }

    @Then("the item {string} should not be visible in the cart")
    @Step("Verifying item is removed from cart: {0}")
    public void item_should_not_be_visible_in_cart(String itemName) {
        Assert.assertFalse(cartPage.isItemInCart(itemName),
                "Item '" + itemName + "' should not be visible in cart after removal");
    }
}