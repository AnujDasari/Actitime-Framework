package com.actitime.web_pages;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.actitime.pages.BasePage;
import com.actitime.utils.FileUtilityManager;
import com.actitime.utils.HelperManager;

/**
 * This is ActiTime Users Page Object
 **/
public class WebUsers extends BasePage {

	@FindBy(xpath = "//div[@class='pagetitle']/span")
	private WebElement usersTitle;

	@FindBy(css = "div.buttonText")
	private WebElement addUserBtn;

	@FindBy(css = "input#userDataLightBox_firstNameField")
	private WebElement firstNameTextField;

	@FindBy(css = "input#userDataLightBox_lastNameField")
	private WebElement lastNameTextField;

	@FindBy(css = "input#userDataLightBox_emailField")
	private WebElement emailTextField;

	@FindBy(css = "input#userDataLightBox_usernameField")
	private WebElement userNameTextField;

	@FindBy(css = "input#userDataLightBox_passwordField")
	private WebElement passwordTextField;

	@FindBy(css = "input#userDataLightBox_passwordCopyField")
	private WebElement retypePasswordTextField;

	@FindBy(css = "div#userDataLightBox_commitBtn")
	private WebElement confirmUserAddBtn;

	@FindBy(css = "span.userNameSpan")
	private WebElement confirmUserAdd;

	@FindBy(xpath = "//div[@class='name']/span[@class='userNameSpan']")
	private List<WebElement> addedUsersList;

	@FindBy(xpath = "//div[@class='userFieldError usernameSecondLvl']/div[contains(text(),'this')]")
	private WebElement duplicateUserError;

	@FindBy(xpath = "//button[contains(text(),'Delete User')]")
	private WebElement deleteUserBtn;

	@FindBy(css = "img.closeButton")
	private WebElement modalWindowClose;

	@FindBy(xpath = "//a[@class='page' and contains(text(),'1')]")
	private WebElement firstPageUserList;

	@FindBy(css = "a.next")
	private WebElement nextBtn;

	public WebUsers(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * This method is used to create a user
	 **/
	public String createUser() {
		HelperManager.explicitWait(addUserBtn, driver);
		addUserBtn.click();
		firstNameTextField.sendKeys(FileUtilityManager.getTestData().get(
				"First_Name"));
		lastNameTextField.sendKeys(FileUtilityManager.getTestData().get(
				"Last_Name"));
		emailTextField.sendKeys(FileUtilityManager.getTestData().get(
				"Email_Address"));
		userNameTextField.sendKeys(FileUtilityManager.getTestData().get(
				"UserName"));
		passwordTextField.sendKeys(FileUtilityManager.getTestData().get(
				"Password"));
		retypePasswordTextField.sendKeys(FileUtilityManager.getTestData().get(
				"Password"));
		HelperManager.explicitWait(modalWindowClose, driver);
		if (duplicateUserError.isDisplayed()) {
			modalWindowClose.click();
			HelperManager.normalWait(driver, 5);
			HelperManager.handleAlert("Y", driver);
			return "User having username: "
					+ FileUtilityManager.getTestData().get("UserName")
					+ " already exists!";

		} else {
			HelperManager.scrollTo(confirmUserAddBtn, driver);
			confirmUserAddBtn.click();
			HelperManager.normalWait(driver, 2);
			HelperManager.scrollTo(confirmUserAdd, driver);
			String actualName = confirmUserAdd.getText();
			HelperManager.scrollTo(usersTitle, driver);
			return actualName;
		}
	}

	/**
	 * This method is used to check for an existing user
	 **/
	public boolean checkExistingUser(String checkUser) {
		driver.navigate().refresh();
		int count = 0;
		String availableUsers;
		while (count < 10) {
			HelperManager.scrollTo(usersTitle, driver);
			for (int i = 0; i < addedUsersList.size(); i++) {
				availableUsers = addedUsersList.get(i).getText();
				if (checkUser.equalsIgnoreCase(availableUsers)) {
					WebElement we = addedUsersList.get(i);
					HelperManager.scrollTo(we, driver);
					return true;
				} else if (!(checkUser.equalsIgnoreCase(availableUsers))
						&& count < 10) {
					count++;
					continue;
				}
			}

			/* If all items in one page are checked, click on next page */
			if (count == 10) {
				count = 0;
				try {
					HelperManager.scrollTo(nextBtn, driver);
					nextBtn.click();
					continue;
				} catch (NoSuchElementException e) {
					return false;
				}

			}

			/* Verify for an user on all pages */
			else if (count < 10) {
				try {
					if (nextBtn.isEnabled()) {
						break;
					}
				} catch (Exception e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (checkUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(checkUser.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							return false;
						}
					}
					break;

				}

			}
		}
		return false;

	}

	/**
	 * This method is used to delete an existing user
	 **/
	public boolean deleteUser(String deleteUser) {
		driver.navigate().refresh();
		int count = 0;
		String availableUsers;
		while (count < 10) {
			HelperManager.scrollTo(usersTitle, driver);
			for (int i = 0; i < addedUsersList.size(); i++) {
				availableUsers = addedUsersList.get(i).getText();
				if (deleteUser.equalsIgnoreCase(availableUsers)) {
					WebElement we = addedUsersList.get(i);
					HelperManager.scrollTo(we, driver);
					we.click();
					HelperManager.normalWait(driver, 2);
					if (!deleteUserBtn.isEnabled()) {
						modalWindowClose.click();
						return false;
					}

					else {
						deleteUserBtn.click();
						HelperManager.normalWait(driver, 2);
						HelperManager.handleAlert("Y", driver);
						return true;
					}
				} else if (!(deleteUser.equalsIgnoreCase(availableUsers))
						&& count < 10) {
					count++;
					continue;
				}
			}

			/* If all items in one page are checked, click on next page */
			if (count == 10) {
				count = 0;
				try {
					HelperManager.scrollTo(nextBtn, driver);
					nextBtn.click();
					continue;
				} catch (NoSuchElementException e) {
					return false;
				}

			}

			/* Verify for an user on all pages */
			else if (count < 10) {
				try {
					if (nextBtn.isEnabled()) {
						break;
					}
				} catch (Exception e) {
					for (int i = 0; i < addedUsersList.size(); i++) {
						availableUsers = addedUsersList.get(i).getText();
						if (deleteUser.equalsIgnoreCase(availableUsers)) {
							break;
						} else if (!(deleteUser
								.equalsIgnoreCase(availableUsers))
								&& count < addedUsersList.size()) {
							continue;
						} else {
							return false;
						}
					}
					break;

				}

			}
		}
		return false;
	}

}

