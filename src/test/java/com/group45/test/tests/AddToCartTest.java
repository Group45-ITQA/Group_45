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

public class AddToCartTest {
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

    @Test(description = "Test adding a single item to the cart")
    public void testAddCartItem() throws InterruptedException {
        System.out.println("Adding Sauce Labs Backpack to the cart...");
        inventoryPage.addBackpackToCart();
        Thread.sleep(2000); // Wait for UI update (replace with explicit waits in production code)

        System.out.println("Navigating to the cart...");
        inventoryPage.navigateToCart();
        Thread.sleep(2000); // Wait for page load (replace with explicit waits in production code)

        System.out.println("Verifying item in the cart...");
        boolean isBackpackInCart = cartPage.isBackpackInCart();

        Assert.assertTrue(isBackpackInCart, "Sauce Labs Backpack is not in the cart");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
