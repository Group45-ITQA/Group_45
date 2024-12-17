Feature: Get All Books API
  As a library administrator
  I want to retrieve all books in the system
  So that I can view the complete library catalog

  @GetAllBooks @Smoke
  Scenario: Successfully retrieve all books as admin user
    Given I am authenticated as an admin user with credentials
    When I send a request to get all books from the library
    Then the API response status code should be 200
    And the response should contain a list of all books
    And each book in the response should have the required fields