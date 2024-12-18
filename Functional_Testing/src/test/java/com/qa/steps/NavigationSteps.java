    package com.qa.steps;

    import com.qa.pages.CartPage;
    import com.qa.pages.LoginPage;
    import com.qa.pages.NavigationPage;
    import io.cucumber.java.en.*;
    import io.cucumber.java.After;
    import io.cucumber.java.Before;
    import org.testng.Assert;
    import com.qa.utils.DriverManager;

    public class NavigationSteps {
        private NavigationPage navigationPage;
        private LoginPage loginPage;
        private CartPage cartPage;

        @Before
        public void setup() {
            navigationPage = new NavigationPage();
            loginPage = new LoginPage();
            cartPage = new CartPage();
        }

        @Given("I am logged in as problem user")
        public void i_am_logged_in_as_problem_user() {
            loginPage.login();
        }

        @Given("I have added {string} to cart")
        public void i_have_added_item_to_cart(String itemName) {
            cartPage.addItemToCart(itemName);
        }

        @When("I click the menu button")
        public void i_click_menu_button() {
            navigationPage.clickMenu();
        }

        @When("I click the All Items link")
        public void i_click_all_items() {
            navigationPage.clickAllItems();
        }

        @When("I click the About link")
        public void i_click_about() {
            navigationPage.clickAbout();
        }

        @When("I click the Logout link")
        public void i_click_logout() {
            navigationPage.clickLogout();
        }

        @When("I click the Reset App State link")
        public void i_click_reset() {
            navigationPage.clickReset();
        }

        @When("I click the cart button")
        public void i_click_cart() {
            navigationPage.clickCart();
        }



        @Then("I should be on the inventory page")
        public void verify_on_inventory_page() {
            Assert.assertTrue(navigationPage.isOnInventoryPage(),
                    "Not on inventory page");
        }

        @Then("I should be on the cart page")
        public void verify_on_cart_page() {
            Assert.assertTrue(navigationPage.isOnCartPage(),
                    "Not on cart page");
        }

        @Then("I should be on the login page")
        public void verify_on_login_page() {
            Assert.assertTrue(navigationPage.isOnLoginPage(),
                    "Not on login page");
        }

        @Then("the {string} should not be in the cart")
        public void verify_item_not_in_cart(String itemName) {
            cartPage.goToCartPage();
            Assert.assertFalse(cartPage.isItemInCart(itemName),
                    "Item " + itemName + " is still in cart after reset");
        }

        @After
        public void cleanup() {
            DriverManager.quitDriver();
        }
    }
