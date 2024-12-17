package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private WebDriver driver;

    // Elements for adding product to cart
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    // Element that appears after adding to cart - indicates product is in cart
    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement bikeLight;

    @FindBy(css = "[id^='remove']")
    private List<WebElement> removeButtons;

    private List<WebElement> workingProducts;

    // Shopping cart badge that shows number of items
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;



    public ProductPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        initializeWorkingProducts();
    }

    private void initializeWorkingProducts() {
        workingProducts = new ArrayList<>();
        workingProducts.add(backpack);
        workingProducts.add(bikeLight);
    }

    // Adds the product to cart
    public void addProductToCart() {
        addToCartButton.click();
        sleep(1); // Wait for cart update
    }

    public void addMultipleProductsToCart(int numberOfProducts) {
        int productsToAdd = Math.min(numberOfProducts, workingProducts.size());

        for (int i = 0; i < productsToAdd; i++) {
            WebElement product = workingProducts.get(i);
            if (product != null && product.isEnabled()) {
                product.click();
                waitForCartUpdate();
            }
        }
    }

    // Verifies if the product was successfully added to cart
    public boolean isProductAddedToCart() {
        try {
            return removeButton.isDisplayed(); // If "Remove" button is visible, product was added
        } catch (Exception e) {
            return false; // If "Remove" button isn't found, product wasn't added
        }
    }


    public boolean areProductsAddedToCart(int expectedCount) {
        return removeButtons.size() == expectedCount;
    }

    // Gets the cart count (though this might not be the best verification)
    public String getCartCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0"; // Return "0" if no badge is found
        }
    }

    private void waitForCartUpdate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Utility method for adding small delays
    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}