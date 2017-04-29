package com.actitime.driver;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import com.actitime.utils.FileUtilityManager;
import com.actitime.utils.XMLUtilityManager;

/**
 * DriverManager class to Read properties from the configuration files.
 **/

public class DriverManager {
	protected static String relativePath;
	protected static String configPath;
	protected static String gridConfigPath;
	protected static String app;
	protected static String browserName;
	protected static String browserName1;
	protected static String browserName2;
	protected static String browserName3;
	protected static String deviceNodeUrl;
	protected static String nodeUrl1;
	protected static String nodeUrl2;
	protected static String nodeUrl3;
	protected static String automationName;
	protected static String deviceName;
	protected static String platformName;
	protected static String platformVersion;
	protected static String noReset;
	protected static String fullReset;
	protected static String desktopUrl;
	protected static String deviceUrl;
	protected static String type;
	protected static String runOn;
	protected static String device;

	public static String getDesktopUrl() {
		return desktopUrl;
	}

	public static String getDeviceUrl() {
		return deviceUrl;
	}

	public static String getApp() {
		return app;
	}

	public static String getBrowserName() {
		return browserName;
	}

	public static String getAutomationName() {
		return automationName;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static String getPlatformName() {
		return platformName;
	}

	public static String getPlatformVersion() {
		return platformVersion;
	}

	public static String getNoReset() {
		return noReset;
	}

	public static String getFullReset() {
		return fullReset;
	}

	public static String getRunOn() {
		return runOn;
	}

	public static String getType() {
		return type;
	}

	public static String getConfigPath() {
		return configPath;
	}

	public static String getGridConfigPath() {
		return gridConfigPath;
	}

	public static String getBrowserName1() {
		return browserName1;
	}

	public static String getBrowserName2() {
		return browserName2;
	}

	public static String getBrowserName3() {
		return browserName3;
	}

	public static String getDeviceNodeUrl() {
		return deviceNodeUrl;
	}

	public static String getNodeUrl1() {
		return nodeUrl1;
	}

	public static String getNodeUrl2() {
		return nodeUrl2;
	}

	public static String getNodeUrl3() {
		return nodeUrl3;
	}

	public static String getDevice() {
		return device;
	}

	public static String getRelativePath() {
		return relativePath;
	}

	/**
	 * This method retrieves all properties from the StandAloneEnvProperties
	 * file
	 **/
	protected static void retrieveStandAloneEnvProperties() {
		configPath = "./src/test/resources/configuration/config.properties";

		Properties prop = FileUtilityManager.getProperties(configPath);

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("desktopUrl")) {
				desktopUrl = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceUrl")) {
				deviceUrl = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformName")) {
				platformName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("platformVersion")) {
				platformVersion = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("automationName")) {
				automationName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceName")) {
				deviceName = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("noReset")) {
				noReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("fullReset")) {
				fullReset = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("app")) {
				app = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("device")) {
				device = prop.getProperty(key);
			} else
				continue;
		}
	}

	/**
	 * This method retrieves all properties from the GridEnvProperties file
	 **/
	protected static void retrieveGridEnvProperties() {
		gridConfigPath = "./src/test/resources/configuration/grid_config.properties";

		Properties prop = FileUtilityManager.getProperties(gridConfigPath);

		Set<Object> set = prop.keySet();

		Iterator<Object> it = set.iterator();

		for (int i = 0; i < set.size(); i++) {
			String key = (String) it.next();

			if (key.equalsIgnoreCase("desktopUrl")) {
				desktopUrl = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceUrl")) {
				deviceUrl = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName1")) {
				browserName1 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName2")) {
				browserName2 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("browserName3")) {
				browserName3 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("deviceNodeUrl")) {
				deviceNodeUrl = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("nodeUrl1")) {
				nodeUrl1 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("nodeUrl2")) {
				nodeUrl2 = prop.getProperty(key);
			} else if (key.equalsIgnoreCase("nodeUrl3")) {
				nodeUrl3 = prop.getProperty(key);
			} else
				continue;
		}
	}

	/**
	 * This method selects the sheet based on platform type
	 **/
	protected static void createXML() throws Exception {
		if (type.equalsIgnoreCase("Desktop")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtilityManager.createXmlForGridConfig("TestScriptsWeb",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtilityManager.createXmlForStandAloneConfig(
						"TestScriptsWeb", browserName);
			}
		}

		if (type.equalsIgnoreCase("Device")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtilityManager.createXmlForGridConfig("TestScriptsDevice",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtilityManager.createXmlForStandAloneConfig(
						"TestScriptsDevice", app);
			}

		}

		if (type.equalsIgnoreCase("App")) {
			if (runOn.equalsIgnoreCase("grid")) {
				XMLUtilityManager.createXmlForGridConfig("TestScriptsApp",
						browserName1, browserName2, browserName3);
			} else if (runOn.equalsIgnoreCase("StandAlone")) {
				XMLUtilityManager.createXmlForStandAloneConfig(
						"TestScriptsApp", app);
			}
		}
	}

}
