package com.group45.test.tests;

import com.group45.test.pages.CartPage;
import com.group45.test.pages.InventoryPage;
import com.group45.test.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmptyCartCheckoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        System.out.println("Starting the test...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        System.out.println("Navigating to website...");
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        // Log in
        System.out.println("Logging in with valid credentials...");
        loginPage.loginToApplication("standard_user", "secret_sauce");
    }

    @Test(description = "Test navigation to checkout with an empty cart")
    public void testEmptyCartCheckout() throws InterruptedException {
        System.out.println("Ensuring cart is empty...");
        inventoryPage.navigateToCart();
        cartPage.removeAllItemsIfExist(); // Ensure cart is empty
        Thread.sleep(1000); // Wait for UI update

        System.out.println("Attempting to navigate to checkout...");
        cartPage.clickCheckoutButton();

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Validate that the user is not navigated to the checkout page
        Assert.assertFalse(currentUrl.contains("/checkout-step-one.html"),
                "Bug: User can navigate to checkout with an empty cart");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
