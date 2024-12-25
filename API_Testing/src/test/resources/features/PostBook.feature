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

  @PostBook
  @severity:normal
  @bug:LIBRARY-103
  @defect:UserPermissions
  Scenario: Regular user can create a book
    Given I am authenticated as a regular user to add a new book
    And I have valid book details
    When I send a request to create a book
    Then the create book response status code should be 201
    And the response should contain the created book details

  @PostBook
  @severity:normal
  Scenario: Cannot create book with numeric author
    Given I am authenticated as an admin user to add a new book
    And I have book details with numeric author
    When I send a request to create a book
    Then the create book response status code should be 400
    And the response should indicate invalid author input

  @PostBook
  @severity:critical
  Scenario: Cannot create book without authentication
    Given I am not authenticated
    And I have valid book details
    When I send a request to create a book
    Then the create book response status code should be 401

  @PostDuplicateBook
  @severity:critical
  Scenario: Attempt to add a duplicate book
    Given I am authenticated as an admin user to add a new book
    And I have valid book details
    And I have added the book to the system
    When I send a request to add the same book again
    Then the duplicate book response status code should be 208
    And the response should indicate the book is already added