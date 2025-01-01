package com.qa.pages;

import com.qa.locators.NavBarLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.ConfigurationManager;

public class NavBarPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;
    private final HeaderComponent headerComponent;
    private final CartPage cartPage;

    // Navigation Menu Elements
    @FindBy(id = NavBarLocators.ALL_ITEMS_ID)
    private WebElement allItemsButton;

    @FindBy(id = NavBarLocators.ABOUT_ID)
    private WebElement aboutButton;

    @FindBy(id = NavBarLocators.LOGOUT_ID)
    private WebElement logoutButton;

    @FindBy(id = NavBarLocators.RESET_ID)
    private WebElement resetButton;

    public NavBarPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        this.headerComponent = new HeaderComponent(driver);
        this.cartPage = new CartPage();
        PageFactory.initElements(driver, this);
    }

    // Menu Operations
    public void clickMenu() {
        headerComponent.clickMenu();
        // Wait for menu animation to complete
        pageUtils.isDisplayed(allItemsButton);
    }

    public void clickAllItems() {
        pageUtils.click(allItemsButton);
    }

    public void clickAbout() {
        pageUtils.click(aboutButton);
    }

    public void clickLogout() {
        pageUtils.click(logoutButton);
    }

    public void clickReset() {
        pageUtils.click(resetButton);
    }

    // Page Verification Methods
    public boolean isOnInventoryPage() {
        String expectedUrl = ConfigurationManager.getProperty("base.url") + "inventory.html";
        return driver.getCurrentUrl().contains(expectedUrl);
    }

    public boolean isOnCartPage() {
        String expectedUrl = ConfigurationManager.getProperty("base.url") + "cart.html";
        return driver.getCurrentUrl().contains(expectedUrl);
    }

    public boolean isOnLoginPage() {
        String baseUrl = ConfigurationManager.getProperty("base.url");
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(baseUrl) ||
                currentUrl.equals(baseUrl.substring(0, baseUrl.length() - 1));
    }
}