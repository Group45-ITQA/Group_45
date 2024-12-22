@product
Feature: Product Cart Functionality
  As a user
  I want to add products to the cart
  So that I can purchase them

  Background:
    Given I am logged in and on the products page

  @add_to_cart @critical
  Scenario: Add product to cart
    When I add a product to the cart
    Then the cart count should be "1"
