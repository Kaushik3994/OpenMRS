package stepDefinition;

import com.qa.util.ScenarioFactory;
import com.relevantcodes.extentreports.LogStatus;
import helpers.ExecutionHelper;
//import helpers.LocalDriverManager;
import io.cucumber.java.en.And;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.util.DriverFactory;
import com.qa.util.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class LoginPageSteps {

	private static String title;
	private List<Map<String, String>> testData;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());


	@When("user enters username {string}")
	public void user_enters_username(String username) {
		loginPage.enterUserName(username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) throws InterruptedException {
		loginPage.enterPassword(password);
		Thread.sleep(2000);
	}

	@When("user selects location {string} session")
	public void user_selects_location_session(String session) throws IOException {
		switch (session) {

			case "Inpatient Ward":
				loginPage.clickOnSession();
				ExecutionHelper.getLogger().log(LogStatus.PASS, "User enters into session " + session +ExecutionHelper.getLogger()
						.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

				break;

			default:

		}
	}


	@When("user clicks on {string} button")
	public void user_clicks_on_login_button(String buttonName) {

		switch (buttonName) {
			case "Login":
				loginPage.clickOnLogin();
				break;
			default:
//				DoNothing

		}
	}

	@Given("user is on login page")
	public void user_is_on_login_page() {
		DriverFactory.getDriver()
				.get("http://localhost:8081/openmrs-standalone/login.htm");

	}

	public boolean isLoginSuccessful(String title) {
		return title.contains("Home");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		title = loginPage.getPageTitle();
	}



	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) throws InterruptedException, IOException {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(title.contains(expectedTitleName));
		Thread.sleep(2000);
		System.out.println("Expected Title is "+expectedTitleName +" Actual title is " +title );

		ExecutionHelper.getLogger().log(LogStatus.PASS, "title contains" + expectedTitleName +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

	}

	@Then("the user should be able to view {string}")
	public void the_user_should_be_able_to_view(String sectionString) throws IOException {

	//	System.out.println("Expected accounts section list: " + sectionString);

		List<String> actualAccountSectionsList = loginPage.getAppsSectionsList();
	//	System.out.println("Actual accounts section list: " + actualAccountSectionsList);

		Assert.assertTrue(loginPage.stringFound(sectionString, actualAccountSectionsList));

		ExecutionHelper.getLogger().log(LogStatus.PASS, "The page contains the tile "+sectionString  +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

	}


	@And("user clicks the app {string}")
	public void userClicksTheApp(String appName) throws InterruptedException, IOException {
		switch (appName) {
			case "Find Patient Record":
				loginPage.clickOnApp(appName);
				Thread.sleep(2000);

				ExecutionHelper.getLogger().log(LogStatus.PASS, "User clicks on tile "+appName +ExecutionHelper.getLogger()
						.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));
				break;
			default:
				break;
		}
	}


	@When("I enter {string} as {string} and {string}")
	public void iEnterAsAnd(String scenario, String username, String password) {
		
	}

	@When("I enter username and password with {string}")
	public void iEnterUsernameAndPasswordWith(String scenarioToFind) throws IOException, InvalidFormatException {

		ExcelReader reader = new ExcelReader();
		String systemDir = System.getProperty("user.dir");
		String filePath = systemDir + "/src/test/resources/testdata.xlsx"; // Path with system directory
		String username = "";
		String password = "";
		String scenarioName = "";

		List<Map<String, String>> testData = reader.getData(filePath, "LoginData");

		// Search for the index of the scenario in the testData
		int index = -1;
		for (int i = 0; i < testData.size(); i++) {
			if (testData.get(i).get("Scenario").equals(scenarioToFind)) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			username = testData.get(index).get("username");
			password = testData.get(index).get("password");
			scenarioName = scenarioToFind;

		} else {
			String errorMessage = "Scenario '" + scenarioToFind + "' not found in Excel data.";
			String screenshotPath = ExecutionHelper.takeScreenshot(DriverFactory.getDriver());

			// Log the error message and attach the screenshot
			ExecutionHelper.getLogger().log(LogStatus.FAIL, errorMessage + ExecutionHelper.getLogger().addScreenCapture(screenshotPath));
		}

		loginPage.enterUserName(username);
		loginPage.enterPassword(password);

		ExecutionHelper.getLogger().log(LogStatus.PASS, "User enters userName" + username + "and Password" + password +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

	}

	@Then("the login should be successful")
	public void theLoginShouldBeSuccessful() throws IOException {
		if (isLoginSuccessful(title)) {
			ExecutionHelper.getLogger().log(LogStatus.PASS, "Page title is: " + title + ExecutionHelper.getLogger().addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));
		} else {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Login was unsuccssful: " + ExecutionHelper.getLogger().addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));
		}
	}
}








