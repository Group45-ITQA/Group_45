package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Shopping Cart Features")
@Feature("Product Cart Management")
@Story("Add All Products Functionality")
public class AddAllProductsSteps {
    private final ProductPage productPage;
    private final CartPage cartPage;
    private int numberOfProductsAdded;

    public AddAllProductsSteps() {
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @When("I add all available products to the cart")
    public void i_add_all_products_to_cart() {
        numberOfProductsAdded = productPage.addAllProductsToCart();
    }

    @Then("all products should be in the cart")
    public void verify_all_products_in_cart() {
        String actualCount = cartPage.getItemCount();
        Assert.assertEquals(actualCount, String.valueOf(numberOfProductsAdded),
                "Not all products were added to cart. Expected: " + numberOfProductsAdded +
                        ", but got: " + actualCount);
    }
}