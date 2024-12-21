@epic:LibraryAPI
@feature:BookManagement
@story:GetSingleBook
Feature: Get Book By ID API
  As a library user
  I want to retrieve a specific book by its ID
  So that I can view its details

  @GetSingleBook
  @severity:critical
  Scenario: Successfully retrieve a book by its ID
    Given I am authenticated as an admin user to get a specific book
    And I have a valid book ID from the created books
    When I send a request to get the book with the stored ID
    Then the get book by id response status code should be 200
    And the response should contain valid book details

  @GetSingleBook
  @severity:medium
  @bug:LIBRARY-101
  @defect:InvalidIDHandling
  Scenario: Verify API handles non-integer ID format
    Given I am authenticated as an admin user to get a book with invalid ID
    When I send a request to get the book with a non-integer ID "abc"
    Then the get book by invalid id response status code should be 400
    And the response should contain an invalid ID format error message