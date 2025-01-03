@epic:LibraryAPI
@feature:BookManagement
@story:GetSingleBook
Feature: Get Book By ID API
  As a library user
  I want to retrieve a specific book by its ID
  So that I can view its details

  Background:
    Given I am authenticated as "admin" user

  @GetSingleBook
  @severity:critical
  Scenario: Successfully retrieve a book by its ID
    Given I have a valid book ID from the created books
    When I send a request to get the book with the stored ID
    Then The status code of the response should be 200
    And the response should contain valid book details

  @GetSingleBook
  @severity:medium
  @defect
  Scenario: Verify API handles non-integer ID format
    When I send a request to get the book with a non-integer ID "abc"
    Then The status code of the response should be 400