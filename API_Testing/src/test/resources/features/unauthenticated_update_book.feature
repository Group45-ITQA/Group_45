@epic:LibraryAPI
@feature:BookManagement
@story:UpdateBookAccess
Feature: Update Book Access Control API
  As a library system
  I want to control access to book updates
  So that I can maintain system security and data integrity

  Background:
    Given I am authenticated as "no" user

  @UpdateBook
  @severity:critical
  Scenario: Cannot update book without authentication
    Given I have a valid book ID to update
    When I send a PUT request to update the book with the stored ID
    Then The status code of the response should be 401