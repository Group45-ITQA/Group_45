package com.qa.pages;

import com.qa.locators.CheckoutPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;

public class CheckoutPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;

    @FindBy(id = CheckoutPageLocators.FIRST_NAME_ID)
    private WebElement firstNameField;

    @FindBy(id = CheckoutPageLocators.LAST_NAME_ID)
    private WebElement lastNameField;

    @FindBy(id = CheckoutPageLocators.POSTAL_CODE_ID)
    private WebElement postalCodeField;

    @FindBy(id = CheckoutPageLocators.CONTINUE_BUTTON_ID)
    private WebElement continueButton;

    public CheckoutPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        pageUtils.type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        pageUtils.type(lastNameField, lastName);
    }

    public String getFirstNameValue() {
        pageUtils.isDisplayed(firstNameField);
        return firstNameField.getAttribute("value");
    }

    public String getLastNameValue() {
        pageUtils.isDisplayed(lastNameField);
        return lastNameField.getAttribute("value");
    }
}