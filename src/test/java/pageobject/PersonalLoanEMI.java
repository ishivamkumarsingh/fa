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
    private By personalLoanLink = By.xpath("//a[contains(text(),'Personal Loan')]");
    private By loanAmountField = By.id("loanamount");
    private By interestRateField = By.id("loaninterest");
    private By interestRateSlider = By.xpath("//div[@id='loaninterestslider']/span");
    private By tenureField = By.id("loanterm");
    private By emiAmount = By.xpath("//div[@id='emiamount']/p/span");
    private By totalInterest = By.xpath("//div[@id='emitotalinterest']/p/span");
    private By totalPayment = By.xpath("//div[@id='emitotalamount']/p/span");

    // Navigate to Personal Loan EMI Section
    public void navigateToPersonalLoanEMI() {
        WebElement personalLoanElement = wait.until(ExpectedConditions.elementToBeClickable(personalLoanLink));
        personalLoanElement.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("personal-loan"), "Failed to navigate to Personal Loan EMI page");
    }

    // Enter Loan Amount
    public void enterLoanAmount(String amount) {
        WebElement loanAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountField));
        loanAmountElement.clear();
        // Remove any commas from the input
        String cleanAmount = amount.replaceAll(",", "");
        loanAmountElement.sendKeys(cleanAmount);
        
        // Verify the amount was set correctly
        String actualValue = loanAmountElement.getAttribute("value").replaceAll(",", "");
        Assert.assertEquals(actualValue, cleanAmount, "Loan amount was not set correctly");
    }

    // Drag and Drop Interest Rate Slider
    public void dragAndDropInterestRate(String interestRate) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateSlider));
        WebElement rateField = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateField));
        
        // Get current value
        String currentValue = rateField.getAttribute("value");
        double currentRate = Double.parseDouble(currentValue);
        double targetRate = Double.parseDouble(interestRate);
        
        // Calculate how much to drag
        // Assuming slider has a range from 0 to maxRate (e.g., 20%)
        double maxRate = 20.0; // Adjust based on the actual max value
        int sliderWidth = 200; // Approximate width in pixels
        
        int pixelsToMove = (int)((targetRate - currentRate) * sliderWidth / maxRate);
        
        actions.dragAndDropBy(slider, pixelsToMove, 0).perform();
        
        // Verify the rate was set correctly by checking the rate field
        String actualValue = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateField)).getAttribute("value");
        double actualRate = Double.parseDouble(actualValue);
        
        // Allow some wiggle room in the slider precision
        Assert.assertTrue(Math.abs(actualRate - targetRate) < 1.0, 
                          "Interest rate was not set correctly via slider. Expected around: " + targetRate + ", Got: " + actualRate);
    }

    // Enter Loan Tenure
    public void enterTenure(String tenure) {
        WebElement tenureElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tenureField));
        tenureElement.clear();
        tenureElement.sendKeys(tenure);
        Assert.assertEquals(tenureElement.getAttribute("value"), tenure, "Loan tenure was not set correctly");
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
}