@epic:LibraryAPI
@feature:BookManagement
@story:UnauthenticatedCreateBook
Feature: Unauthenticated Create Book API
  As a library system
  I want to prevent unauthorized book creation
  So that I can maintain system security

  Background:
    Given I am authenticated as "no" user

  @PostBook
  @severity:critical
  Scenario: Cannot create book without authentication
    Given I have valid book details
    When I send a request to create a book
    Then The status code of the response should be 401