package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;

public class CartPage {
    private WebDriver driver;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

//    @FindBy(className = "shopping_cart_badge")
//    private WebElement cartBadge;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "item_4_title_link")
    private WebElement backpackItem;

    @FindBy(id = "item_0_title_link")
    private WebElement bikeLightItem;

    public CartPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToCartPage() {
        cartLink.click();
        sleep(1);
    }

    public void addItemToCart(String itemName) {
        WebElement addItemButton = driver.findElement(By.id("add-to-cart-" + itemName.replace(" ", "-").toLowerCase()));
        addItemButton.click();
        sleep(1);
    }

    public void removeItemFromCart(String itemName) {
        WebElement removeItemButton = driver.findElement(By.id("remove-" + itemName.replace(" ", "-").toLowerCase()));
        removeItemButton.click();
        sleep(1);
    }

    public boolean isItemInCart(String itemName) {
        try {
            WebElement item = driver.findElement(By.id("item_" + itemName.replace(" ", "-").toLowerCase() + "_title_link"));
            return item.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckoutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public void attemptToCheckout() {
        checkoutButton.click();
        sleep(1);
    }

    public boolean isCartEmptyErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
