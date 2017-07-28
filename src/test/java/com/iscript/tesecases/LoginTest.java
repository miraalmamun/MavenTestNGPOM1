package com.iscript.tesecases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.iscripts.pages.HomePage;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.pages.session.ProfilePage;
import com.qtpselenium.facebook.pom.testcases.base.BaseTest;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest  extends BaseTest
{
	String testCaseName="ProfileTest";
	
	@Test
	public void LandOnIscriptHomePage()
	{
		
		test=extent.startTest("Profile Test");
		test.log(LogStatus.INFO, "Starting  Test");

		init("Chrome");
		
		HomePage home = new HomePage(driver,test);
		
		if(home.goToHomePage()==true)
		{
			test.log(LogStatus.PASS, "Test Passed");
		}
	}
	
	@AfterMethod
	public void quit()
	{
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}

}
