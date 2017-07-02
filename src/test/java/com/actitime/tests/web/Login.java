package com.actitime.tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;

/*
 * Login Test Script
 */
public class Login extends BaseTest {
	@Test
	public void signIntoApplication() {
		test = extent.createTest("Login", "Verify Successful Login");
		FileUtilityManager.retrieveData("Login");
		Object loginTitle = webLoginPO.login();
		String expectedTitle = "actiTIME - Enter Time-Track";
	
		//generate ReportNG report
		Assert.assertEquals(loginTitle, expectedTitle);
		ReportNGReport.captureScreenshot(driver, "Login ");
		
		//generate Extent report
		ExtentReport.assertTest(test, "Login", loginTitle, expectedTitle);
		ExtentReport.captureAndDisplayScreenShot(driver, test);
		
		webDashBoardPO.logout();
	}
}
