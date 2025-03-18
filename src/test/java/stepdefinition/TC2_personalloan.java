package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.PersonalLoanEMI;

public class TC2_personalloan {
	 WebDriver driver = new ChromeDriver();
	    PersonalLoanEMI personalLoanPage = new PersonalLoanEMI(driver);
	    Actions actions = new Actions(driver);

	    @Given("User is on the Personal Loan EMI page")
	    public void user_is_on_the_personal_loan_emi_page() {
	        personalLoanPage.navigateToPersonalLoanEMI();
	    }

	    @When("User enters Personal Loan Amount {string}")
	    public void user_enters_personal_loan_amount(String amount) {
	        personalLoanPage.enterLoanAmount(amount);
	    }

	    @And("User sets Interest Rate to {string} using slider")
	    public void user_sets_interest_rate_to_using_slider(String rate) {
	        personalLoanPage.dragAndDropInterestRate(rate);
	    }

	    @And("User enters Loan Tenure {string} years for Personal Loan")
	    public void user_enters_loan_tenure_years_for_personal_loan(String tenure) {
	        personalLoanPage.enterTenure(tenure);
	    }

	    @Then("User verifies the EMI details for Personal Loan")
	    public void user_verifies_the_emi_details_for_personal_loan() {
	        System.out.println("EMI Details: " + personalLoanPage.validateEMIDetails());
	        driver.quit();
	    }
}
