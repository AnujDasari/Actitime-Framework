package com.actitime.tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Navigate to Tasks Tab Test Script
 */
public class Tasks extends BaseTest {
	@Test
	public void navigateToTasks() {
		test = extent.createTest("NavigateToTasks","Verify Tasks Tab");
		FileUtilityManager.retrieveData("Tasks");
		deviceLoginPO.login();
		String navigateTitle = deviceDashBoardPO.navigateToTasks();
		ReportNGReport.captureScreenshot(driver, "actiTIME - Open Tasks ");
		Assert.assertEquals(navigateTitle, "actiTIME - Open Tasks");
		if (navigateTitle.equals("actiTIME - Open Tasks")) {
			test.pass("NavigateToTasks");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "actiTIME - Open Tasks");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		deviceDashBoardPO.logout();
	}
}