package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	private static WebDriver driver;
	// 1. By Locators: OR
//	private By registerLink = By.xpath("/html/body/div/div/div/div/div[2]/form/div[3]/span/a");
	private static By firstNameTitle = By.xpath("/html/body/div/div/div/div[2]/form/div[1]/label");
	private static By firstName = By.id("firstName");

	private static By LastNameTitle = By.xpath("/html/body/div/div/div/div[2]/form/div[2]/label");
	private By LastName = By.id("lastName");

	private static By emailTitle = By.xpath("/html/body/div/div/div/div[2]/form/div[3]/label");
	private By emailId = By.id("email");

	private static By passwordTitle = By.xpath("/html/body/div/div/div/div[2]/form/div[4]/label");
	private By password = By.id("password");

//	private By registerButton = By.xpath("//button[@type='submit']");
//	private By registrationSuccessMessage = By.xpath("//div[@class='alert alert-info']");


	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public static String verifyTitle(String fieldName) {
		String fieldTitle = "";
		switch (fieldName) {
			case "First Name":
				fieldTitle = driver.findElement(firstNameTitle).getText();
				break;
			case "Last Name":
				fieldTitle = driver.findElement(LastNameTitle).getText();
				break;
			case "Email":
				fieldTitle = driver.findElement(emailTitle).getText();
				break;
			case "Password":
				fieldTitle = driver.findElement(passwordTitle).getText();
				break;
			default:
				break;
		}
		return fieldTitle;
	}


	public void enterfirstName(String firstname) {
		driver.findElement(firstName).sendKeys(firstname);
	}

	public void enterLastName(String lastname) {
		driver.findElement(LastName).sendKeys(lastname);
	}

	public void enterEmail(String email) {
		driver.findElement(emailId).sendKeys(email);
	}

	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	public void clickOnRegister() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//div//button[@type='submit']"));
		((JavascriptExecutor) driver).executeScript("javascript:arguments[0].click();", element);
		Thread.sleep(20000);
	}

	public boolean validateSuccessMessage()
	{
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-info']"));
		return element.isDisplayed();
	}
}