Feature: Cart Functionality
  As a user
  I want to ensure the cart is properly validated
  So that I cannot proceed to checkout with an empty cart

  Scenario: Prevent checkout with an empty cart
    Given I am logged in to the Sauce Demo website
    When I navigate to the cart page
    Then the checkout button should be disabled
    And I should not be able to proceed to checkout
