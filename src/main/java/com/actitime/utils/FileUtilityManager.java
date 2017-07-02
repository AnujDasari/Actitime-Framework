package com.actitime.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.actitime.driver.Driver;

/**
 * FileUtility class to read data from Test Data sheets and Properties files
 **/

public class FileUtilityManager {
	private static HashMap<String, String> testData = new HashMap<String, String>();
	private static ArrayList<String> sheetNames = new ArrayList<>();

	public static HashMap<String, String> getTestData() {
		return testData;
	}

	public static ArrayList<String> getSheetNames() {
		return sheetNames;
	}

	/*
	 * Method to retrieve data from the TestData based on TestCaseId
	 */
	public static void retrieveData(String TestCaseDataId) {
		try {
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/data_lib/TestData.csv");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = null;
			String env = Driver.getType();
			if (env.equalsIgnoreCase("Desktop")) {
				sh = wb.getSheet("Desktop");
			} else if (env.equalsIgnoreCase("Device")) {
				sh = wb.getSheet("Device");
			} else if (env.equalsIgnoreCase("App")) {
				System.out.println("In App sheet");
				sh = wb.getSheet("App");
			}
			Row rheader = sh.getRow(0);
			Row rValues = null;
			int i = 0;
			boolean flag = true;
			do {
				i++;
				if (TestCaseDataId.equals(sh.getRow(i).getCell(0)
						.getStringCellValue().toString())) {
					rValues = sh.getRow(i);
					for (int j = 0; j < rheader.getLastCellNum(); j++) {
						testData.put(rheader.getCell(j).getStringCellValue()
								.toString(), rValues.getCell(j)
								.getStringCellValue().toString());
					}
					flag = false;
					i = sh.getLastRowNum();
				}

			} while (i < sh.getLastRowNum() || flag == true);
		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println("Exception with respect to Input/Output file");
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Test Name not found in Test data sheet");
			e.printStackTrace();
		}
	}

	/*
	 * Method to get all classes set to 'Y'
	 */
	public static ArrayList<String> getFlaggedClasses(String sheetname) {
		ArrayList<String> testCaseToExecute = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/data_lib/XMLFlag.csv");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			int i = 0;
			int RowCount = sh.getLastRowNum() + 1;
			do {
				if ((sh.getRow(i).getCell(1).getStringCellValue().toString())
						.equals("Y"))
					testCaseToExecute.add(sh.getRow(i).getCell(0)
							.getStringCellValue().toString());
				i++;

			} while (RowCount != i);

		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Exception with respect to Input/Output file");
		}
		return testCaseToExecute;

	}

	/*
	 * Method to get all classes set to 'N'
	 */
	public static ArrayList<String> getNotFlaggedClasses(String sheetname) {
		ArrayList<String> testCaseNotToExecute = new ArrayList<>();
		try {

			FileInputStream fis = new FileInputStream(
					"./src/test/resources/data_lib/XMLFlag.csv");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			int i = 0;
			int RowCount = sh.getLastRowNum() + 1;
			do {
				if ((sh.getRow(i).getCell(1).getStringCellValue().toString())
						.equals("N"))
					testCaseNotToExecute.add(sh.getRow(i).getCell(0)
							.getStringCellValue().toString());
				i++;

			} while (RowCount != i);

		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Exception with respect to Input/Output file");
		}
		return testCaseNotToExecute;

	}

	/*
	 * Method to get sheet name
	 */
	public static ArrayList<String> getSheetNameMethods() {
		try {
			sheetNames.clear();
			FileInputStream fis = new FileInputStream(
					"./src/test/resources/data_lib/XMLFlag.csv");
			Workbook wb = WorkbookFactory.create(fis);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheetNames.add(wb.getSheetName(i));
			}
		} catch (EncryptedDocumentException e) {
			System.out.println("Test data file is excrypted. Please check");
		} catch (InvalidFormatException e) {
			System.out.println("InvalidFormat Exception");
		} catch (IOException e) {
			System.out.println("Exception with respect to Input/Output file");
		}
		return sheetNames;
	}

	/*
	 * Method to get properties
	 */
	public static Properties getProperties(String propFilePath) {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(propFilePath);
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prop;
	}
}
