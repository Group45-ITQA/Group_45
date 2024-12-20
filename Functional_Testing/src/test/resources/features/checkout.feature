Feature: Checkout Form Functionality
  As a user
  I want to verify the first name field does not retain entered values incorrectly
  So that I can understand the bug present in the checkout form

  Scenario: Verify the first name field does not retain entered values incorrectly when entering last name
    Given I am logged in to the Sauce Demo website
    And I have an item in my cart
    When I navigate to the cart page
    And I proceed to the checkout page
    And I enter "John" in the first name field
    And I enter "Doe" in the last name field
    Then the first name field should contain "e"
    And the last name field should be empty
