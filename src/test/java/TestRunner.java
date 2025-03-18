import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
@Test
@CucumberOptions(features = "features" , glue = "stepdefinition")
public class TestRunner  {

}
