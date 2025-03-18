package pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarLoanEMI {

	WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    public CarLoanEMI(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By loanAmountField = By.id("loanamount");
    private By interestRateField = By.id("loaninterest");
    private By tenureSlider = By.xpath("//div[@id='loantermslider']/span");
    private By emiResult = By.id("emiamount");

    // Navigate to Car Loan EMI Section
    public void navigateToCarLoanEMI() {
        driver.findElement(By.linkText("Car Loan EMI Calculator")).click();
    }

    // Enter Loan Amount
    public void enterLoanAmount(String amount) {
        driver.findElement(loanAmountField).clear();
        driver.findElement(loanAmountField).sendKeys(amount);
    }

    // Enter Interest Rate
    public void enterInterestRate(String interestRate) {
        driver.findElement(interestRateField).clear();
        driver.findElement(interestRateField).sendKeys(interestRate);
    }

    // Drag and Drop Loan Tenure Slider
    public void dragAndDropLoanTenure(String tenure) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(tenureSlider));
        actions.dragAndDropBy(slider, 80, 0).perform(); // Adjust as per required tenure
    }

    // Validate EMI Details
    public String validateEMIDetails() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emiResult)).getText();
    }
}
