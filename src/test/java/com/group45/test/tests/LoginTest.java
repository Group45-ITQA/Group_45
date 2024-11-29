package com.group45.test.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.group45.test.pages.LoginPage;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.out.println("Starting the test...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        System.out.println("Navigating to website...");
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
    }

    @Test(description = "Test successful login with valid credentials")
    public void testValidLogin() throws InterruptedException {
        System.out.println("Test starting...");
        Thread.sleep(2000); // Wait 2 seconds

        System.out.println("Entering username: standard_user");
        loginPage.enterUsername("standard_user");
        Thread.sleep(2000); // Wait 2 seconds

        System.out.println("Entering password: secret_sauce");
        loginPage.enterPassword("secret_sauce");
        Thread.sleep(2000); // Wait 2 seconds

        System.out.println("Clicking login button");
        loginPage.clickLogin();
        Thread.sleep(2000); // Wait 2 seconds

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("/inventory.html"),
                "Login failed: User not redirected to inventory page");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Closing the browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}