package com.qa.steps;

import com.qa.pages.ProductDetailsPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Product Features")
@Feature("Product Details Cart Integration")
@Story("Add to Cart from Product Details")
public class ProductDetailsCartSteps {
    private final ProductDetailsPage productDetailsPage;

    public ProductDetailsCartSteps() {
        productDetailsPage = new ProductDetailsPage();
    }

    @When("I am on the product details page")
    public void verify_product_details_page() {
        Assert.assertTrue(productDetailsPage.isProductDetailsPageDisplayed(),
                "Product details page is not displayed");
    }

    @And("I click the Add to Cart button")
    public void i_click_add_to_cart_button() {
        productDetailsPage.clickAddToCartButton();
    }

    @Then("I should see the cart count increase to {string}")
    public void verify_cart_count(String expectedCount) {
        Assert.assertEquals(productDetailsPage.getCartCount(), expectedCount,
                "Cart count does not match expected value");
    }

    @And("the Add to Cart button should change to Remove")
    public void verify_button_changed_to_remove() {
        Assert.assertTrue(productDetailsPage.isRemoveButtonDisplayed(),
                "Add to Cart button did not change to Remove");
    }

    @Then("the product details should display correctly")
    public void verify_product_details() {
        Assert.assertTrue(productDetailsPage.isProductDetailsPageDisplayed(),
                "Product details are not displayed correctly");
    }
}