package com.actitime.device_pages;

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
public class DeviceLogin extends BasePage{
	@FindBy(css = "input#username")
	private WebElement userName;

	@FindBy(css = "input[type='password']")
	private WebElement passWord;

	@FindBy(css = "input[type='checkbox']")
	private WebElement passWordChkBox;

	@FindBy(css = "input#loginButton")
	private WebElement logInBtn;

	public DeviceLogin(WebDriver driver) {
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
			HelperManager.implicitWait(driver);
			userName.sendKeys(FileUtilityManager.getTestData().get("UserID"));
			passWord.sendKeys(FileUtilityManager.getTestData().get("Pwd"));
			passWord.sendKeys(Keys.TAB);
			passWord.sendKeys(Keys.TAB);
			passWord.sendKeys(Keys.ENTER);
			HelperManager.normalWait(driver, 2);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}

}
