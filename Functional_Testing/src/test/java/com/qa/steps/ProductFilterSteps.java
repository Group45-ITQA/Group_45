package com.qa.steps;

import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Product Features")
@Feature("Product Filtering")
public class ProductFilterSteps {
    private ProductPage productPage;

    public ProductFilterSteps() {
        productPage = new ProductPage();
    }

    @When("I select the {string} filter option")
    public void selectFilterOption(String filterOption) {
        productPage.selectFilter(filterOption);
    }

    @Then("the products should be sorted by price low to high")
    public void verifyPriceSorting() {
        Assert.assertTrue(productPage.verifyPriceLowToHighFilter(),
                "Defect: Products are not properly sorted by price low to high");
    }
}