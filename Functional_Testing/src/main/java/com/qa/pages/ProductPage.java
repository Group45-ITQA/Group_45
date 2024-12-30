package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.DriverManager;
import com.qa.utils.PageUtils;
import com.qa.utils.WaitUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductPage {
    private final WebDriver driver;
    private final PageUtils pageUtils;
    private final HeaderComponent headerComponent;

    // Product List Elements
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    // Cart Elements
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpack;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement bikeLight;

    @FindBy(css = "[id^='remove']")
    private List<WebElement> removeButtons;

    // Product Details Elements
    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productLinks;

    @FindBy(css = ".inventory_details_name")
    private WebElement productDetailName;

    @FindBy(css = ".inventory_item_img img")
    private List<WebElement> productImages;

    // Filter Elements
    @FindBy(className = "product_sort_container")
    private WebElement filterDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    private List<WebElement> workingProducts;

    public ProductPage() {
        this.driver = DriverManager.getDriver();
        this.pageUtils = new PageUtils(driver);
        this.headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
        initializeWorkingProducts();
    }

    private void initializeWorkingProducts() {
        workingProducts = new ArrayList<>();
        workingProducts.add(backpack);
        workingProducts.add(bikeLight);
    }

    // Product Page Verification
    public boolean verifyProductPageIsDisplayed() {
        try {
            return !inventoryItems.isEmpty() &&
                    pageUtils.isDisplayed(inventoryItems.get(0));
        } catch (Exception e) {
            return false;
        }
    }

    // Cart Operations
    public void addProductToCart() {
        pageUtils.click(addToCartButton);
    }

    public void addMultipleProductsToCart(int numberOfProducts) {
        int productsToAdd = Math.min(numberOfProducts, workingProducts.size());

        for (int i = 0; i < productsToAdd; i++) {
            WebElement product = workingProducts.get(i);
            if (product != null) {
                pageUtils.click(product);
            }
        }
    }

    public boolean isProductAddedToCart() {
        try {
            return pageUtils.isDisplayed(removeButton);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areProductsAddedToCart(int expectedCount) {
        WaitUtils.staticWait(1); // Maintaining original wait behavior
        return removeButtons.size() == expectedCount;
    }

    public String getCartCount() {
        return headerComponent.getCartCount();
    }

    // Filter Operations
    public void selectFilter(String filterOption) {
        try {
            pageUtils.selectFromDropdown(filterDropdown, filterOption);
        } catch (Exception e) {
            throw new RuntimeException("Failed to select filter option: " + filterOption, e);
        }
    }

    public boolean verifyPriceLowToHighFilter() {
        WaitUtils.staticWait(1); // Maintaining original wait behavior
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    // Product Navigation
    public void clickProductLink(String productName) {
        for (WebElement link : productLinks) {
            if (link.getText().equals(productName)) {
                pageUtils.click(link);
                return;
            }
        }
        throw new RuntimeException("Product link not found: " + productName);
    }

    // Image Verification
    public boolean hasDuplicateImages() {
        Set<String> imageSources = new HashSet<>();
        for (WebElement image : productImages) {
            pageUtils.isDisplayed(image); // Wait for image to be visible
            String src = image.getAttribute("src");
            if (!imageSources.add(src)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectProductDisplayed(String expectedProduct) {
        try {
            pageUtils.isDisplayed(productDetailName);
            String actualProduct = productDetailName.getText();
            return actualProduct.equals(expectedProduct);
        } catch (Exception e) {
            return false;
        }
    }
}