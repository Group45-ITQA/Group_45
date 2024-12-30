package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;

public class CheckoutPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
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