@epic:LibraryAPI
@feature:BookManagement
@story:UpdateBook
Feature: Update Book API
  As a library administrator
  I want to update existing books in the system
  So that I can maintain accurate book information

  Background:
    Given I am authenticated as "admin" user

  @UpdateBook
  @severity:critical
  Scenario: Successfully update an existing book
    Given I have a valid book ID to update
    When I send a PUT request to update the book with the stored ID
    Then The status code of the response should be 200
    And the response should contain updated book details

  @UpdateBook
  @severity:normal
  @bug:LIBRARY-102
  @defect:NonExistentIDHandling
  Scenario: Attempt to update non-existent book
    When I send a PUT request to update a non-existent book with ID "999"
    Then The status code of the response should be 404
    And the response should indicate the book was not found

  @UpdateBook
  @severity:normal
  Scenario: Attempt to update book with invalid ID format
    When I send a PUT request to update a book with invalid ID "abc"
    Then The status code of the response should be 400
    And the response should indicate invalid input