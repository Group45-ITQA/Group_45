@type:cart_management @cart
Feature: Remove Items from Cart
  As a user
  I want to remove items from my cart
  So that I can update my purchase selection

  Background:
    Given I am logged in and on the products page
    And I have added "Sauce Labs Backpack" to the cart

  @remove_single_item_from_cart @critical @defect
  Scenario: Remove an item from cart
    When I navigate to the cart page
    And I remove "Sauce Labs Backpack" from the cart
    Then the item "Sauce Labs Backpack" should not be visible in the cart