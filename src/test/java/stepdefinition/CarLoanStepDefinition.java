package stepdefinition;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.CarLoanEMI;

public class CarLoanStepDefinition {
    WebDriver driver;
    CarLoanEMI carLoanPage;
    
    public CarLoanStepDefinition() {
        this.driver = BaseTest.getDriver();
        this.carLoanPage = new CarLoanEMI(driver);
    }
    
    @When("User navigates to {string} section")
    public void user_navigates_to_car_loan_section(String section) {
        if (section.equals("Car Loan EMI")) {
            carLoanPage.navigateToCarLoanEMI();
        }
    }
    
    @When("User enters the following loan details:")
    public void user_enters_the_loan_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        
        // Get the values from the data table
        String loanAmount = data.get(0).get("Loan Amount");
        String interestRate = data.get(0).get("Interest Rate");
        String loanTenure = data.get(0).get("Loan Tenure (Years)");
        
        // Enter the values
        carLoanPage.enterLoanAmount(loanAmount);
        carLoanPage.enterInterestRate(interestRate);
        carLoanPage.dragAndDropLoanTenure(loanTenure);
    }
    
    @Then("User validates and displays the following EMI details:")
    public void user_validates_and_displays_the_emi_details(DataTable dataTable) {
        // Get the EMI details from the page
        Map<String, String> emiDetails = carLoanPage.getEMIDetails();
        
        // Output the details
        System.out.println("Car Loan EMI Details:");
        System.out.println("Loan EMI: " + emiDetails.get("Loan EMI"));
        System.out.println("Total Interest Payable: " + emiDetails.get("Total Interest Payable"));
        System.out.println("Total Payment: " + emiDetails.get("Total Payment"));
        
        // Verify that all required details are displayed
        Assert.assertNotNull(emiDetails.get("Loan EMI"), "Loan EMI is not displayed");
        Assert.assertNotNull(emiDetails.get("Total Interest Payable"), "Total Interest Payable is not displayed");
        Assert.assertNotNull(emiDetails.get("Total Payment"), "Total Payment is not displayed");
    }
}