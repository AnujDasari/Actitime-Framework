package com.actitime.app_pages;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.actitime.pages.BasePage;
import com.actitime.utils.FileUtilityManager;
import com.actitime.utils.HelperManager;

/*
 * CreateForm PageObject
 */

public class CreateForm extends BasePage {
	@FindBy(id = "edt_firstname")
	private WebElement firstNameTextBox;

	@FindBy(id = "edt_lastname")
	private WebElement lastNameTextBox;

	@FindBy(id = "edt_email")
	private WebElement emailTextBox;

	@FindBy(id = "edt_phone")
	private WebElement phoneNumberTextBox;

	@FindBy(id = "radio_gender_male")
	private WebElement maleGenderRadioBtn;

	@FindBy(id = "radio_gender_female")
	private WebElement femaleGenderRadioBtn;

	@FindBy(id = "edt_street")
	private WebElement streetTextBox;

	@FindBy(id = "spinner_countries_main")
	private WebElement countryDropDown;

	@FindBy(id = "text1")
	private List<WebElement> countryList;

	@FindBy(id = "text1")
	private List<WebElement> stateList;

	@FindBy(id = "spinner_states_main")
	private WebElement stateDropDown;

	@FindBy(id = "chkbox_tv")
	private WebElement watchTvCheckBox;

	@FindBy(id = "chkbox_games")
	private WebElement playVideoGamesCheckBox;

	@FindBy(id = "chkbox_books")
	private WebElement readBooksCheckBox;

	@FindBy(id = "chkbox_stamps")
	private WebElement collectStampsCheckBox;

	@FindBy(id = "chkbox_termsandconditions")
	private WebElement termsAndConditionsCheckBox;

	@FindBy(id = "btn_save")
	private WebElement saveFormBtn;

	public CreateForm(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * This methods creates a new form
	 **/
	public boolean createNewForm() {
		try {

			HelperManager.explicitWait(firstNameTextBox, driver);
			firstNameTextBox.sendKeys(FileUtilityManager.getTestData().get(
					"First_Name"));
			lastNameTextBox.sendKeys(FileUtilityManager.getTestData().get(
					"Last_Name"));
			emailTextBox.sendKeys(FileUtilityManager.getTestData().get(
					"Email_Address"));
			phoneNumberTextBox.sendKeys(FileUtilityManager.getTestData().get(
					"Phone"));

			String gender = FileUtilityManager.getTestData().get("Gender");
			HelperManager.scrollDown(driver);
			if (gender.equalsIgnoreCase("male")) {
				maleGenderRadioBtn.click();
			} else {
				femaleGenderRadioBtn.click();
			}
			streetTextBox.sendKeys(FileUtilityManager.getTestData().get(
					"Street"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			countryDropDown.click();
			int countriesCount = 0;
			// Iterator<WebElement> it = countryList.iterator();
			while (countriesCount < 11) {
				for (int i = 0; i < countryList.size(); i++) {
					String country = countryList.get(i).getText();
					if (country.equalsIgnoreCase(FileUtilityManager
							.getTestData().get("Country"))) {
						countriesCount = 12;
						countryList.get(i).click();
						break;
					} else if (!(country.equalsIgnoreCase(FileUtilityManager
							.getTestData().get("Country")))) {
						countriesCount++;
						if (countriesCount == 11) {
							countriesCount = 0;
							HelperManager.scrollDown(driver);
							continue;
						}
					}
				}
			}
		} catch (NoSuchElementException e) {
			return false;
		}

		try {
			stateDropDown.click();
			int statesCount = 0;

			while (statesCount < 11) {
				for (int i = 0; i < stateList.size(); i++) {
					String state = stateList.get(i).getText();
					if (state.equalsIgnoreCase(FileUtilityManager.getTestData()
							.get("State"))) {
						statesCount = 12;
						stateList.get(i).click();
						break;
					} else if (!(state.equalsIgnoreCase(FileUtilityManager
							.getTestData().get("State")))) {
						statesCount++;
						if (statesCount == 11) {
							statesCount = 0;
							HelperManager.scrollDown(driver);
							continue;
						}
					}
				}
			}
		} catch (NoSuchElementException e) {
			return false;
		}

		HelperManager.scrollDown(driver);
		HelperManager.scrollDown(driver);
		String hobbies = FileUtilityManager.getTestData().get("Hobbies");
		hobbies.replaceAll("\\s+", "");
		List<String> hobbiesList = Arrays.asList(hobbies.split(","));
		for (int i = 0; i < hobbiesList.size(); i++) {
			String s = hobbiesList.get(i);
			System.out.println(s);
			if (hobbiesList.get(i).equalsIgnoreCase("watch tv")) {
				watchTvCheckBox.click();
			} else if (hobbiesList.get(i).equalsIgnoreCase(" read books")) {
				readBooksCheckBox.click();
			} else if (hobbiesList.get(i).equalsIgnoreCase("video games")) {
				playVideoGamesCheckBox.click();
			} else if (hobbiesList.get(i).equalsIgnoreCase("collect stamps")) {
				collectStampsCheckBox.click();
			}

		}
		HelperManager.scrollDown(driver);
		termsAndConditionsCheckBox.click();
		saveFormBtn.click();
		return true;
	}
}
