Feature: Social Media Links
  As a user
  I want to access the social media links
  So that I can follow the company on different platforms

  Background:
    Given I am logged in as problem user

  Scenario Outline: Social media redirects
    When I click the "<platform>" social media link
    Then I should be redirected to the "<platform>" page

    Examples:
      | platform |
      | Twitter  |
      | Facebook |
      | LinkedIn |

