package com.qa.pages;

import com.qa.locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.ConfigurationManager;

public class LoginPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;

    @FindBy(id = LoginPageLocators.USERNAME_ID)
    private WebElement usernameField;

    @FindBy(id = LoginPageLocators.PASSWORD_ID)
    private WebElement passwordField;

    @FindBy(id = LoginPageLocators.LOGIN_BUTTON_ID)
    private WebElement loginButton;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void login() {
        login(
                ConfigurationManager.getProperty("username.problem"),
                ConfigurationManager.getProperty("password.default")
        );
    }

    public void login(String username, String password) {
        driver.get(ConfigurationManager.getProperty("base.url"));

        pageUtils.type(usernameField, username);
        pageUtils.type(passwordField, password);
        pageUtils.click(loginButton);
    }
}