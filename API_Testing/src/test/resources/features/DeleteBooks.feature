@epic:LibraryAPI
@feature:BookManagement
@story:DeleteBook
Feature: Delete Book API
  As a library system user
  I want to delete books from the system
  So that I can manage the book inventory

  @DeleteBook
  @severity:blocker
  Scenario: Admin successfully deletes a book
    Given I am authenticated as an admin user to delete a book
    And I have a valid book ID to delete
    When I send a request to delete the book
    Then the delete book response status code should be 200
    And the book should no longer exist in the system

  @DeleteBook
  @severity:critical
  @bug:LIBRARY-102
  @defect:UserPermissions
  Scenario: Regular user attempts to delete a book - should be forbidden
    Given I am authenticated as a regular user to delete a book
    And I have a valid book ID to delete
    When I send a request to delete the book
    Then the delete book response status code should be 403
    And the response should contain a permission denied message

  @DeleteBook
  @severity:normal
  Scenario: Attempt to delete a non-existent book
    Given I am authenticated as an admin user to delete a book
    When I send a request to delete a book with ID "999999"
    Then the delete book response status code should be 404
    And the response should contain a book not found message

  @DeleteBook
  @severity:medium
  @bug:LIBRARY-103
  @defect:InvalidIDHandling
  Scenario: Attempt to delete a book with invalid ID format
    Given I am authenticated as an admin user to delete a book
    When I send a request to delete a book with ID "abc"
    Then the delete book response status code should be 400
    And the response should contain an invalid ID format message
