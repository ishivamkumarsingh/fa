package pageobject;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    private By carLoanLink = By.xpath("//a[contains(text(),'Car Loan')]");
    private By loanAmountField = By.id("loanamount");
    private By interestRateField = By.id("loaninterest");
    private By tenureField = By.id("loanterm");
    private By tenureSlider = By.xpath("//div[@id='loantermslider']/span");
    private By emiAmount = By.xpath("//div[@id='emiamount']/p/span");
    private By totalInterest = By.xpath("//div[@id='emitotalinterest']/p/span");
    private By totalPayment = By.xpath("//div[@id='emitotalamount']/p/span");

    // Navigate to Car Loan EMI Section
    public void navigateToCarLoanEMI() {
        WebElement carLoanElement = wait.until(ExpectedConditions.elementToBeClickable(carLoanLink));
        carLoanElement.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("car-loan"), "Failed to navigate to Car Loan EMI page");
    }

    // Enter Loan Amount
    public void enterLoanAmount(String amount) {
        WebElement loanAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountField));
        loanAmountElement.clear();
        loanAmountElement.sendKeys(amount);
        Assert.assertEquals(getValue(loanAmountElement), amount, "Loan amount was not set correctly");
    }

    // Enter Interest Rate
    public void enterInterestRate(String interestRate) {
        WebElement interestRateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateField));
        interestRateElement.clear();
        interestRateElement.sendKeys(interestRate);
        Assert.assertEquals(getValue(interestRateElement), interestRate, "Interest rate was not set correctly");
    }

    // Enter Loan Tenure
    public void enterLoanTenure(String tenure) {
        WebElement tenureElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tenureField));
        tenureElement.clear();
        tenureElement.sendKeys(tenure);
        Assert.assertEquals(getValue(tenureElement), tenure, "Loan tenure was not set correctly");
    }

    // Drag and Drop Loan Tenure Slider
    public void dragAndDropLoanTenure(String tenure) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(tenureSlider));
        // Calculate the appropriate x-offset based on the tenure value
        int targetTenure = Integer.parseInt(tenure);
        int maxTenure = 7; // Assuming max tenure is 7 years
        int pixelWidth = 200; // Approximate width of slider in pixels
        
        int xOffset = (targetTenure * pixelWidth) / maxTenure - pixelWidth/2;
        
        actions.dragAndDropBy(slider, xOffset, 0).perform();
        
        // Verify the tenure was set correctly by checking the tenure field value
        WebElement tenureElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tenureField));
        Assert.assertEquals(getValue(tenureElement), tenure, "Loan tenure was not set correctly via slider");
    }

    // Get calculated EMI details
    public Map<String, String> getEMIDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emiAmount));
        
        Map<String, String> emiDetails = new HashMap<>();
        emiDetails.put("Loan EMI", wait.until(ExpectedConditions.visibilityOfElementLocated(emiAmount)).getText());
        emiDetails.put("Total Interest Payable", wait.until(ExpectedConditions.visibilityOfElementLocated(totalInterest)).getText());
        emiDetails.put("Total Payment", wait.until(ExpectedConditions.visibilityOfElementLocated(totalPayment)).getText());
        
        return emiDetails;
    }
    
    // Helper method to get value from input elements
    private String getValue(WebElement element) {
        return element.getAttribute("value");
    }
}