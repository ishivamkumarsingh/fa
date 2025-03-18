Feature: Home Loan EMI Calculation

  Background: User is on the EMI Calculator homepage
    Given User launches the EMI Calculator application

  Scenario: Calculate Home Loan EMI
    When User navigates to "Home Loan EMI" section
    And User sets the loan amount to 50,00,000 using drag and drop
    And User enters interest rate as 10%
    And User enters loan tenure as 20 years
    Then User verifies and displays the calculated EMI details
