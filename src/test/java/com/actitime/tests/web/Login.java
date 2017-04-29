package com.actitime.tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Login Test Script
 */
public class Login extends BaseTest {
	@Test(groups = { "Regression" })
	public void signIntoApplication() {
		test = extent.createTest("Login","Verify SignIntoApplication");
		FileUtilityManager.retrieveData("Login");
		String loginTitle = webLoginPO.login();
		ReportNGReport.captureScreenshot(driver, "SignIntoApplication ");
		Assert.assertEquals(loginTitle, "actiTIME - Enter Time-Track");
		if (loginTitle.equals("actiTIME - Enter Time-Track")) {
			test.pass("SignIntoApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "SignIntoApplication");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		webDashBoardPO.logout();
	}
}
