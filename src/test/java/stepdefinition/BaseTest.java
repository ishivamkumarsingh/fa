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
            WebDriver driver = new ChromeDriver();
           
            driver.manage().window().maximize();
            driver.get(BASE_URL);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}