@product @cart
Feature: Add All Products to Cart
  As a user
  I want to add all available products to my cart
  So that I can purchase everything at once

  Background:
    Given I am logged in and on the products page

  @add_all_products @critical
  Scenario: Add all products to cart
    When I add all available products to the cart
    Then all products should be in the cart