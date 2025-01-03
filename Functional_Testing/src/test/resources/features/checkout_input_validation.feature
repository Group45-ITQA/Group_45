@type:checkout_validation @product @cart
Feature: Checkout Form Validation
  As a user
  I want to verify the checkout form fields maintain correct input values
  So that I can complete my purchase accurately

  Background:
    Given I am logged in and on the products page
    And I have added "Sauce Labs Backpack" to the cart

  @checkout_form @firstname_validation @critical
  Scenario: Verify the first name field retains the entered value
    When I navigate to the cart page
    And I proceed to the checkout page
    And I enter "John" in the first name field
    Then the first name field should contain "John"

  @checkout_form @lastname_validation @defect @critical
  Scenario: Verify the last name field retains the entered value
    When I navigate to the cart page
    And I proceed to the checkout page
    And I enter "Doe" in the last name field
    Then the last name field should contain "Doe"
