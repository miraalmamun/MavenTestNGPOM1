package com.iscripts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SignUpPage extends BasePage{
	
	
	//===>> Web Elements For HOme Page 
	public String email = "//*[@id='txtEmail']";
	public String username = "//*[@id='txtUserName']";
	public String password = "//*[@id='txtPassword']";
	public String conpassword = "//*[@id='txtCnfPassword']";
	public String fname = "//*[@id='txtFirstName']";
	public String lname = "//*[@id='txtLastName']";
	public String submitButton = "//*[@id='btnRegister']";


	
	public SignUpPage(WebDriver driver, ExtentTest test)
	{
		super(driver,test);
	}
	
	public boolean createAccount()
	{
		
		try
		{
			typeText(email,"arhimel@test.com");
			typeText(username,"arhimel");
			typeText(password,"abc123");
			typeText(conpassword,"abc123");
			typeText(fname,"Mr");
			typeText(lname,"smith");

			clickLink(submitButton);
			return true;
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, e.getMessage());
			super.takeScreenShot();
			return false;
		}
	}
	

	
	

}
