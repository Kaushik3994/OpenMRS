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

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static WebDriver driver;

	public static void  setDriver() {
		//System.setProperty("webdriver.chrome.driver","/usr/bin/google-chrome");
		ChromeOptions chromeOptions=new ChromeOptions();
//		DesiredCapabilities cap=DesiredCapabilities.chrome();

// Set ACCEPT_SSL_CERTS  variable to true
//		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeOptions.addArguments("headless");
//		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver=WebDriverManager.chromedriver().capabilities(chromeOptions).create();
		//chromeDriver=new ChromeDriver(chromeOptions);
		//chromeDriver.manage().deleteAllCookies();
		//chromeDriver.manage().window().maximize();
	}

	public static WebDriver getDriver(){
		return driver;

	}
	public DriverFactory(WebDriver driver) {
		this.driver=driver;
	}

	/*public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}*/

}
