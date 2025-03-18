package stepdefinition;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    private static WebDriver driver;
    private static final String BASE_URL = "https://emicalculator.net/";

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }
    
    public static void navigateToBaseUrl() {
        getDriver().get(BASE_URL);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}