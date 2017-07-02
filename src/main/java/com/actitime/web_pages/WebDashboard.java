package com.actitime.web_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actitime.pages.BasePage;
import com.actitime.utils.HelperManager;

/**
 * ActiTime Dashboard Page Object
 **/

public class WebDashboard extends BasePage {
	@FindBy(css = "a.content.tasks")
	private WebElement tasksWidget;

	@FindBy(css = "a.content.reports")
	private WebElement reportWidget;

	@FindBy(css = "a.content.users")
	private WebElement usersWidget;

	@FindBy(xpath = "//span[contains(text(),'Open Tasks')]")
	private WebElement tasksTitle;

	@FindBy(xpath = "//td[@class='pagetitle']")
	private WebElement reportsTitle;

	@FindBy(xpath = "//span[contains(text(),'User List')]")
	private WebElement usersTitle;

	@FindBy(css = "a.logout")
	private WebElement logOutBtn;

	public WebDashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * This methods Navigates driver to Tasks
	 **/
	public String navigateToTasks() {
		tasksWidget.click();
		HelperManager.explicitWait(tasksTitle, driver);
		return driver.getTitle();
	}

	/**
	 * This methods Navigates driver to Reports
	 **/
	public String navigateToReports() {
		reportWidget.click();
		HelperManager.explicitWait(reportsTitle, driver);
		return driver.getTitle();
	}

	/**
	 * This methods Navigates driver to Users
	 **/
	public String navigateToUsers() {
		usersWidget.click();
		HelperManager.explicitWait(usersTitle, driver);
		return driver.getTitle();
	}

	/**
	 * This methods makes driver logout of application
	 **/
	public String logout() {
		logOutBtn.click();
		HelperManager.normalWait(driver, 2);
		return driver.getTitle();
	}
}

















