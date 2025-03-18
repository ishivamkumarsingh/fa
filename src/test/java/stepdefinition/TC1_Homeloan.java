package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.HomeLoanEMI;

public class TC1_Homeloan {
	  WebDriver driver  = new ChromeDriver();
	    HomeLoanEMI homeLoanPage = new HomeLoanEMI(driver);
	    Actions actions = new Actions(driver);

	    @Given("User is on the Home Loan EMI page")
	    public void user_is_on_the_home_loan_emi_page() {
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
	        driver.quit();
	    }
	}

