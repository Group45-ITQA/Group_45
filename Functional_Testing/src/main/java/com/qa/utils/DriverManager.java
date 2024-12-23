package com.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Configure Chrome options for both local and CI environments
            ChromeOptions options = new ChromeOptions();

            // Check if running in CI environment (GitHub Actions)
            if (System.getenv("CI") != null) {
                // Options specific for CI environment
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            // Common options for both environments
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--ignore-certificate-errors");

            // Create WebDriver instance with options
            WebDriver webDriver = new ChromeDriver(options);

            // Set timeouts and window size (if not headless)
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

            // Only maximize if not in headless mode
            if (System.getenv("CI") == null) {
                webDriver.manage().window().maximize();
            }

            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}