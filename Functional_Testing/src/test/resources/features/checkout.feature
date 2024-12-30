Feature: Checkout Form Validation
  As a user
  I want to verify the checkout form fields maintain correct input values
  So that I can complete my purchase accurately

  Background:
    Given I am logged in to the Sauce Demo website
    And I have "Sauce Labs Backpack" in my cart

  @checkout_form @field_validation @critical
  Scenario: Verify checkout form fields maintain entered values correctly
    When I navigate to the cart page
    And I proceed to the checkout page
    And I enter "John" in the first name field
    And I enter "Doe" in the last name field
    Then the first name field should contain "John"
    And the last name field should contain "Doe"