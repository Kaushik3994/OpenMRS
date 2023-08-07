package testrunners;

import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepDefinition"},
		tags = "@LoginPage",
		dryRun = false, monochrome = true,
	//	tags = "@T1",
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
		}
		
		)
public class MyTestRunner extends AbstractTestNGCucumberTests{
	@Override

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
