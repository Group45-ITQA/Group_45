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

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;



    public CartPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToCartPage() {
        cartLink.click();
        sleep(1);
    }

    public void addItemToCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName);
        driver.findElement(By.xpath(xpath)).click();
        sleep(1);
    }

    public void removeItemFromCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button[contains(@id,'remove')]", itemName);
        driver.findElement(By.xpath(xpath)).click();
        sleep(1);
    }

    public boolean isItemInCart(String itemName) {
        try {
            String xpath = String.format("//div[text()='%s']", itemName);
            return driver.findElement(By.xpath(xpath)).isDisplayed();
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
