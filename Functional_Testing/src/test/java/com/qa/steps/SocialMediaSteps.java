package com.qa.steps;

import com.qa.pages.SocialMediaPage;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import org.testng.Assert;

public class SocialMediaSteps {
    private SocialMediaPage socialMediaPage;

    @Before
    public void setup() {
        socialMediaPage = new SocialMediaPage();
    }

    @When("I click the {string} social media link")
    public void i_click_social_media_link(String platform) {
        socialMediaPage.clickSocialMediaLink(platform);
    }

    @Then("I should be redirected to the {string} page")
    public void verify_social_media_redirect(String platform) {
        // Switch to new window/tab
        String expectedUrl = getExpectedSocialUrl(platform);
        String actualUrl = socialMediaPage.getCurrentUrl();
        boolean isMatch = isUrlMatch(actualUrl, platform);
        Assert.assertTrue(isMatch,
                String.format("URL mismatch for %s.\nExpected: %s\nActual: %s",
                        platform, expectedUrl, actualUrl));
    }

    private boolean isUrlMatch(String actualUrl, String platform) {
        String lowerActual = actualUrl.toLowerCase();
        switch (platform.toLowerCase()) {
            case "facebook":
                return lowerActual.contains("facebook.com/saucelabs");
            case "twitter":
                return lowerActual.contains("x.com/saucelabs");
            case "linkedin":
                return lowerActual.contains("linkedin.com/company/sauce-labs");
            default:
                return false;
        }
    }

    private String getExpectedSocialUrl(String platform) {
        switch (platform.toLowerCase()) {
            case "twitter":
                return "https://x.com/saucelabs";
            case "facebook":
                return "https://facebook.com/saucelabs";
            case "linkedin":
                return "https://www.linkedin.com/company/sauce-labs/";
            default:
                throw new IllegalArgumentException("Unsupported social media platform: " + platform);
        }
    }
}