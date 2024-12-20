@allure.label.layer:api
@allure.label.owner:malithi
Feature: Get All Books API
  As a library administrator
  I want to retrieve all books in the system
  So that I can view the complete library catalog

  @GetAllBooks
  @allure.severity:critical
  Scenario: Successfully retrieve all books via GET endpoint
    Given I am authenticated as an admin user with credentials
    When I send a request to get all books from the library
    Then the API response status code should be 200