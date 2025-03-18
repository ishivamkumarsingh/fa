package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.CarLoanEMI;

public class TC3_carloan {
    WebDriver driver;
    CarLoanEMI carLoanPage;
    Actions actions;

    @Given("User is on the Car Loan EMI page")
    public void user_is_on_the_car_loan_emi_page() {
        driver = BaseTest.getDriver();
        carLoanPage = new CarLoanEMI(driver);
        actions = new Actions(driver);
        carLoanPage.navigateToCarLoanEMI();
    }

    @When("User enters Car Loan Amount {string}")
    public void user_enters_car_loan_amount(String amount) {
        carLoanPage.enterLoanAmount(amount);
    }

    @And("User enters Interest Rate {string} for Car Loan")
    public void user_enters_interest_rate_for_car_loan(String rate) {
        carLoanPage.enterInterestRate(rate);
    }

    @And("User sets Loan Tenure to {string} years using slider")
    public void user_sets_loan_tenure_to_using_slider(String tenure) {
        carLoanPage.dragAndDropLoanTenure(tenure);
    }

    @Then("User verifies the EMI details for Car Loan")
    public void user_verifies_the_emi_details_for_car_loan() {
        System.out.println("EMI Details: " + carLoanPage.validateEMIDetails());
        // Don't quit the driver here; it will be managed by hooks
    }
}