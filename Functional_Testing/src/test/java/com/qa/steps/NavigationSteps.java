    package com.qa.steps;

    import com.qa.pages.CartPage;
    import com.qa.pages.HeaderComponent;
    import com.qa.pages.NavBarPage;
    import io.cucumber.java.en.*;
    import io.cucumber.java.After;
    import io.qameta.allure.Step;
    import org.openqa.selenium.WebDriver;
    import org.testng.Assert;
    import com.qa.utils.DriverManager;

    public class NavigationSteps {
        private final WebDriver driver;
        private final NavBarPage navBarPage;
        private final CartPage cartPage;
        private final HeaderComponent headerComponent;

        public NavigationSteps() {
            driver = DriverManager.getDriver();
            navBarPage = new NavBarPage();
            cartPage = new CartPage();
            headerComponent = new HeaderComponent(driver);
        }


        @When("I click the menu button")
        @Step("Clicking menu button")
        public void i_click_menu_button() {
            navBarPage.clickMenu();
        }

        @When("I click the All Items link")
        @Step("Clicking All Items link")
        public void i_click_all_items() {
            navBarPage.clickAllItems();
        }

        @When("I click the Logout link")
        @Step("Clicking Logout link")
        public void i_click_logout() {
            navBarPage.clickLogout();
        }

        @When("I click the Reset App State link")
        @Step("Clicking Reset App State link")
        public void i_click_reset() {
            navBarPage.clickReset();
        }

        @When("I click the cart button")
        @Step("Clicking cart button")
        public void i_click_cart() {
            headerComponent.navigateToCart();
        }



        @Then("I should be on the inventory page")
        @Step("Verifying navigation to inventory page")
        public void verify_on_inventory_page() {
            Assert.assertTrue(navBarPage.isOnInventoryPage(),
                    "Not on inventory page");
        }

        @Then("I should be on the cart page")
        @Step("Verifying navigation to cart page")
        public void verify_on_cart_page() {
            Assert.assertTrue(navBarPage.isOnCartPage(),
                    "Not on cart page");
        }

        @Then("I should be on the login page")
        @Step("Verifying navigation to login page")
        public void verify_on_login_page() {
            Assert.assertTrue(navBarPage.isOnLoginPage(),
                    "Not on login page");
        }

        @Then("the {string} should not be in the cart")
        @Step("Verifying item {0} is not in cart")
        public void verify_item_not_in_cart(String itemName) {
            headerComponent.navigateToCart();
            Assert.assertFalse(cartPage.isItemInCart(itemName),
                    "Item " + itemName + " is still in cart after reset");
        }

        @After
        public void cleanup() {
            DriverManager.quitDriver();
        }
    }
