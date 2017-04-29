package com.actitime.reports;

import java.io.File;
import java.net.URL;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import com.actitime.driver.Driver;

/**
 * ReportNGReport class with reporting and screenshot functionality
 **/
public class ReportNGReport extends TestListenerAdapter {
	private static String dirPath = null;

	private static Date dtNow = new Date();

	private static String strStartDate = dtNow.toString().replace(":", "_");

	private static String strDateStamp = strStartDate.replace(" ", "_");

	/**
	 * This method creates the execution report
	 **/
	public static String makDir() {
		String s = Driver.getRelativePath() + "/Resources/ReportNGReports/"
				+ strDateStamp;
		File srcDir = new File(s);
		srcDir.mkdirs();
		return dirPath = srcDir.getAbsolutePath();
	}

	/**
	 * This method captures a screenshot
	 **/
	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(dirPath + "/ " + screenshotName
					+ "_" + strDateStamp

					+ ".png"));
			String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
			System.setProperty(ESCAPE_PROPERTY, "false");
			URL path = new File(dirPath + "/ " + screenshotName + "_"
					+ strDateStamp + ".png").toURI().toURL();
			String test = "<a href=" + path + "> click to open screenshot of "
					+ screenshotName + "</a>";
			Reporter.log(screenshotName + test + "<br>");
			Reporter.log("<br>");
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot "
					+ e.getMessage());
		}
	}
}
