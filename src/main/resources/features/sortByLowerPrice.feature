@SortBy
  Feature: I need inspect sort product by lower price

    Scenario: Sort product by lower price
      Given I am on home page
      Then I go on women page
      Then I click on dresses link
      Then I click on to list link
      And  I sort product by 'Price: Lowest first'
      And  I check actual product list and expected product list

