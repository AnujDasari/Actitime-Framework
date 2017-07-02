package com.actitime.driver;

import com.actitime.utils.XMLUtilityManager;

/**
 * Driver class with main method
 **/
public class Driver extends DriverManager {
	
	/**
	 * Main method
	 **/
	public static void main(String args[]) throws Exception {
		relativePath = System.getProperty("user.dir");
		runOn = System.getProperty("runOn");
		type = System.getProperty("platform");
		if(type.equalsIgnoreCase("desktop")) {
			browserName = System.getProperty("browser");
		}
		
		if (runOn.equalsIgnoreCase("Grid")) {
			retrieveGridEnvProperties();
		} else if (runOn.equalsIgnoreCase("Standalone")) {
			retrieveStandAloneEnvProperties();
		}
		createXML();
		XMLUtilityManager.autoRunXml();
	}
}