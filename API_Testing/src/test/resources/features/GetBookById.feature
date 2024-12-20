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
    When I send a request to get the book with ID "1"
    Then the get book by id response status code should be 200
    And the response should contain valid book details