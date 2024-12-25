Feature: Product Details Add to Cart Functionality
  As a user
  I want to add products to cart from the product details page
  So that I can purchase items after viewing their details

  Scenario: Add product to cart from product details page
    Given I am logged in and on the products page
    When I click on a product name "Sauce Labs Backpack"
    And I am redirected to the product details page
    And I click the Add to Cart button
    Then the product should be added to the cart
    And I should see the cart count increase to "1"
    And the Add to Cart button should change to Remove