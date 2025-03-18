# features/personalloan.feature
Feature: Personal Loan EMI Calculation

  Background: User is on the EMI Calculator homepage
    Given User launches the EMI Calculator application

  Scenario: Calculate Personal Loan EMI
    When User navigates to "Personal Loan EMI" section
    And User enters loan amount as 750000
    And User sets the interest rate to 15 using drag and drop
    And User enters loan tenure as 5 years
    Then User validates and displays the following EMI details:
      | Loan EMI | Total Interest Payable | Total Payment |