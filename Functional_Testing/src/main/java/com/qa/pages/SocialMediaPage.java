package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import java.util.Set;

public class SocialMediaPage {
    private WebDriver driver;

    @FindBy(css = "li.social_twitter a")
    private WebElement twitterLink;

    @FindBy(css = "li.social_facebook a")
    private WebElement facebookLink;

    @FindBy(css = "li.social_linkedin a")
    private WebElement linkedinLink;

    public SocialMediaPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickSocialMediaLink(String platform) {
        switch (platform.toLowerCase()) {
            case "twitter":
                twitterLink.click();
                break;
            case "facebook":
                facebookLink.click();
                break;
            case "linkedin":
                linkedinLink.click();
                break;
            default:
                throw new IllegalArgumentException("Unsupported social media platform: " + platform);
        }
        handleWindowSwitch();
    }

    public boolean isRedirectedToSocialMedia(String expectedUrl) {
        // Wait for a short time to allow the redirect to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        return currentUrl.toLowerCase().contains(expectedUrl.toLowerCase());
    }

    private void handleWindowSwitch() {
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to new window/tab
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
