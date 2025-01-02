@product
Feature: Remove Product from Inventory Page
  As a user
  I want to remove products directly from the inventory page
  So that I can manage my cart without navigating to the cart page

  Background:
    Given I am logged in and on the products page
    And I have added "Sauce Labs Backpack" to the cart

  @remove_product @critical
  Scenario: Remove product using remove button on inventory page
    When I click the remove button for "Sauce Labs Backpack"
    Then the cart count should be "0"
    And the "Add to cart" button should be visible for "Sauce Labs Backpack"