package orbitz.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = "orbitz.stepdefinition",
		monochrome = true,
		tags={"@Vuelo,@Paquete"}
		)
public class TestRunner {
	
}
