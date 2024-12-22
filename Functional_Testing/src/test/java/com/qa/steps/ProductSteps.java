package com.qa.steps;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.utils.DriverManager;
import com.qa.utils.AllureReportUtil;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.*;
import org.testng.Assert;
import com.qa.utils.LoginUtils;

@Epic("Shopping Cart Features")
@Feature("Product Cart Management")
public class ProductSteps {


    private ProductPage productPage;

    @Before
    public void setup() {
        // Initialize the driver
        DriverManager.getDriver();

        // Initialize page objects
        productPage = new ProductPage();

        // Perform login and navigate to the product page
        LoginUtils.loginAndNavigateToProductPage();
    }

    @Given("I am logged in and on the products page")
    public void i_am_logged_in_and_on_products_page() {
        LoginUtils.verifyProductPage(); // This ensures the user is on the product page
    }

    @When("I add a product to the cart")
    public void i_add_product_to_cart() {
        productPage.addProductToCart();
    }

    @Then("the cart count should be {string}")
    public void verify_cart_count(String expectedCount) {
        Assert.assertEquals(productPage.getCartCount(), expectedCount);
    }

    @After
    public void cleanup(Scenario scenario) {
        if (scenario.isFailed()) {
            AllureReportUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }
}