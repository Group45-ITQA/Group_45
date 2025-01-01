@cart
Feature: Cart Checkout Functionality
  As a user
  I want to verify checkout is not possible with an empty cart
  So that I can ensure the checkout process works correctly

  Background:
    Given I am logged in and on the products page

  @empty_cart @critical
  Scenario: Verify checkout is not possible with an empty cart
    When I navigate to the cart page
    And my shopping cart is empty
    And I proceed to the checkout page
    Then I should not be able to proceed with checkout