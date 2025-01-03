package com.qa.pages;

import com.qa.locators.CartPageLocators;
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

    @FindBy(id = CartPageLocators.CHECKOUT_BUTTON_ID)
    private WebElement checkoutButton;

    @FindBy(className = CartPageLocators.ERROR_MESSAGE_CLASS)
    private WebElement errorMessage;

    @FindBy(className = CartPageLocators.CART_ITEM_CLASS)
    private List<WebElement> cartItems;

    public CartPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }


    private String formatItemName(String itemName) {
        return itemName.replace(" ", "-").toLowerCase();
    }

    public void removeItemFromCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String buttonId = String.format(CartPageLocators.BUTTON_ID_FORMAT, "remove", formattedName);

        try {
            WebElement removeButton = driver.findElement(By.id(buttonId));
            pageUtils.click(removeButton);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find remove button for item: " + itemName, e);
        }
    }

    public boolean isItemInCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String itemId = String.format(CartPageLocators.ITEM_TITLE_FORMAT, formattedName);

        try {
            return WaitUtils.waitForElementVisible(driver, driver.findElement(By.id(itemId)), 5);
        } catch (Exception e) {
            return false;
        }
    }


    public String getItemCount() {
        return headerComponent.getCartCount();
    }

    public boolean isCartEmpty() {
        return getItemCount().equals("0");
    }


    public boolean attemptToCheckout() {
        if (!isCheckoutButtonEnabled()) {
            return false;
        }

        try {
            pageUtils.click(checkoutButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckoutButtonEnabled() {
        return WaitUtils.waitForElementVisible(driver, checkoutButton) &&
                checkoutButton.isEnabled();
    }


    public boolean hasEmptyCartError() {
        return WaitUtils.waitForElementVisible(driver, errorMessage, 5);
    }
}