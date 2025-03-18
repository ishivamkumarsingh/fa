package pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeLoanEMI {
	 WebDriver driver;
	    Actions actions;
	    WebDriverWait wait;

	    public HomeLoanEMI(WebDriver driver) {
	        this.driver = driver;
	        this.actions = new Actions(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }

	    // Locators
	    private By loanAmountSlider = By.xpath("//div[@id='loanamountslider']/span");
	    private By interestRateField = By.id("loaninterest");
	    private By tenureField = By.id("loanterm");
	    private By emiResult = By.id("emiamount");

	    // Navigate to Home Loan EMI Section
	    public void navigateToHomeLoanEMI() {
	        driver.findElement(By.linkText("Home Loan EMI Calculator")).click();
	    }

	    // Drag and Drop Loan Amount Slider
	    public void dragAndDropLoanAmount(String amount) {
	        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountSlider));
	        actions.dragAndDropBy(slider, 100, 0).perform(); // Adjusting as per amount scale
	    }

	    // Enter Interest Rate
	    public void enterInterestRate(String interestRate) {
	        driver.findElement(interestRateField).clear();
	        driver.findElement(interestRateField).sendKeys(interestRate);
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
