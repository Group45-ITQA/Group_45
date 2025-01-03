package com.qa.steps;

import com.qa.pages.ProductDetailsPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Shopping Cart Features")
@Feature("Product Details Cart Management")
@Story("Remove Product From Cart On Details Page")
public class ProductDetailsRemoveSteps {
    private final ProductDetailsPage productDetailsPage;

    public ProductDetailsRemoveSteps() {
        productDetailsPage = new ProductDetailsPage();
    }

    @When("I click the remove button on the product details page")
    public void clickRemoveButtonOnProductDetails() {
        Assert.assertTrue(productDetailsPage.isRemoveButtonDisplayed(),
                "Remove button should be visible before removing item");
        productDetailsPage.clickRemoveButton();
    }

    @Then("the add to cart button should be visible")
    public void verifyAddToCartButtonVisible() {
        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(),
                "Add to Cart button should be visible after removing item");
    }
}