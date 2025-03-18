Feature: Car Loan EMI Calculation

  Background: User is on the EMI Calculator homepage
    Given User launches the EMI Calculator application

  Scenario: Calculate Car Loan EMI using data table
    When User navigates to "Car Loan EMI" section
    And User enters the following loan details:
      | Loan Amount | Interest Rate | Loan Tenure (Years) |
      | 2000000     | 9%            | 6                   |
    Then User verifies and displays the calculated EMI details
