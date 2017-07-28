package com.iscript.tesecases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.iscripts.pages.HomePage;
import com.iscripts.pages.SignUpPage;
import com.qtpselenium.facebook.pom.testcases.base.BaseTest;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;

public class createAccount  extends BaseTest
{
	
	
	public boolean passOrFail = false; // Need for Find all Test case Passed/Fail Flag

	
	
	@Test
	public void createAccountTest()
	{
		
		test=extent.startTest("Profile Test");
		test.log(LogStatus.INFO, "Starting  Create Account Test");

		
		init("Chrome");
		HomePage home = new HomePage(driver,test);
		home.goToHomePage();
		home.gotoCreateAccountPage();
		
		
		SignUpPage signup = new SignUpPage(driver,test);
		
		if(signup.createAccount()==true)
		{
			test.log(LogStatus.PASS, "Crete ccount Passed");
			super.takeScreenShot();
			passOrFail = true;
		}
		else 
		{
			test.log(LogStatus.PASS, "Crete ccount Failed");
			
			passOrFail = false;

		}
		
	}

	@AfterMethod
	public void quit()
	{
		if(extent!=null)
		{
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
		
		if(FBConstants.GRID_RUN)
		{
			//System.out.println("Session ID Sending to SauceLab "+ super.mySessionID);
			//System.out.println("Check Test Pass Or Fail "+ passOrFail);

			//super.sendPAssFailToSauce(super.mySessionID,passOrFail,super.USERNAME,super.ACCESS_KEY);
		}
	}
	
	
}
