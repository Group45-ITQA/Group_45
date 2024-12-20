Feature: Cart Checkout Functionality
  As a user
  I want to verify checkout is possible with an empty cart
  So that I can ensure the checkout process works correctly

  Scenario: Verify checkout is possible with an empty cart
    Given I am logged in to the Sauce Demo website
    When I navigate to the cart page
    And my shopping cart is empty
    And I click on the checkout button
    Then I should be able to proceed to the checkout information page
