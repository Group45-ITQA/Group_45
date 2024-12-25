package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;

public class ProductDetailsPage {
    private WebDriver driver;

    @FindBy(css = ".inventory_details_container")
    private WebElement productDetailsContainer;

    @FindBy(css = ".inventory_details_name")
    private WebElement productName;

    @FindBy(css = "button[id^='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(css = "button[id^='remove']")
    private WebElement removeButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public ProductDetailsPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDetailsPageDisplayed() {
        try {
            return productDetailsContainer.isDisplayed() &&
                    productName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddToCartButton() {
        if (addToCartButton.isDisplayed() && addToCartButton.isEnabled()) {
            addToCartButton.click();
            sleep(1); // Wait for cart update
        } else {
            throw new RuntimeException("Add to Cart button is not clickable");
        }
    }

    public boolean isProductAddedToCart() {
        try {
            return removeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isRemoveButtonDisplayed() {
        try {
            return removeButton.isDisplayed() &&
                    removeButton.isEnabled();
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