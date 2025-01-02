@epic:LibraryAPI
@feature:BookManagement
@story:GetAllBooks
Feature: Get All Books API
  As a library administrator
  I want to retrieve all books in the system
  So that I can view the complete library catalog

  Background:
    Given I am authenticated as "admin" user

  @GetAllBooks
  @severity:critical
  Scenario: Successfully retrieve all books via GET endpoint
    When I send a request to get all books from the library
    Then The status code of the response should be 200
    And the response should contain a list of books with valid details