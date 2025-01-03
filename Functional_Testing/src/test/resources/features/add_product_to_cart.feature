@type:cart_management @product @cart
Feature: Product Cart Functionality
  As a user
  I want to add products to the cart
  So that I can purchase them

  Background:
    Given I am logged in and on the products page

  @critical @defect @add_single_product_to_cart
  Scenario: Add product to cart
    When I add a product to the cart
    Then the cart count should be "1"


