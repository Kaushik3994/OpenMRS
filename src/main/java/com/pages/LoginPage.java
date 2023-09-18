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
	// 1. By Locators: OR
	private By emailId = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By signInButton = By.xpath("//button[@type='submit']");
	private By sessionButtonInpatientWard =By.xpath("//li[@id='Inpatient Ward']");
	private By sessionButtonFindPatientRecords =By.xpath("/html/body/div/div[3]/div[3]/div/a[1]");

	private By newRegistration = By.xpath("/html/body/div/div/div/div/div[2]/form/div[3]/span/a");
	// 2. Constructor of the page class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. page actions: features(behavior) of the page the form of methods:

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void enterUserName(String username) {
		driver.findElement(emailId).sendKeys(username);
	}

	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	public void clickOnNewRegistration() {
		driver.findElement(newRegistration).click();
	}

	public void clickOnLogin() {
			driver.findElement(signInButton).click();
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

