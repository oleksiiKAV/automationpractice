@LoginProfile
Feature: Login Profile
  As an employee of the company
  I want to login my employee profile using my credentials
  In order to collaborate with my colleagues

  Scenario: Successful login
    Given I am on home page
    Then I click on signIn link
    When I fill login 'oleg.kh81@gmail.com'
    And I fill password '123qwerty'
    And click signIn button
    Then I should see 'Oleg Afanasiev' link
    Then I should make logout
