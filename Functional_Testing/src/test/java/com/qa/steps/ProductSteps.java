package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Shopping Cart Features")
@Feature("Product Cart Management")
@Story("Product Cart Functionality")
public class ProductSteps {
    private ProductPage productPage;
    private CartPage cartPage;

    public ProductSteps() {
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @When("I add a product to the cart")
    public void i_add_product_to_cart() {
        productPage.addProductToCart();
    }

    @Then("the cart count should be {string}")
    public void verify_cart_count(String expectedCount) {
        Assert.assertEquals(cartPage.getItemCount(), expectedCount);
    }
}