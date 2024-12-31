package com.qa.pages;

import com.qa.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private final WebDriver driver;
    private final PageUtils pageUtils;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
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

