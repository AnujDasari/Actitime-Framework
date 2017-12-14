# Actitime-Framework Project  [![Build Status](https://travis-ci.org/AnujDasari/Actitime-Framework.svg?branch=master)](https://travis-ci.org/AnujDasari/Actitime-Framework)
A simple framework to run Functional and UI Test Scripts for actiTIME application. The same framework can be used to run scripts - in Desktop browsers(firefox,chrome and internet explorer); in chrome browser on Android Device; and to run scripts on a Native Android App. 

Sample test scripts have been written to execute test scripts across three platforms - Desktop, Device and App.

------------------------------------------------------------------------

### Description of Project Files
1. Driver.java : Contains java main method which runs the programmatically generated testng.xml.  

2. DriverFactory.java : Creates and returns WebDriver instance to run automated tests against desktop browsers, device browser and        android app. 

3. DriverManager.java : Reads properties from the config files.

4. FileUtilityManager.java : Includes methods to read and retrieve data from XML Flag,Test Data sheets and Properties files. 

5. XMLUtilityManager.java : Programmatically generates and runs testng.xml based on properties and parameters set by the user.  

6. HelperManager.java : Contains generic methods to be used across the entire framework.  

7. ExtentReport.java : Contains Extent Report screenshot and report creation functionality.   

8. ReportNGReport.java : Contains ReportNG Report screenshot and report creation functionality.

------------------------------------------------------------------------

### Resource and .exe files
1. chromedriver - This folder contains the chromedriver.exe.

2. geckodriver - This folder contains the geckodriver.exe.

3. iedriver - This folder contains the IEDriverServer.exe.

4. apk - This folder contains the Apk of the app to be tested.

5. grid - This folder contains the node json config files to run in grid.

6. data_lib - This folder contains the XMLFlag and TestData test data sheets.   
    a.**TestData** sheet is used to set the test data for the scripts that are being automated.  
    b.**XMLFlag** sheet is used to set the test scripts that have to be automated or run.
    
7. configuration - This folder contains the property files - StandAloneEnvProperties and GridEnvProperties.    
    a.**config.properties** -  Contains properties to run scripts on desktop and device browser.  
    b.**grid_config.properties** - Contains properties to run scripts in parallel using grid.
    
------------------------------------------------------------------------
    
### Test Report
Test Reports are generated under Resources folder:
1. Extent Reports : `./Resources/ExtentReports`  
2. ReportNG Reports : `./Resources/ReportNGReports`  

------------------------------------------------------------------------

### Running the Framework
To run the scripts in desktop browser:
`mvn test -DrunOn=standalone -Dplatform=desktop -Dbrowser=chrome/firefox/internetexplorer`

To run the scripts in device browser:
`mvn test -DrunOn=standalone -Dplatform=device`

To run app scripts:
`mvn test -DrunOn=standalone -Dplatform=app` 

To run scripts in grid:
`mvn test -DrunOn=grid -Dplatform=desktop` 

------------------------------------------------------------------------

### Setup and Installation of the Framework
Refer to the wiki page **https://github.com/AnujDasari/Actitime-Framework/wiki** for details on setting up and installing the framework.

