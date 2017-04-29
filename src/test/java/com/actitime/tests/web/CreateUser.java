package com.actitime.tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Create User Test Script
 */
public class CreateUser extends BaseTest {
	@Test(groups = { "Regression" })
	public void createUser() {
		test = extent.createTest("CreateAUser", "Verify User is Created");
		FileUtilityManager.retrieveData("CreateAUser");
		String expectedName = FileUtilityManager.getTestData().get("Last_Name")
				+ ", " + FileUtilityManager.getTestData().get("First_Name");
		webLoginPO.login();
		webDashBoardPO.navigateToUsers();
		String createUser = webUsersPO.createUser();
		if (createUser.contains("already exists")) {
			test.log(Status.FAIL, "CreateAUser");
			test.log(Status.INFO, createUser);
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			Assert.fail(createUser);
		} else {
			ReportNGReport.captureScreenshot(driver, "UserAddition ");
			Assert.assertEquals(expectedName, createUser);
			test.pass("CreateAUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		webDashBoardPO.logout();
	}
}
