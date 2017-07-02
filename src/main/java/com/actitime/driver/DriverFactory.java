package com.actitime.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.actitime.app_pages.CreateForm;
import com.actitime.device_pages.DeviceDashboard;
import com.actitime.device_pages.DeviceLogin;
import com.actitime.web_pages.WebDashboard;
import com.actitime.web_pages.WebLogin;
import com.actitime.web_pages.WebUsers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * DriverFactory class to create WebDriver instance to run automated tests against desktop browsers, device browser and android app.
 **/
public class DriverFactory {
	protected WebDriver driver;
	protected WebLogin webLoginPO;
	protected WebDashboard webDashBoardPO;
	protected WebUsers webUsersPO;
	protected DeviceLogin deviceLoginPO;
	protected DeviceDashboard deviceDashBoardPO;
	protected CreateForm appCreateNewFormPO;
	protected ExtentReports extent;
	protected ExtentTest test;

	/* Starting Appium from Console */
	AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
			usingDriverExecutable(new File("C:/Program Files (x86)/Appium/node.exe"))
					.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js")));

	
	/* For Device Only - START APPIUM SERVER */
	protected void appiumStart() {
		if (service.isRunning() == true) {
			service.stop();
			service.start();
		} else {
			service.start();
		}
	}

	/* For Device Only - STOP APPIUM SERVER */
	protected void appiumStop() throws IOException {
		service.stop();
	}

	/* Properties to trigger Chrome browser*/
	protected void chromeDriver() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/* Properties to trigger Firefox browser*/
	protected void firefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	/* Properties to trigger IE browser*/
	protected void ieDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver","./src/main/resources/iedriver/IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().window().maximize();
	}

	/**
	 * This method invokes a standalone browser
	 * 
	 **/
	protected WebDriver invokeBrowser(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("firefox")) {
			firefoxDriver();
			return driver;
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			chromeDriver();
			return driver;
		}

		else if (browser.equalsIgnoreCase("internetexplorer")) {
			ieDriver();
			return driver;
		}
		return driver;
	}

	/**
	 * This method invokes a grid browser
	 * 
	 **/
	protected static WebDriver invokeBrowserInGrid(String browser) throws MalformedURLException {
		String nodeURL = null;
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("firefox")) {
			nodeURL = Driver.getNodeUrl1();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("chrome")) {
			nodeURL = Driver.getNodeUrl2();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else if (browser.equalsIgnoreCase("internetexplorer")) {
			nodeURL = Driver.getNodeUrl3();
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}

		else {
			nodeURL = Driver.getNodeUrl2();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
			driver.manage().window().maximize();
			return driver;
		}
	}

	/**
	 * This method closes the browser
	 **/
	protected void closeBrowser() throws IOException {
		driver.quit();
	}
	
	/**
	 * This method runs scripts in a device using grid
	 * @throws MalformedURLException 
	 **/
	protected WebDriver setupInGrid(String browser) throws MalformedURLException {
		String nodeURL = null;
		WebDriver driver = null;
		nodeURL = Driver.getDeviceNodeUrl();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus");
		driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
		return driver;
	}

	/**
	 * This method runs scripts in a device browser
	 **/
	@SuppressWarnings("rawtypes")
	protected WebDriver setup(String browser) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("automationName", Driver.getAutomationName());
		capabilities.setCapability("deviceName", Driver.getDeviceName());
		capabilities.setCapability("platformName", Driver.getPlatformName());
		capabilities.setCapability("platformVersion", Driver.getPlatformVersion());
		capabilities.setCapability("app", Driver.getApp());
		capabilities.setCapability("device", Driver.getDevice());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		return driver;
	}

	/**
	 * This method runs scripts in a mobile application
	 **/
	@SuppressWarnings("rawtypes")
	protected WebDriver setupApp(String browser) throws MalformedURLException, InterruptedException {
		File app = new File("./src/test/resources/apk/FormApp.apk");
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability("appium-version", "1.4.16.1");
		capabilities.setCapability("automationName", Driver.getAutomationName());
		capabilities.setCapability("deviceName", Driver.getDeviceName());
		capabilities.setCapability("platformName", Driver.getPlatformName());
		capabilities.setCapability("platformVersion",Driver.getPlatformVersion());
		capabilities.setCapability("device", Driver.getDevice());
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("noReset", Driver.getNoReset());
		capabilities.setCapability("fullReset", Driver.getFullReset());
		capabilities.setCapability("appActivity", "com.anuj.task1.FormLogin");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		return driver;
	}
}
