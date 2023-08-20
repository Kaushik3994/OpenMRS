//package com.qa.util;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class DriverFactory {
//
//	public WebDriver driver;
//
//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//	/**
//	 * This method is used to initialize the thradlocal driver on the basis of given
//	 * browser
//	 *
//	 * @param browser
//	 * @return this will return tldriver.
//	 */
//	public WebDriver init_driver(String browser) {
//
//
//		System.out.println("browser value is: " + browser);
//
//		if (browser.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			tlDriver.set(new ChromeDriver());
//		} else if (browser.equals("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			tlDriver.set(new FirefoxDriver());
//		} else if (browser.equals("safari")) {
//			tlDriver.set(new SafariDriver());
//		} else {
//
//			System.out.println("Please pass the correct browser value: " + browser);
//		}
//
//
//		getDriver().manage().deleteAllCookies();
//		getDriver().manage().window().maximize();
//
//		// Set the driver instance in LocalDriverManager
//
//		return getDriver();
//
//	}
//
//	/**
//	 * this is used to get the driver with ThreadLocal
//	 *
//	 * @return
//	 */
//	public static synchronized WebDriver getDriver() {
//		return tlDriver.get();
//	}
//}

package com.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static WebDriver init_driver(String browser) {
		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		System.out.println("Setting Driver");
		tlDriver.set(driver);
	}

	// Add the rest of the methods from the LocalDriverManager class here
	// For example, if you have methods like 'closeDriver', 'quitDriver', etc.,
	// you can add them here as well.
}

