@epic:LibraryAPI
@feature:BookManagement
@story:RegularUserCreateBook
Feature: Regular User Create Book API
  As a regular library user
  I want to create books
  So that I can contribute to the library catalog

  Background:
    Given I am authenticated as "regular" user

  @PostBook
  @severity:normal
  @bug:LIBRARY-103
  @defect:UserPermissions
  Scenario: Regular user can create a book
    Given I have valid book details
    When I send a request to create a book
    Then The status code of the response should be 201
    And the response should contain the created book details