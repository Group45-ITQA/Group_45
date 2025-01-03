package com.qa.pages;

import com.qa.locators.SocialMediaLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.WaitUtils;

import java.util.Set;

public class SocialMediaPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;

    // Social Media Links
    @FindBy(css = SocialMediaLocators.TWITTER_LINK_SELECTOR)
    private WebElement twitterLink;

    @FindBy(css = SocialMediaLocators.FACEBOOK_LINK_SELECTOR)
    private WebElement facebookLink;

    @FindBy(css = SocialMediaLocators.LINKEDIN_LINK_SELECTOR)
    private WebElement linkedinLink;

    public SocialMediaPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSocialMediaLink(String platform) {
        WebElement socialLink = getSocialLink(platform);
        pageUtils.click(socialLink);
        pageUtils.switchToNewWindow();
    }

    private WebElement getSocialLink(String platform) {
        switch (platform.toLowerCase()) {
            case "twitter":
                return twitterLink;
            case "facebook":
                return facebookLink;
            case "linkedin":
                return linkedinLink;
            default:
                throw new IllegalArgumentException("Unsupported social media platform: " + platform);
        }
    }

    public boolean isRedirectedToSocialMedia(String expectedUrl) {
        try {
            for (int i = 0; i < 10; i++) {
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.toLowerCase().contains(expectedUrl.toLowerCase())) {
                    return true;
                }
                WaitUtils.staticWait(1);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void closeAdditionalWindows() {
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
    }
}