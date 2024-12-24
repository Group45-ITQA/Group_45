@product @image
Feature: Product Image Verification
  As a QA engineer
  I want to verify product images
  So that I can ensure each product has unique images

  Background:
    Given I am logged in and on the products page for image verification

  @image-verification @defect @medium
  Scenario: Verify product images are unique
    When I verify the product images
    Then all product images should be unique