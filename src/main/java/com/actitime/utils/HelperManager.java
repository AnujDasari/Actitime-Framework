package com.actitime.utils;

import io.appium.java_client.AppiumDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is Helper class with all generic methods
 **/
public class HelperManager {
	/**
	 * This method makes the driver wait for specified seconds
	 **/
	public static void normalWait(WebDriver driver, long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method makes the driver wait implicitly
	 **/
	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	/**
	 * This method makes the driver wait till the webelement is located
	 **/
	public static void explicitWait(WebElement wb, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(wb));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method makes the driver scroll to the specified webelement in
	 * browser
	 **/
	public static boolean scrollTo(WebElement wb, WebDriver driver) {
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", wb);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb.isDisplayed();
	}

	/**
	 * This method makes the driver scroll down the specified webelement in an
	 * android app
	 **/
	public static void scrollDown(WebDriver driver) {
		try {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.1;
			int scrollEnd = screenHeightEnd.intValue();
			((AppiumDriver<?>) driver)
					.swipe(0, scrollStart, 0, scrollEnd, 2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method handles alert windows
	 **/
	public static void handleAlert(String status, WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		if (status.equalsIgnoreCase("Y")) {
			alert.accept();
		} else if (status.equalsIgnoreCase("N")) {
			alert.dismiss();
		}
	}

}
