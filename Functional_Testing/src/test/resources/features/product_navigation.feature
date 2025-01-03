@type:navigation @product
Feature: Product Details Navigation
  As a user
  I want to click on product links
  So that I can view the correct product details

  Background:
    Given I am logged in and on the products page

  @verify_product_navigation @normal @defect
  Scenario: Verify product link redirects to correct product
    When I click on the "Sauce Labs Backpack" product link
    Then I should see the correct product details for "Sauce Labs Backpack"