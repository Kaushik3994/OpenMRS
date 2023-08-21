package stepDefinition;

import com.qa.util.ScenarioFactory;
import com.relevantcodes.extentreports.LogStatus;
import helpers.ExecutionHelper;
//import helpers.LocalDriverManager;
import io.cucumber.java.en.And;
import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.util.DriverFactory;
import com.qa.util.ExcelDataReader;
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


	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);

		//ExtentLogs.log("Page title is: " + title);
		ExecutionHelper.getLogger().log(LogStatus.PASS, "Page title is: " + title +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));



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



	public Map<String, String> getLoginData(String testCaseId,String usernameKey,String passwordKey) throws IOException {
		String systemPath = System.getProperty("user.dir"); // Get the current working directory
		String filePath = systemPath + "\\src\\test\\resources\\testdata.xlsx"; // Construct the complete file path

		List<Map<String, String>> testData = ExcelDataReader.readExcelData(filePath, "Sheet1");
		for (Map<String, String> data : testData) {
			if (data.get("Test Case ID").equalsIgnoreCase(testCaseId)) {
				Map<String, String> loginData = new HashMap<>();
				loginData.put(usernameKey, data.get(usernameKey));
				loginData.put(passwordKey, data.get(passwordKey));
				return loginData;
			}
		}
		throw new IllegalArgumentException("Test case ID not found in test data: " + testCaseId);
	}





	@When("^I enter \"(.*)\" and \"(.*)\"$")
	public void iEnterUsernameAndPassword(String usernameKey, String passwordKey) throws IOException {
		String testCaseId = ScenarioFactory.getCurrentScenarioTestCaseIDs();
		Map<String, String> loginData = getLoginData(testCaseId, usernameKey, passwordKey);
		String username = loginData.get(usernameKey);
		String password = loginData.get(passwordKey);

		// Rest of your step definition logic
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);

		ExecutionHelper.getLogger().log(LogStatus.PASS, "User enters userName" + username + "and Password" + password +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

	}

}








