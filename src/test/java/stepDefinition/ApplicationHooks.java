package stepDefinition;

import java.util.Properties;

import helpers.ExecutionHelper;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.util.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.qa.util.ScenarioFactory; // Import the ScenarioFactory class


public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	private Scenario scenario; // Store the injected Scenario object

	ExecutionHelper helper = new ExecutionHelper();

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}


	@Before(order = 1)
	public void launchBrowser(Scenario scenario) {
		this.scenario = scenario;
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);

		// Set the current scenario using ScenarioFactory
		ScenarioFactory.setScenario(scenario);
		
	}

	@Before(order = 2) // Add this new Before hook with order 2
	public void beforeScenario(Scenario scenario) throws Throwable {
		String scenarioName = scenario.getName();
		String featureName = scenario.getId().split(";")[0];

		String[] featureParts = featureName.split("features/");
		String value = (featureParts.length > 1) ? featureParts[1] : ""; // Use an empty string if the split result is not as expected

		String FeatureName = value.split(".feature")[0];

//		String FeatureName = value.split(".feature")[0];
//		System.out.println("featureName : " + value.split(".feature")[0]);
//		System.out.println("scenarioName : " + scenarioName);
//		PageObject.initialize(FeatureName, scenarioName);
		ExecutionHelper.startTest(value.split(".feature")[0] + " : " + scenarioName);
	}


	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

			// Clear the current scenario using ScenarioFactory
			ScenarioFactory.setScenario(null);

		}
	}

}
