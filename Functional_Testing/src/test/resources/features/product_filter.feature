@product
Feature: Product Filtering
  As a user
  I want to filter products
  So that I can view them in my preferred order

  Background:
    Given I am logged in and on the products page

  @filter @defect @medium
  Scenario: Verify price low to high filter
    When I select the "Price (low to high)" filter option
    Then the products should be sorted by price low to high