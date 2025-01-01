@navigation
Feature: Navigation Functionality
  As a user
  I want to use the navigation menu
  So that I can access different parts of the application

  Background:
    Given I am logged in and on the products page

  @navigation @cart @critical
  Scenario: Access cart page
    When I click the cart button
    Then I should be on the cart page

  @navigation @menu @critical
  Scenario: Use menu navigation
    When I click the menu button
    And I click the All Items link
    Then I should be on the inventory page

  @navigation @authentication @critical
  Scenario: Logout functionality
    When I click the menu button
    And I click the Logout link
    Then I should be on the login page

  @navigation @cart @reset @medium
  Scenario: Reset removes item from cart
    Given I have added "Sauce Labs Backpack" to the cart
    When I click the menu button
    And I click the Reset App State link
    Then the "Sauce Labs Backpack" should not be in the cart