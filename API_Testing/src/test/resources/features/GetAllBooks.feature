@epic:LibraryAPI
@feature:BookManagement
@story:GetAllBooks
Feature: Get All Books API
  As a library administrator
  I want to retrieve all books in the system
  So that I can view the complete library catalog

  @GetAllBooks
  @severity:critical
  Scenario: Successfully retrieve all books via GET endpoint
    Given I am authenticated as an admin user with credentials
    When I send a request to get all books from the library
    Then the get all books response status code should be 200