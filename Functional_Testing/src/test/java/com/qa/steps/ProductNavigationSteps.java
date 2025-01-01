package com.qa.steps;

import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Product Features")
@Feature("Product Details Navigation")
@Story("Product Link Navigation Validation")
public class ProductNavigationSteps {
    private ProductPage productPage;

    public ProductNavigationSteps() {
        productPage = new ProductPage();
    }

    @When("I click on the {string} product link")
    @When("I click on a product name {string}")
    @Step("Clicking on product link: {productName}")
    public void clickProductLink(String productName) {
        productPage.clickProductLink(productName);
    }

    @Then("I should see the correct product details for {string}")
    @Step("Verifying correct product details are displayed for: {productName}")
    public void verifyCorrectProductDetails(String productName) {
        Assert.assertTrue(productPage.isCorrectProductDisplayed(productName),
                "Defect: Wrong product details displayed after clicking " + productName);
    }
}