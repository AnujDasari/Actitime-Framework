package com.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/* 
 * BasePage with constructor to initialize driver 
 */

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
