package com.actitime.tests.base;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.actitime.driver.Driver;
import com.actitime.driver.DriverFactory;
import com.actitime.reports.ExtentReport;
import com.actitime.web_pages.WebDashboard;
import com.actitime.web_pages.WebLogin;
import com.actitime.web_pages.WebUsers;
import com.actitime.app_pages.*;
import com.actitime.device_pages.*;

/*
 * BaseTest 
 */
public class BaseTest extends DriverFactory {
	/* Before Class */
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void beforeClass(String browser) throws Exception {
		extent = ExtentReport.getExtent();
		if (Driver.getRunOn().equalsIgnoreCase("grid")) {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver = invokeBrowserInGrid(browser);
				webLoginPO = PageFactory.initElements(driver, WebLogin.class);
				webDashBoardPO = PageFactory.initElements(driver,
						WebDashboard.class);
				webUsersPO = PageFactory.initElements(driver, WebUsers.class);
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				appiumStart();
				driver = setupInGrid(browser);
				deviceLoginPO = PageFactory.initElements(driver,DeviceLogin.class);
				deviceDashBoardPO = PageFactory.initElements(driver,DeviceDashboard.class);

			} else if (Driver.getType().equalsIgnoreCase("App")) {
				appiumStart();
				driver = setupApp(browser);
				appCreateNewFormPO = PageFactory.initElements(driver,
						CreateForm.class);
			}
		}

		else if (Driver.getRunOn().equalsIgnoreCase("StandAlone")) {
			if (Driver.getType().equalsIgnoreCase("Desktop")) {
				driver = invokeBrowser(browser);
				webLoginPO = PageFactory.initElements(driver, WebLogin.class);
				webDashBoardPO = PageFactory.initElements(driver, WebDashboard.class);
				webUsersPO = PageFactory.initElements(driver, WebUsers.class);
			} else if (Driver.getType().equalsIgnoreCase("Device")) {
				appiumStart();
				driver = setup(browser);
				deviceLoginPO = PageFactory.initElements(driver, DeviceLogin.class);
				deviceDashBoardPO = PageFactory.initElements(driver, DeviceDashboard.class);
			} else if (Driver.getType().equalsIgnoreCase("App")) {
				appiumStart();
				setupApp(browser);
				appCreateNewFormPO = PageFactory.initElements(driver,
						CreateForm.class);
			}
		}
	}

	/* After Class */
	@AfterClass
	public void afterClass() throws IOException, InterruptedException {
		extent.flush();
		if (Driver.getType().equalsIgnoreCase("Device")) {
			appiumStop();
		} else if (Driver.getType().equalsIgnoreCase("Desktop")) {
			closeBrowser();
		}
	}
}
