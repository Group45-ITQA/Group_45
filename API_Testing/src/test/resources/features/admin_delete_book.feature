@epic:LibraryAPI
@feature:BookManagement
@story:AdminDeleteBook
Feature: Admin Delete Book API
  As a library administrator
  I want to delete books from the system
  So that I can maintain an accurate library catalog

  Background:
    Given I am authenticated as "admin" user

  @DeleteBook
  @severity:critical
  Scenario: Successfully delete a book via DELETE endpoint
    Given I have a valid book ID to delete
    When I send a DELETE request to remove the book with the stored ID
    Then The status code of the response should be 200

  @DeleteBook
  @severity:normal
  Scenario: Attempt to delete non-existent book
    When I send a DELETE request to remove a non-existent book with ID "999"
    Then The status code of the response should be 404
    And the response should contain book not found message

  @DeleteBook
  @severity:normal
  Scenario: Attempt to delete book with invalid ID format
    When I send a DELETE request to remove a book with invalid ID "abc"
    Then The status code of the response should be 400