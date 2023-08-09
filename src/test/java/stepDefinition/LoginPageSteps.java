package stepDefinition;

import com.qa.util.ExtentLogs;
import io.cucumber.java.en.And;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ExcelDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPageSteps {

	private static String title;
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
	public void user_selects_location_session(String session) {
		switch (session) {

			case "Inpatient Ward":
				loginPage.clickOnSession();
				ExtentLogs.log("User enters into session " + session);
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

		ExtentLogs.log("User is on Login Page");
	}


	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		// Write code here that turns the phrase above into concrete actions
		title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);

		ExtentLogs.log("Page title is: " + title);


	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(title.contains(expectedTitleName));
		Thread.sleep(2000);
		System.out.println("Expected Title is "+expectedTitleName +" Actual title is " +title );
		ExtentLogs.log("title contains" + expectedTitleName);

	}

	@Then("the user should be able to view {string}")
	public void the_user_should_be_able_to_view(String sectionString) {

		System.out.println("Expected accounts section list: " + sectionString);

		List<String> actualAccountSectionsList = loginPage.getAppsSectionsList();
		System.out.println("Actual accounts section list: " + actualAccountSectionsList);

		Assert.assertTrue(loginPage.stringFound(sectionString, actualAccountSectionsList));
		ExtentLogs.log("The page contains the tile "+sectionString );
	}


	@And("user clicks the app {string}")
	public void userClicksTheApp(String appName) throws InterruptedException {
		switch (appName) {
			case "Find Patient Record":
				loginPage.clickOnApp(appName);
				Thread.sleep(2000);
				ExtentLogs.log("User clicks on tile "+appName );
		}
	}



	private Map<String, String> getLoginData(String usernameKey, String passwordKey) throws IOException {
		List<Map<String, String>> loginCredentials;
		String filePath;
		filePath = "\"C:\\Users\\mbkaushikkumar\\Desktop\\CredentialsDataProviders.xlsx\"";
		loginCredentials = ExcelDataReader.readExcelData("\"C:\\Users\\mbkaushikkumar\\IdeaProjects\\OpenMRS\\CredentialsDataProviders.xlsx\"", "Sheet1");

		for (Map<String, String> loginData : loginCredentials) {
			if (loginData.containsKey(usernameKey) && loginData.containsKey(passwordKey)) {
				return loginData;
			}
		}
		throw new IllegalArgumentException("Login data not found for given keys: " + usernameKey + ", " + passwordKey);
	}

	private List<Map<String, String>> testData;



	@When("I enter {string} and {string}")
	public void iEnterAnd(String usernameKey, String passwordKey)throws IOException  {
//			System.out.println("Into the Function");
			Map<String, String> loginData = getLoginData(usernameKey, passwordKey);
			String username = loginData.get(usernameKey);
			String password = loginData.get(passwordKey);

			loginPage.enterUserName(username);
			loginPage.enterPassword(password);
			ExtentLogs.log("User enters userName" + username + "and Password" +password);
		}


}








