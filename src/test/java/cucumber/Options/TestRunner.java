package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(tags = "@Scenario2", 
				features = "src/test/java/features", 
				glue = "stepdefs",
				plugin = {"pretty",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						},
				monochrome = true
				)
 
class TestRunner {
    // CucumberTestSteps are glued automatically as they are in the same package.
}