@epic:LibraryAPI
@feature:BookManagement
@story:CreateBook
Feature: Create Book API
  As a library administrator
  I want to create new books
  So that I can add them to the library catalog

  @PostBook
  @severity:critical
  Scenario: Successfully create a book via POST endpoint
    Given I am authenticated as an admin user to add a new book
    And I have valid book details
    When I send a request to create a book
    Then the create book response status code should be 201
    And the response should contain the created book details
