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
    Given I am authenticated as an admin user
    And I have book details without a title
    When I send a request to create a book without title
    Then the response status code should be 400
    And the response should indicate a "title" is required

  @PostWithoutAuthor
  @severity:critical
  Scenario: Attempting to create a book without an author
    Given I am authenticated as an admin user
    And I have book details without an author
    When I send a request to create a book without author
    Then the response status code should be 400
    And the response should indicate a "author" is required