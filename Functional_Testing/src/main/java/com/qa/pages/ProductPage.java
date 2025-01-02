package com.qa.pages;

import com.qa.locators.CartPageLocators;
import com.qa.locators.ProductPageLocators;
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
    @FindBy(className = ProductPageLocators.INVENTORY_ITEM_CLASS)
    private List<WebElement> inventoryItems;

    // Cart Elements

    @FindBy(id = ProductPageLocators.BACKPACK_ID)
    private WebElement backpack;

    @FindBy(id = ProductPageLocators.BIKE_LIGHT_ID)
    private WebElement bikeLight;

    @FindBy(css = ProductPageLocators.REMOVE_BUTTONS)
    private List<WebElement> removeButtons;

    @FindBy(css = ProductPageLocators.ADD_TO_CART_BUTTONS)
    private List<WebElement> addToCartButtons;

    // Product Details Elements
    @FindBy(css = ProductPageLocators.PRODUCT_NAME_CLASS)
    private List<WebElement> productLinks;

    @FindBy(css = ProductPageLocators.PRODUCT_DETAIL_CLASS)
    private WebElement productDetailName;

    @FindBy(css = ProductPageLocators.PRODUCT_IMAGE_SELECTOR)
    private List<WebElement> productImages;

    // Filter Elements
    @FindBy(className = ProductPageLocators.PRODUCT_SORT_CLASS)
    private WebElement filterDropdown;

    @FindBy(className = ProductPageLocators.PRODUCT_PRICE_CLASS)
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
    public void addItemToCart(String itemName) {
        String formattedName = formatItemName(itemName);
        String buttonId = String.format(ProductPageLocators.BUTTON_ID_FORMAT, "add-to-cart", formattedName);

        try {
            WebElement addButton = driver.findElement(By.id(buttonId));
            pageUtils.click(addButton);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not find add button for item: " + itemName, e);
        }
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

    public int addAllProductsToCart() {
        int addedProducts = 0;
        for (WebElement button : addToCartButtons) {
            try {
                pageUtils.click(button);
                addedProducts++;
            } catch (Exception e) {
                System.out.println("Failed to add product: " + e.getMessage());
            }
        }
        return addedProducts;
    }


    public boolean areProductsAddedToCart(int expectedCount) {
        WaitUtils.staticWait(1);
        return removeButtons.size() == expectedCount;
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
        WaitUtils.staticWait(1);
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

    public void clickButtonById(String buttonId) {
        try {
            WebElement button = driver.findElement(By.id(buttonId));
            pageUtils.click(button);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click button with id: " + buttonId, e);
        }
    }

    public boolean isButtonVisible(String buttonId) {
        try {
            WebElement button = driver.findElement(By.id(buttonId));
            return WaitUtils.waitForElementVisible(driver, button, 5);
        } catch (Exception e) {
            return false;
        }
    }

    private String formatItemName(String itemName) {
        return itemName.replace(" ", "-").toLowerCase();
    }
}