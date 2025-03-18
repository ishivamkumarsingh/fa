package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CommonSteps {
    private WebDriver driver;
    
    @Given("User launches the EMI Calculator application")
    public void user_launches_the_emi_calculator_application() {
        driver = BaseTest.getDriver();
        BaseTest.navigateToBaseUrl();
    }
    
    @When("User navigates to {string} section")
    public void user_navigates_to_section(String section) {
        // This step will be handled by specific step definition classes
        // as each loan type has its own navigation method
    }
}