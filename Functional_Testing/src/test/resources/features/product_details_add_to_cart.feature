@product
Feature: Product Details Add to Cart Functionality
  As a user
  I want to add products to cart from the product details page
  So that I can purchase items after viewing their details

  Background:
    Given I am logged in and on the products page

  @product-details @add_to_cart @critical
  Scenario: Add product to cart from product details page
    When I click on the "Sauce Labs Backpack" product link
    And I click the Add to Cart button
    Then I should see the cart count increase to "1"
    And the Add to Cart button should change to Remove