package com.qtpselenium.facebook.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.pages.session.settings.GeneralSettingsPage;
import com.qtpselenium.facebook.pom.testcases.base.BaseTest;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;

public class ChangePasswordTest extends BaseTest{
	String testCaseName="ChangePasswordTest";
	
	@Test(dataProvider="getData")
	public void changePasswordTest(Hashtable<String,String> data){
		
		test = extent.startTest(testCaseName);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(FBConstants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		test.log(LogStatus.INFO, "Starting test");
		init(data.get("Browser"));
		LaunchPage launchPage =new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);
		
		LoginPage loginPage = launchPage.gotoLoginPage();
		test.log(LogStatus.INFO, "Logging in");
		Object page=loginPage.doLogin(data.get("Username"), data.get("OldPassword"));
		
		if(page instanceof LoginPage)
			reportFailure("Could not login");
		// change password
		LandingPage landingPage = (LandingPage)page;
		GeneralSettingsPage settings = landingPage.getMenu().gotoSettings();
		settings.gotoPasswordChange();
		String actualResult=settings.doPasswordChange(data.get("OldPassword"),data.get("NewPassword"));
		test.log(LogStatus.INFO, "Result of changing password - "+actualResult);

		//validation
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure("Got password change result as - "+actualResult);
		
		test.log(LogStatus.PASS, "Test Passed");
		
	}
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
	
}
