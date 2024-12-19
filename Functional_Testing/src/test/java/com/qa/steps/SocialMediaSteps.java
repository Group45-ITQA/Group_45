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
        Assert.assertTrue(socialMediaPage.isRedirectedToSocialMedia(expectedUrl),
                "Not redirected to correct " + platform + " page");
    }

    private String getExpectedSocialUrl(String platform) {
        switch (platform.toLowerCase()) {
            case "twitter":
                return "https://x.com/saucelabs";
            case "facebook":
                return "https://www.facebook.com/saucelabs";
            case "linkedin":
                return "https://www.linkedin.com/company/sauce-labs/";
            default:
                throw new IllegalArgumentException("Unsupported social media platform: " + platform);
        }
    }
}