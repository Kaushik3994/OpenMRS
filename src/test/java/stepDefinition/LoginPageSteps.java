package stepDefinition;

import com.pages.RegisterPage;
import com.qa.util.ScenarioFactory;
import com.relevantcodes.extentreports.LogStatus;
import helpers.ExecutionHelper;
//import helpers.LocalDriverManager;
import helpers.Utils;
import io.cucumber.java.en.And;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.util.DriverFactory;
import com.qa.util.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helpers.Utils.generateRandomNumber;


public class LoginPageSteps {

	// Define a class-level variable to store the counter
	String randomNumber = Utils.generateRandomNumber(4);
	private static String title;
	public static String firstName = "";
	public static String lastName = "";
	public static String email = "";
	public static String passwordValue = "";
	public static String testdata = "";
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private RegisterPage registerPage = new RegisterPage(DriverFactory.getDriver());

	@When("user clicks on {string} button")
	public void user_clicks_on_login_button(String buttonName) throws InterruptedException {

		switch (buttonName) {
			case "Login":
				loginPage.clickOnLogin();
				break;
			case "Register":
				loginPage.clickOnNewRegistration();
			case "register user":
				registerPage.clickOnRegister();
			default:

		}
	}

	@Given("user is on login page")
	public void user_is_on_login_page() {
		WebDriver chromeDriver;
		System.setProperty("webdriver.chrome.driver","usr/bin/google-chrome");
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("headless");
		chromeDriver=new ChromeDriver(chromeOptions);
		chromeDriver.get("http://3.130.246.234/login");

	}

	public boolean isLoginSuccessful(String title) {
		return title.contains("Patients Report");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		title = loginPage.getPageTitle();
	}



	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) throws InterruptedException, IOException {
		// Write code here that turns the phrase above into concrete actions
		//	Assert.assertTrue(title.contains(expectedTitleName));
		if(title.contains(expectedTitleName))
		{
			Thread.sleep(2000);
			//	System.out.println("Expected Title is "+expectedTitleName +" Actual title is " +title );
			ExecutionHelper.getLogger().log(LogStatus.PASS, "title contains" + expectedTitleName +ExecutionHelper.getLogger()
					.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));
		} else {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "title" + expectedTitleName + "not found" +ExecutionHelper.getLogger()
					.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

		}



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

	@When("user is able to see {string}")
	public void userIsAbleToSee(String fieldName) throws IOException {
		switch (fieldName) {
			case "First Name":
			case "Last Name":
			case "Email":
			case "Password":
				String fieldTitle = registerPage.verifyTitle(fieldName);
				if(fieldTitle.contains(fieldName))
				{
					ExecutionHelper.getLogger().log(LogStatus.PASS, "Field Name is validated" + fieldName +ExecutionHelper.getLogger()
							.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));
				}
				else {
					ExecutionHelper.getLogger().log(LogStatus.FAIL, "Field Validation failed" +ExecutionHelper.getLogger()
							.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

				}

				break;
			case "Register":
				loginPage.clickOnNewRegistration();
			default:

		}

	}

	@And("I get the Registration details for {string}")
	public void iGetTheRegistrationDetailsFor(String testdataToFind) throws IOException, InvalidFormatException {


		ExcelReader reader = new ExcelReader();
		String systemDir = System.getProperty("user.dir");
		String filePath = systemDir + "/src/test/resources/registration.xlsx"; // Path with system directory


		List<Map<String, String>> testData = reader.getData(filePath, "registrationData");

		// Search for the index of the scenario in the testData
		int index = -1;
		for (int i = 0; i < testData.size(); i++) {
			if (testData.get(i).get("test data").equals(testdataToFind)) {
				index = i;
				break;
			}
		}



		if (index != -1) {
			firstName = testData.get(index).get("First Name");
			lastName = testData.get(index).get("Last Name");
			email = testData.get(index).get("Email");
			passwordValue = testData.get(index).get("Password");
			testdata = testdataToFind;

		} else {
			String errorMessage = "Test data '" + testdataToFind + "' not found in Excel data.";
			String screenshotPath = ExecutionHelper.takeScreenshot(DriverFactory.getDriver());

			// Log the error message and attach the screenshot
			ExecutionHelper.getLogger().log(LogStatus.FAIL, errorMessage + ExecutionHelper.getLogger().addScreenCapture(screenshotPath));
		}

		String newEmail = generateNewEmail(email, randomNumber);
		email = newEmail;

		ExecutionHelper.getLogger().log(LogStatus.PASS, "User retrieves Registration details"  +ExecutionHelper.getLogger()
				.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));


	}

	// Function to generate a new email address based on the base email and the random number
	private String generateNewEmail(String baseEmail, String randomNumber) {
		return baseEmail.replace("@", randomNumber + "@");
	}


	@When("I enter {string}")
	public void iEnterWith(String fieldName) {
		switch(fieldName) {
			case "FirstName":
				registerPage.enterfirstName(firstName);
				break;
			case "LastName":
				registerPage.enterLastName(lastName);
				break;
			case "Email":
				registerPage.enterEmail(email);
				break;
			case "Password":
				registerPage.enterPassword(passwordValue);
				break;
			default:
		}
	}

	@And("validate user is registered")
	public void validateUserIsRegistered() throws IOException {

		boolean isDisplayed = registerPage.validateSuccessMessage();
		if( isDisplayed = true)
		{
			ExecutionHelper.getLogger().log(LogStatus.PASS, "Registration Success" + ExecutionHelper.getLogger()
					.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

		}
		else
		{
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Registration unsuccessful" + ExecutionHelper.getLogger()
					.addScreenCapture(ExecutionHelper.takeScreenshot(DriverFactory.getDriver())));

		}


	}

}
