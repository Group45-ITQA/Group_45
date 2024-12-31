package com.qa.steps;

import com.qa.pages.HeaderComponent;
import com.qa.pages.LoginPage;
import com.qa.pages.CartPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CommonCartSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private LoginPage loginPage = new LoginPage();
    private HeaderComponent headerComponent = new HeaderComponent(driver);;

    @Given("I am logged in to the Sauce Demo website")
    public void i_am_logged_in_to_sauce_demo() {
        loginPage.login();
    }

    @When("I navigate to the cart page")
    public void i_navigate_to_cart_page() {
        headerComponent.navigateToCart();
    }
}