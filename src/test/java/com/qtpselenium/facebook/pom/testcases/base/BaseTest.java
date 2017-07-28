package com.qtpselenium.facebook.pom.testcases.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.qtpselenium.facebook.pom.util.ExtentManager;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.qtpselenium.facebook.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls = new Xls_Reader(FBConstants.DATA_XLS_PATH);

	
	//===== Sauce Connect Credentials 
 	public static final String USERNAME = "MDRasul";
 	public static final String ACCESS_KEY = "1df585b2-88bc-4578-8a98-c920e9eaf383";
 	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
 	public static  SessionId mySessionID;
	DesiredCapabilities myBrowserCapability = new DesiredCapabilities();
	static String myScreenResolutionCapability = "screenresolution";

 	
 	
	public WebDriver driver;
	public void init(String bType){
		test.log(LogStatus.INFO, "Opening browser - "+ bType);
		if(!FBConstants.GRID_RUN)
		{
			// local machine
			if(bType.equals("Mozilla"))
			{
				System.setProperty("webdriver.gecko.driver", FBConstants.Gecko_DRIVER_EXE);
				//driver= new FirefoxDriver();
			}
			else if(bType.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
				driver= new ChromeDriver();
			}
		}
		else
		{

			// grid
			DesiredCapabilities cap=null;
			if(bType.equals("Mozilla"))
			{
				cap = DesiredCapabilities.firefox();

				cap.setCapability("name", "Test Case Name");

				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}
			else if(bType.equals("Chrome"))
			{
				 cap = DesiredCapabilities.chrome();
				 cap.setCapability("name", "Test Case Name");
				 cap.setBrowserName("chrome");
				 cap.setCapability(myScreenResolutionCapability,"1600x1200");    			
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try 
			{
			    driver = new RemoteWebDriver(new URL(URL), cap);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		
			
			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened browser successfully - "+ bType);
		mySessionID = ((RemoteWebDriver)driver).getSessionId();	

	}
	
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	public void takeScreenShot()
	{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}
}
