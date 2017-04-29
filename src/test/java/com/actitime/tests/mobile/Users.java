package com.actitime.tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Navigate to Users Tab Test Script
 */
public class Users extends BaseTest {
	@Test
	public void navigateToTasks() {
		test = extent.createTest("NavigateToUsers","Verify Users Tab");
		FileUtilityManager.retrieveData("Users");
		deviceLoginPO.login();
		String navigateUser = deviceDashBoardPO.navigateToUsers();
		ReportNGReport.captureScreenshot(driver, "actiTIME - User List ");
		Assert.assertEquals(navigateUser, "actiTIME - User List");
		if (navigateUser.equals("actiTIME - User List")) {
			test.pass("NavigateToUsers");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "actiTIME - User List");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		deviceDashBoardPO.logout();
	}
}