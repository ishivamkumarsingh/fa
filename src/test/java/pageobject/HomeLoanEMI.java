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
    private By homeLoanLink = By.xpath("//a[contains(text(),'Home Loan')]");
    private By loanAmountField = By.id("loanamount");
    private By loanAmountSlider = By.xpath("//div[@id='loanamountslider']/span");
    private By interestRateField = By.id("loaninterest");
    private By tenureField = By.id("loanterm");
    private By emiAmount = By.xpath("//div[@id='emiamount']/p/span");
    private By totalInterest = By.xpath("//div[@id='emitotalinterest']/p/span");
    private By totalPayment = By.xpath("//div[@id='emitotalamount']/p/span");

    // Navigate to Home Loan EMI Section
    public void navigateToHomeLoanEMI() {
        WebElement homeLoanElement = wait.until(ExpectedConditions.elementToBeClickable(homeLoanLink));
        homeLoanElement.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("home-loan"), "Failed to navigate to Home Loan EMI page");
    }

    // Drag and Drop Loan Amount Slider
    public void dragAndDropLoanAmount(String amount) {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountSlider));
        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountField));
        
        // Get current value
        String currentValue = amountField.getAttribute("value").replaceAll(",", "");
        long currentAmount = Long.parseLong(currentValue);
        long targetAmount = Long.parseLong(amount);
        
        // Calculate how much to drag
        // Assuming slider has a range from 0 to maxAmount (e.g., 10000000)
        long maxAmount = 10000000; // Adjust based on the actual max value
        int sliderWidth = 300; // Approximate width in pixels
        
        int pixelsToMove = (int)((targetAmount - currentAmount) * sliderWidth / maxAmount);
        
        actions.dragAndDropBy(slider, pixelsToMove, 0).perform();
        
        // Verify the amount was set correctly by checking the amount field
        String actualValue = wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountField))
                                 .getAttribute("value").replaceAll(",", "");
        
        // Allow some wiggle room in the slider precision
        long actualAmount = Long.parseLong(actualValue);
        Assert.assertTrue(Math.abs(actualAmount - targetAmount) < targetAmount * 0.1, 
                          "Loan amount was not set correctly via slider. Expected around: " + targetAmount + ", Got: " + actualAmount);
    }

    // Enter Interest Rate
    public void enterInterestRate(String interestRate) {
        WebElement interestRateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRateField));
        interestRateElement.clear();
        interestRateElement.sendKeys(interestRate);
        Assert.assertEquals(interestRateElement.getAttribute("value"), interestRate, "Interest rate was not set correctly");
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