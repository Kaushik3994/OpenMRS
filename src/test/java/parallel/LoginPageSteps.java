package parallel;

import io.cucumber.java.en.And;
import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

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

	@When("user selects location session")
	public void user_selects_location_session() {
		loginPage.clickOnSession();
	}


	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnLogin();
	}

	@Given("user is on login page")
	public void user_is_on_login_page() {
	DriverFactory.getDriver()
				.get("http://localhost:8081/openmrs-standalone/login.htm");


	}




	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		// Write code here that turns the phrase above into concrete actions
		title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);


	}
	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(title.contains(expectedTitleName));


	}

	@Then("the user should be able to view {string}")
	public void the_user_should_be_able_to_view(String sectionString) {

		System.out.println("Expected accounts section list: " + sectionString);
//		List<String> actualHomeSectionsList = new ArrayList<>();

		List<String> actualAccountSectionsList = loginPage.getAppsSectionsList();
//		for (String str : actualAccountSectionsList) {
//			String stringWithoutNewlines = str.replaceAll("\n", ",");
//			stringWithoutNewlines = stringWithoutNewlines.trim();
//			actualHomeSectionsList.add(stringWithoutNewlines);
//		}
		System.out.println("Actual accounts section list: " + actualAccountSectionsList);

//		Assert.assertTrue(expHomeSectionsList.contains(actualHomeSectionsList));

		Assert.assertTrue(loginPage.stringFound(sectionString,actualAccountSectionsList));
//			expHomeSectionsList.add(expectedHomeSectionsList);
		}



}






