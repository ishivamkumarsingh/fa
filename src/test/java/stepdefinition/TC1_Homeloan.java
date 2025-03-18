package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.HomeLoanEMI;

public class TC1_Homeloan {
    WebDriver driver;
    HomeLoanEMI homeLoanPage;
    Actions actions;

    @Given("User is on the Home Loan EMI page")
    public void user_is_on_the_home_loan_emi_page() {
        driver = BaseTest.getDriver();
        homeLoanPage = new HomeLoanEMI(driver);
        actions = new Actions(driver);
        homeLoanPage.navigateToHomeLoanEMI();
    }

    @When("User sets Home Loan Amount to {string} using slider")
    public void user_sets_home_loan_amount_using_slider(String amount) {
        homeLoanPage.dragAndDropLoanAmount(amount);
    }

    @And("User enters Interest Rate {string}")
    public void user_enters_interest_rate(String rate) {
        homeLoanPage.enterInterestRate(rate);
    }

    @And("User enters Loan Tenure {string} years")
    public void user_enters_loan_tenure_years(String tenure) {
        homeLoanPage.enterTenure(tenure);
    }

    @Then("User verifies the EMI details for Home Loan")
    public void user_verifies_the_emi_details_for_home_loan() {
        System.out.println("EMI Details: " + homeLoanPage.validateEMIDetails());
        // Don't quit the driver here; it will be managed by hooks
    }
}