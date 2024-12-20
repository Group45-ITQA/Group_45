package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.CheckoutPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class CheckoutSteps {
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("I have an item in my cart")
    public void i_have_an_item_in_my_cart() {
        productPage = new ProductPage();
        productPage.addProductToCart();
        Assert.assertTrue(productPage.isProductAddedToCart(), "Product was not added to cart.");
    }

    @When("I proceed to the checkout page")
    public void i_proceed_to_the_checkout_page() {
        cartPage = new CartPage();
        cartPage.goToCartPage();
        cartPage.attemptToCheckout();
    }

    @When("I enter {string} in the first name field")
    public void i_enter_in_the_first_name_field(String firstName) {
        checkoutPage = new CheckoutPage();
        checkoutPage.enterFirstName(firstName);
    }

    @When("I enter {string} in the last name field")
    public void i_enter_in_the_last_name_field(String lastName) {
        checkoutPage.enterLastName(lastName);
    }

    @Then("the first name field should contain {string}")
    public void the_first_name_field_should_contain(String expectedFirstName) {
        Assert.assertEquals(checkoutPage.getFirstNameValue(), expectedFirstName, "First name field did not contain the expected value.");
    }

    @Then("the last name field should be empty")
    public void the_last_name_field_should_be_empty() {
        Assert.assertEquals(checkoutPage.getLastNameValue(), "", "Last name field was not empty.");
    }
}
