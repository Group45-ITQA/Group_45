package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;

public class NavigationPage {
    private WebDriver driver;
    private CartPage cartPage;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsButton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    public NavigationPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickMenu() {
        menuButton.click();
        waitForAnimation();
    }

    public void clickAllItems() {
        allItemsButton.click();
        waitForAnimation();
    }

    public void clickAbout() {
        aboutButton.click();
        waitForAnimation();
    }

    public void clickLogout() {
        logoutButton.click();
        waitForAnimation();
    }

    public void clickReset() {
        resetButton.click();
        waitForAnimation();
    }

    public void clickCart() {
        cartButton.click();
        waitForAnimation();
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public boolean isOnLoginPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals("https://www.saucedemo.com") ||
                currentUrl.equals("https://www.saucedemo.com/");
    }

    public boolean isCartEmpty() {
        return !cartPage.isCheckoutButtonEnabled() ||
                cartPage.isCartEmptyErrorMessageDisplayed();
    }

    public boolean verifyItemRemoved(String itemName) {
        return !cartPage.isItemInCart(itemName);
    }

    private void waitForAnimation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
