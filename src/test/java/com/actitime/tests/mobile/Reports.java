package com.actitime.tests.mobile;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Navigate to Reports Tab Test Script
 */
public class Reports extends BaseTest {
	@Test
	public void navigateToTasks() {
		test = extent.createTest("NavigateToReports","Verify Reports Tab");
		FileUtilityManager.retrieveData("Reports");
		deviceLoginPO.login();
		String navigateReport = deviceDashBoardPO.navigateToReports();
		ReportNGReport.captureScreenshot(driver, "actiTIME - Reports Dashboard ");
		Assert.assertEquals(navigateReport, "actiTIME - Reports Dashboard");
		if (navigateReport.equals("actiTIME - Reports Dashboard")) {
			test.pass("NavigateToReports");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "actiTIME - Reports Dashboard");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}
		deviceDashBoardPO.logout();
	}
}