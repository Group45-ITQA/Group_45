package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Shopping Cart Features")
@Feature("Cart Item Management")
@Story("Multiple Products Addition")
public class MultipleProductCartSteps {
    private final ProductPage productPage;
    private final CartPage cartPage;

    public MultipleProductCartSteps() {
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @When("I add {int} products to the cart")
    @Step("Adding {numberOfProducts} products to cart")
    public void i_add_multiple_products_to_cart(int numberOfProducts) {
        productPage.addMultipleProductsToCart(numberOfProducts);
    }

    @Then("I should see {int} products in the cart")
    @Step("Verifying {expectedCount} products in cart")
    public void verify_multiple_products_in_cart(int expectedCount) {
        Assert.assertTrue(productPage.areProductsAddedToCart(expectedCount),
                "Expected " + expectedCount + " products in cart");
        Assert.assertEquals(cartPage.getItemCount(), String.valueOf(expectedCount),
                "Cart count does not match expected number of products");
    }
}