package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginPage {

	private WebDriver driver;
	//public ExtentReports reports;
	//public ExtentTest test;

	// 1. By Locators: OR
	private By emailId = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By signInButton = By.id("loginButton");
	private By sessionButtonInpatientWard =By.xpath("//li[@id='Inpatient Ward']");
	private By sessionButtonFindPatientRecords =By.xpath("/html/body/div/div[3]/div[3]/div/a[1]");

	// 2. Constructor of the page class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. page actions: features(behavior) of the page the form of methods:

	public String getLoginPageTitle() {
		return driver.getTitle();
	}



	public void enterUserName(String username) {
		driver.findElement(emailId).sendKeys(username);
	}

	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);

	}

	public void clickOnLogin() {
			driver.findElement(signInButton).click();
	}
	public void clickOnSession() {
		driver.findElement(sessionButtonInpatientWard).click();
	}
	public void clickOnApp(String appName) {
		switch(appName) {
			case "Find Patient Record":
			driver.findElement(sessionButtonFindPatientRecords).click();
			break;
		}
	}

	private By accountSections = By.id("apps");

	public List<String> getAppsSectionsList() {

		List<String> appsList = new ArrayList<>();
		List<WebElement> appsHeaderList = driver.findElements(accountSections);

		for (WebElement e : appsHeaderList) {
			String text = e.getText();
			appsList.add(text);
		}

		String appList = appsList.toString();
		appList= appsList.toString().replace("[","");
		appList= appList.replace("]","");

		String[] substrings = appList.split("\n");

		// Convert the array to a list
		List<String> substringsList = Arrays.asList(substrings);
		System.out.println(substringsList);
		return substringsList;

	}

	public boolean stringFound(String sectionString, List actualAccountSectionsList) {
		boolean result=  false;
		for (Object str : actualAccountSectionsList) {
			boolean isStringInList = sectionString.contains(str.toString());


			// Output the result
			if (isStringInList) {
				System.out.println("The string '" + sectionString + "' is present in the list.");
				result=true;
			}
		}
		return result;
		}

	}

