@epic:LibraryAPI
@feature:BookManagement
@story:CreateBookWithoutTitle
Feature: Post Book Without Title
  As a library administrator
  I want to ensure books cannot be created without a title
  So that the library catalog maintains data integrity

  @PostWithoutTitle
  @severity:critical
  Scenario: Attempting to create a book without a title
    Given I am authenticated as an admin user to add a book without a title
    And I have book details without a title
    When I send a request to create a book without a title
    Then the response status code for missing title should be 400
    And the response should indicate a title is required
