package com.qa.pages;

import com.qa.locators.ProductDetailsPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.WaitUtils;

public class ProductDetailsPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;
    private final HeaderComponent headerComponent;

    @FindBy(css = ProductDetailsPageLocators.DETAILS_CONTAINER_CLASS)
    private WebElement productDetailsContainer;

    @FindBy(css = ProductDetailsPageLocators.PRODUCT_NAME_CLASS)
    private WebElement productName;

    @FindBy(css = ProductDetailsPageLocators.PRODUCT_DESC_CLASS)
    private WebElement productDescription;

    @FindBy(css = ProductDetailsPageLocators.PRODUCT_PRICE_CLASS)
    private WebElement productPrice;

    @FindBy(css = ProductDetailsPageLocators.ADD_TO_CART_SELECTOR)
    private WebElement addToCartButton;

    @FindBy(css = ProductDetailsPageLocators.REMOVE_SELECTOR)
    private WebElement removeButton;

    public ProductDetailsPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDetailsPageDisplayed() {
        try {
            return WaitUtils.waitForElementVisible(driver, productDetailsContainer) &&
                    WaitUtils.waitForElementVisible(driver, productName);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddToCartButton() {
        try {
            WaitUtils.waitForElementClickable(driver, addToCartButton);
            pageUtils.click(addToCartButton);
        } catch (Exception e) {
            throw new RuntimeException("Add to Cart button is not clickable", e);
        }
    }

    public boolean isProductAddedToCart() {
        try {
            return WaitUtils.waitForElementVisible(driver, removeButton, 5);
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartCount() {
        return headerComponent.getCartCount();
    }

    public boolean isRemoveButtonDisplayed() {
        try {
            return WaitUtils.waitForElementVisible(driver, removeButton) &&
                    removeButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRemoveButton() {
        try {
            WaitUtils.waitForElementClickable(driver, removeButton);
            pageUtils.click(removeButton);
        } catch (Exception e) {
            throw new RuntimeException("Remove button is not clickable", e);
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        try {
            return WaitUtils.waitForElementVisible(driver, addToCartButton) &&
                    addToCartButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }


    public String getProductName() {
        return pageUtils.getText(productName);
    }

    public String getProductDescription() {
        return pageUtils.getText(productDescription);
    }

    public String getProductPrice() {
        return pageUtils.getText(productPrice);
    }
}