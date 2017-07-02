package com.actitime.web_pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actitime.driver.Driver;
import com.actitime.pages.BasePage;
import com.actitime.utils.FileUtilityManager;
import com.actitime.utils.HelperManager;

/**
 * ActiTime Login Page Object
 **/
public class WebLogin extends BasePage {
	@FindBy(css = "input#username")
	private WebElement username;

	@FindBy(css = "input[type='password']")
	private WebElement password;

	@FindBy(css = "input[type='checkbox']")
	private WebElement passwordChkBox;

	@FindBy(css = "input#loginButton")
	private WebElement logInBtn;
	
	public WebLogin(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to login to ActiTime Application
	 * 
	 * @throws IOException
	 **/
	public String login() {
		try {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver.get(Driver.getDesktopUrl());
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				driver.get(Driver.getDeviceUrl());
			}
			username.sendKeys(FileUtilityManager.getTestData().get("UserID"));
			password.sendKeys(FileUtilityManager.getTestData().get("Pwd"));
			password.sendKeys(Keys.TAB);
			password.sendKeys(Keys.TAB);
			password.sendKeys(Keys.ENTER);
			HelperManager.normalWait(driver, 5);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}

}

















