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

    private static final String CART_ITEM_CLASS = "cart_item";
    private static final String BUTTON_ID_FORMAT = "%s-%s";
    private static final String ITEM_ID_FORMAT = "item_%s_title_link";

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(className = CART_ITEM_CLASS)
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


    public void addItemToCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String buttonId = String.format(BUTTON_ID_FORMAT, "add-to-cart", formattedName);

        try {
            WebElement addButton = driver.findElement(By.id(buttonId));
            pageUtils.click(addButton);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find add button for item: " + itemName, e);
        }
    }


    public void removeItemFromCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String buttonId = String.format(BUTTON_ID_FORMAT, "remove", formattedName);

        try {
            WebElement removeButton = driver.findElement(By.id(buttonId));
            pageUtils.click(removeButton);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find remove button for item: " + itemName, e);
        }
    }

    public boolean isItemInCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String itemId = String.format(ITEM_ID_FORMAT, formattedName);

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
        return getItemCount() == "0";
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