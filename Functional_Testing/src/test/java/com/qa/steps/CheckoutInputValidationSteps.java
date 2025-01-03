package com.qa.steps;

import com.qa.pages.CartPage;
import com.qa.pages.CheckoutPage;
import com.qa.pages.ProductPage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import io.qameta.allure.*;
import com.qa.utils.DriverManager;

@Epic("Checkout Features")
@Feature("Checkout Form Validation")
@Story("Form Field Value Retention")
public class CheckoutInputValidationSteps {
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    public CheckoutInputValidationSteps() {
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @When("I enter {string} in the first name field")
    public void i_enter_in_the_first_name_field(String firstName) {

        checkoutPage.enterFirstName(firstName);
    }

    @When("I enter {string} in the last name field")
    public void i_enter_in_the_last_name_field(String lastName) {

        checkoutPage.enterLastName(lastName);
    }

    @Then("the first name field should contain {string}")
    public void the_first_name_field_should_contain(String expectedFirstName) {
        String actualFirstName = checkoutPage.getFirstNameValue();
        Assert.assertEquals(actualFirstName, expectedFirstName,
                "First name field contains incorrect value. Expected: " +
                        expectedFirstName + ", but got: " + actualFirstName);
    }

    @Then("the last name field should contain {string}")
    public void the_last_name_field_should_contain(String expectedLastName) {
        String actualLastName = checkoutPage.getLastNameValue();
        Assert.assertEquals(actualLastName, expectedLastName,
                "Last name field contains incorrect value. Expected: " +
                        expectedLastName + ", but got: " + actualLastName);
    }
}