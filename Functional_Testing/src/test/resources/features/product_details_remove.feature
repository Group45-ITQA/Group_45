@product @cart
Feature: Remove Product from Cart on Product Details Page
  As a user
  I want to remove products from my cart while viewing product details
  So that I can update my purchase selection directly from the product page

  Background:
    Given I am logged in and on the products page
    And I have added "Sauce Labs Backpack" to the cart
    And I click on the "Sauce Labs Backpack" product link

  @remove_from_cart @product_details @critical
  Scenario: Remove product from cart on product details page
    When I click the remove button on the product details page
    Then the cart count should be "0"
    And the add to cart button should be visible