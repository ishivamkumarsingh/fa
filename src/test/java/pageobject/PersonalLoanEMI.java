package pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalLoanEMI {
	 WebDriver driver;
	    Actions actions;
	    WebDriverWait wait;

	    public PersonalLoanEMI(WebDriver driver) {
	        this.driver = driver;
	        this.actions = new Actions(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Locators
	    private By loanAmountField = By.id("loanamount");
	    private By interestRateSlider = By.xpath("//div[@id='loaninterestslider']/span");
	    private By tenureField = By.id("loanterm");
	    private By emiResult = By.id("emiamount");

	    // Navigate to Personal Loan EMI Section
	    public void navigateToPersonalLoanEMI() {
	        driver.findElement(By.linkText("Personal Loan EMI Calculator")).click();
	    }

	    // Enter Loan Amount
	    public void enterLoanAmount(String amount) {
	        driver.findElement(loanAmountField).clear();
	        driver.findElement(loanAmountField).sendKeys(amount);
	    }

	    // Drag and Drop Interest Rate Slider
	    public void dragAndDropInterestRate(String interestRate) {
	        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateSlider));
	        actions.dragAndDropBy(slider, 50, 0).perform(); // Adjust as per required percentage
	    }

	    // Enter Loan Tenure
	    public void enterTenure(String tenure) {
	        driver.findElement(tenureField).clear();
	        driver.findElement(tenureField).sendKeys(tenure);
	    }

	    // Validate EMI Details
	    public String validateEMIDetails() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(emiResult)).getText();
	    }
}
