package com.actitime.tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.actitime.reports.ExtentReport;
import com.actitime.reports.ReportNGReport;
import com.actitime.tests.base.BaseTest;
import com.actitime.utils.FileUtilityManager;
import com.aventstack.extentreports.Status;

/*
 * Delete User Test Script
 */
public class DeleteUser extends BaseTest{
	@Test(groups = { "Regression" })
	public void deleteUser() {
		test = extent.createTest("DeleteUser", "Verify User is Deleted");
		FileUtilityManager.retrieveData("DeleteUser");
		String deleteUser = FileUtilityManager.getTestData().get("Full_Name");
		
		webLoginPO.login();
		webDashBoardPO.navigateToUsers();
		Boolean deleteUserStatus = webUsersPO.deleteUser(deleteUser);
		
		if (deleteUserStatus) {
			test.pass("DeleteUser");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
		} 
		
		else {
			test.log(Status.FAIL, "DeleteUser");
			test.log(Status.INFO, deleteUser + ":No Such User Found!");
			ExtentReport.captureAndDisplayScreenShot(driver, test);
			ReportNGReport.captureScreenshot(driver, "DeleteExistingUser");
			Assert.fail(deleteUser + " : No Such User Found!");
		}
		webDashBoardPO.logout();
	}
}
