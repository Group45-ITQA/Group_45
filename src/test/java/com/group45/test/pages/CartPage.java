package com.group45.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By backpackCartItem = By.xpath("//div[text()='Sauce Labs Backpack']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to verify if the backpack is present in the cart
    public boolean isBackpackInCart() {
        return driver.findElements(backpackCartItem).size() > 0;
    }
}
