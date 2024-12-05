package com.group45.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By backpackCartItem = By.xpath("//div[text()='Sauce Labs Backpack']");
    private By cartItem = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By removeButton = By.className("cart_button");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to verify if the backpack is present in the cart
    public boolean isBackpackInCart() {
        return driver.findElements(backpackCartItem).size() > 0;
    }

    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItem);
        return items.isEmpty();
    }

    public void removeAllItemsIfExist() {
        List<WebElement> removeButtons = driver.findElements(removeButton);
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }
}
