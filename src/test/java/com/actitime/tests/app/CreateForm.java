package com.actitime.tests.app;

import org.testng.annotations.Test;
import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * CreateNewForm Test Script
 */
public class CreateForm extends BaseTest {
	@Test
	public void CreateNewForm() throws InterruptedException {
		test = extent.createTest("CreateNewForm");
		FileUtilityManager.retrieveData("CreateNewForm");
		boolean createForm = appCreateNewFormPO.createNewForm();
		ReportNGReport.captureScreenshot(driver, "CreateNewForm ");
		if (createForm) {
			test.pass("CreateNewForm");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		} else {
			test.log(Status.FAIL, "CreateNewForm");
			test.log(Status.INFO, "Failed to create a new form");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
		}

	}
}
