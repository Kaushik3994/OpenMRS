package testrunners;

import com.qa.util.DriverFactory;
import helpers.ExecutionHelper;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;



@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"stepDefinition"},
		tags = "@LoginPage",
	//	tags = "@T1 or @T2",
		dryRun = false, monochrome = true
		//	tags = "@T1",

)
public class MyTestRunner extends AbstractTestNGCucumberTests{

	@AfterMethod(alwaysRun=true)
	public void endTestExecution(){
		DriverFactory.getDriver().quit();
		try {
			ExecutionHelper.extentReportFlush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override

	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
