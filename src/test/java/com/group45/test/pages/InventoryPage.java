package com.group45.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    // Locators
    private By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.id("shopping_cart_container");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to add the backpack to the cart
    public void addBackpackToCart() {
        driver.findElement(backpackAddButton).click();
    }

    // Method to navigate to the cart
    public void navigateToCart() {
        driver.findElement(cartIcon).click();
    }
}
