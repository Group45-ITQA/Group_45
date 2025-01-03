package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Shopping Cart Features")
@Feature("Product Cart Management")
@Story("Product Cart Functionality")
public class AddProductToCartSteps {
    private ProductPage productPage;
    private CartPage cartPage;

    public AddProductToCartSteps() {
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @When("I add {string} to the cart")
    public void i_add_product_to_cart(String itemName) {
        productPage.addItemToCart(itemName);
    }
}