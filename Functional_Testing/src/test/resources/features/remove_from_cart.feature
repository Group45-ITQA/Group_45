Feature: Remove Items from Cart
  As a user
  I want to remove items from my cart
  So that I can update my purchase selection

  Scenario: Remove an item from cart
    Given I am logged in to the Sauce Demo website
    And I have added "Sauce Labs Backpack" to the cart
    When I navigate to the cart page
    And I remove "Sauce Labs Backpack" from the cart
    Then the item "Sauce Labs Backpack" should not be visible in the cart