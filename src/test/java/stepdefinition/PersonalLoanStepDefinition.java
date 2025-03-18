package stepdefinition;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.PersonalLoanEMI;

public class PersonalLoanStepDefinition {
    WebDriver driver;
    PersonalLoanEMI personalLoanPage;
    
    public PersonalLoanStepDefinition() {
        this.driver = BaseTest.getDriver();
        this.personalLoanPage = new PersonalLoanEMI(driver);
    }
    
    @When("User navigates to {string} section")
    public void user_navigates_to_personal_loan_section(String section) {
        if (section.equals("Personal Loan EMI")) {
            personalLoanPage.navigateToPersonalLoanEMI();
        }
    }
    
    @When("User enters loan amount as {int}")
    public void user_enters_loan_amount(Integer amount) {
        personalLoanPage.enterLoanAmount(amount.toString());
    }
    
    @When("User sets the interest rate to {int} using drag and drop")
    public void user_sets_interest_rate_using_drag_and_drop(Integer rate) {
        personalLoanPage.dragAndDropInterestRate(rate.toString());
    }
    
    @When("User enters loan tenure as {int} years")
    public void user_enters_loan_tenure_years(Integer tenure) {
        personalLoanPage.enterTenure(tenure.toString());
    }
    
    @Then("User validates and displays the following EMI details:")
    public void user_validates_and_displays_the_emi_details(DataTable dataTable) {
        // Get the EMI details from the page
        Map<String, String> emiDetails = personalLoanPage.getEMIDetails();
        
        // Output the details
        System.out.println("Personal Loan EMI Details:");
        System.out.println("Loan EMI: " + emiDetails.get("Loan EMI"));
        System.out.println("Total Interest Payable: " + emiDetails.get("Total Interest Payable"));
        System.out.println("Total Payment: " + emiDetails.get("Total Payment"));
        
        // Verify that all required details are displayed
        Assert.assertNotNull(emiDetails.get("Loan EMI"), "Loan EMI is not displayed");
        Assert.assertNotNull(emiDetails.get("Total Interest Payable"), "Total Interest Payable is not displayed");
        Assert.assertNotNull(emiDetails.get("Total Payment"), "Total Payment is not displayed");
    }
}