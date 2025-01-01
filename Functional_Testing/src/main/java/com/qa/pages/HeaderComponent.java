package com.qa.pages;

import com.qa.locators.HeaderComponentLocators;
import com.qa.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private final PageUtils pageUtils;

    @FindBy(className = HeaderComponentLocators.CART_LINK_CLASS)
    private WebElement cartLink;

    @FindBy(id = HeaderComponentLocators.MENU_BUTTON_ID)
    private WebElement menuButton;

    @FindBy(className = HeaderComponentLocators.CART_BADGE_CLASS)
    private WebElement cartBadge;

    public HeaderComponent(WebDriver driver) {
        this.pageUtils = new PageUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToCart() {
        pageUtils.click(cartLink);
    }

    public void clickMenu() {
        pageUtils.click(menuButton);
    }

    public String getCartCount() {
        try {
            return pageUtils.getText(cartBadge);
        } catch (Exception e) {
            return "0";
        }
    }
}

