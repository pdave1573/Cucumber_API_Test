package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
 
@RunWith(Cucumber.class)
@CucumberOptions(tags = "", 
				features = "src/test/java/features", 
				glue = "stepdefs")
 
class TestRunner {
    // CucumberTestSteps are glued automatically as they are in the same package.
}