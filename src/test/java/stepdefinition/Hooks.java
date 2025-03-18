package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        // Initialize driver through BaseTest but don't navigate yet
        // Each step definition will handle specific navigation
    }
    
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = BaseTest.getDriver();
        
        // Take screenshot if scenario fails
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of failure");
        }
        
        // Quit the driver after scenario completes
        BaseTest.quitDriver();
        System.out.println("Completed scenario: " + scenario.getName());
    }
}