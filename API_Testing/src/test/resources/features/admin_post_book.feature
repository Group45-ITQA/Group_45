@epic:LibraryAPI
@feature:BookManagement
@story:AdminCreateBook
Feature: Admin Create Book API
  As a library administrator
  I want to create and validate books
  So that I can maintain a high-quality library catalog

  Background:
    Given I am authenticated as "admin" user

  @PostBook
  @severity:critical
  Scenario: Successfully create a book via POST endpoint
    Given I have valid book details
    When I send a request to create a book
    Then The status code of the response should be 201
    And the response should contain the created book details

  @PostBook
  @severity:critical
  Scenario: Cannot create a book without a title
    Given I have book details without a title
    When I send a request to create a book without title
    Then The status code of the response should be 400
    And the response should indicate a "title" is required

  @PostBook
  @severity:critical
  Scenario: Cannot create a book without an author
    Given I have book details without an author
    When I send a request to create a book without author
    Then The status code of the response should be 400
    And the response should indicate a "author" is required

  @PostBook
  @severity:critical
  Scenario: Cannot create duplicate book
    Given I have valid book details
    And I have added the book to the system
    When I send a request to add the same book again
    Then The status code of the response should be 409

  @PostBook
  @severity:normal
  Scenario: Cannot create book with numeric author
     Given I have book details with numeric author
     When I send a request to create a book with invalid author
     Then The status code of the response should be 400