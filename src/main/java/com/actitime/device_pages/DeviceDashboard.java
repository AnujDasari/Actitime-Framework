package com.actitime.device_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.actitime.pages.BasePage;

/**
 * ActiTime Dashboard Page Object
 **/

public class DeviceDashboard extends BasePage{
	@FindBy(css = "a.content.tasks")
	private WebElement tasksWidget;

	@FindBy(css = "a.content.reports")
	private WebElement reportWidget;

	@FindBy(css = "a.content.users")
	private WebElement usersWidget;

	@FindBy(xpath = "//div[@class='overlayTipWrapper']/span")
	private WebElement tasksTitle;

	@FindBy(xpath = "//div[@class='overlayTipWrapper' and contains(text(),'Reports')]")
	private WebElement reportsTitle;

	@FindBy(xpath = "//div[@class='pagetitle']/span")
	private WebElement usersTitle;

	@FindBy(css = "a.logout")
	private WebElement logOutBtn;

	public DeviceDashboard(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method Navigates driver to Tasks
	 **/
	public String navigateToTasks() {
		tasksWidget.click();
		return driver.getTitle();
	}

	/**
	 * This method Navigates driver to Reports
	 **/
	public String navigateToReports() {
		reportWidget.click();
		return driver.getTitle();
	}

	/**
	 * This method Navigates driver to Users
	 **/
	public String navigateToUsers() {
		usersWidget.click();
		return driver.getTitle();
	}

	/**
	 * This method makes driver logout of application
	 **/
	public String logout() {
		logOutBtn.click();
		return driver.getTitle();
	}

}

