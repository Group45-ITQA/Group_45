Feature: Navigation Functionality
  As a user
  I want to use the navigation menu
  So that I can access different parts of the application

  Scenario: Access cart page
    Given I am logged in as problem user
    When I click the cart button
    Then I should be on the cart page

  Scenario: Use menu navigation
    Given I am logged in as problem user
    When I click the menu button
    And I click the All Items link
    Then I should be on the inventory page

  Scenario: Logout functionality
    Given I am logged in as problem user
    When I click the menu button
    And I click the Logout link
    Then I should be on the login page

  Scenario: Reset removes item from cart
    Given I am logged in as problem user
    And I have added "Sauce Labs Backpack" to cart
    When I click the menu button
    And I click the Reset App State link
    And I click the cart button
    Then I should be on the cart page
    And the "Sauce Labs Backpack" should not be in the cart