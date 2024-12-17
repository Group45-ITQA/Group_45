Feature: Product Cart Functionality
  As a user
  I want to add products to cart
  So that I can purchase them

  Scenario: Add product to cart
    Given I am on the products page
    When I add a product to the cart
    Then the cart count should be "1"

