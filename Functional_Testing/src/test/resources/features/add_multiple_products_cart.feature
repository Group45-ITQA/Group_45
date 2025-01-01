Feature: Multiple Products Cart Functionality
  As a user
  I want to add multiple products to cart
  So that I can purchase them together

  Background:
    Given I am logged in and on the products page

  @multiple_products @cart_management @critical
  Scenario: Add multiple products to cart
    When I add 2 products to the cart
    Then I should see 2 products in the cart