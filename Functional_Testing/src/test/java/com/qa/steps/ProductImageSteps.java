package com.qa.steps;

import com.qa.pages.ProductPage;
import com.qa.pages.LoginPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Product Features")
@Feature("Product Image Verification")
public class ProductImageSteps {
    private ProductPage productPage;
    private LoginPage loginPage;

    public ProductImageSteps() {
        productPage = new ProductPage();
        loginPage = new LoginPage();
    }


    @When("I verify the product images")
    @Step("Verifying product images uniqueness")
    public void verifyProductImages() {
        Assert.assertFalse(productPage.hasDuplicateImages(),
                "Defect: Found duplicate product images when all should be unique");
    }

    @Then("all product images should be unique")
    @Step("Confirming images are unique")
    public void all_product_images_should_be_unique() {
        Assert.assertFalse(productPage.hasDuplicateImages(),
                "Defect: Found duplicate product images when all should be unique");
    }
}