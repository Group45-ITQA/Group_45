package com.qa.steps;

import com.qa.pages.ProductPage;
import com.qa.pages.CartPage;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.testng.Assert;

@Epic("Product Features")
@Feature("Product Management")
@Story("Remove Products from Inventory")
public class ProductRemoveSteps {
    private final ProductPage productPage;
    private final CartPage cartPage;

    public ProductRemoveSteps() {
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @When("I click the remove button for {string}")
    @Step("Clicking remove button for product: {0}")
    public void clickRemoveButton(String productName) {
        String formattedName = productName.replace(" ", "-").toLowerCase();
        String removeButtonId = "remove-" + formattedName;
        productPage.clickButtonById(removeButtonId);
    }

    @Then("the {string} button should be visible for {string}")
    @Step("Verifying {0} button is visible for product: {1}")
    public void verifyButtonVisible(String buttonType, String productName) {
        String formattedName = productName.replace(" ", "-").toLowerCase();
        String buttonId = buttonType.toLowerCase().replace(" ", "-") + "-" + formattedName;
        Assert.assertTrue(productPage.isButtonVisible(buttonId),
                buttonType + " button should be visible for " + productName);
    }
}