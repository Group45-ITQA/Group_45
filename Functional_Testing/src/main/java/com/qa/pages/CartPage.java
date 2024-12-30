package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.WaitUtils;

import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;
    private final HeaderComponent headerComponent;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(id = "item_4_title_link")
    private WebElement backpackItem;

    @FindBy(id = "item_0_title_link")
    private WebElement bikeLightItem;

    public CartPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToCartPage() {
        headerComponent.clickCart();
    }

    public void addItemToCart(String itemName) {
        String buttonId = "add-to-cart-" + itemName.replace(" ", "-").toLowerCase();
        WebElement addItemButton = driver.findElement(By.id(buttonId));
        pageUtils.click(addItemButton);
    }

    public void removeItemFromCart(String itemName) {
        String buttonId = "remove-" + itemName.replace(" ", "-").toLowerCase();
        WebElement removeItemButton = driver.findElement(By.id(buttonId));
        pageUtils.click(removeItemButton);
    }

    public boolean isItemInCart(String itemName) {
        try {
            String itemId = "item_" + itemName.replace(" ", "-").toLowerCase() + "_title_link";
            WebElement item = driver.findElement(By.id(itemId));
            return WaitUtils.waitForElementVisible(driver, item, 5);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckoutButtonEnabled() {
        return WaitUtils.waitForElementVisible(driver, checkoutButton) &&
                checkoutButton.isEnabled();
    }

    public void attemptToCheckout() {
        pageUtils.click(checkoutButton);
    }

    public boolean isCartEmpty() {
        try {
            List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
            WaitUtils.staticWait(1);
            return cartItems.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartEmptyErrorMessageDisplayed() {
        return WaitUtils.waitForElementVisible(driver, errorMessage, 5);
    }
}