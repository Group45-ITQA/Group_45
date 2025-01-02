@epic:LibraryAPI
@feature:BookManagement
@story:RegularUserDeleteBook
Feature: Regular User Delete Book API
  As a regular library user
  I want to understand my deletion permissions
  So that I know what operations I can perform

  Background:
    Given I am authenticated as "regular" user

  @DeleteBook
  @severity:critical
  @bug:LIBRARY-104
  @defect:UserPermissions
  Scenario: Regular user cannot delete a book
    Given I have a valid book ID to delete
    When I send a DELETE request to remove the book with the stored ID
    Then The status code of the response should be 403