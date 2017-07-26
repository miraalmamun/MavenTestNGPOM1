package com.qtpselenium.facebook.pom.pages.session.settings;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GeneralSettingsPage extends BasePage{

	@FindBy(xpath=FBConstants.PASSWORD_CHANGE)
	public WebElement editPassword;
	
	@FindBy(xpath=FBConstants.OLD_PASSWORD)
	public WebElement oldPassword;
	
	@FindBy(xpath=FBConstants.NEW_PASSWORD)
	public WebElement newPassword;
	
	@FindBy(xpath=FBConstants.CONFIRM_CHANGE)
	public WebElement confirmPassword;
	
	@FindBy(xpath=FBConstants.SAVE_CHANGES)
	public WebElement saveChanges;
	
	@FindBy(xpath=FBConstants.KILL_SESSION)
	public WebElement killSessionRadio;
	
	@FindBy(xpath=FBConstants.CONTINUE_PASSWORD_CHANGE_BUTTON)
	public WebElement continuePasswdChange;
	
	
	public GeneralSettingsPage(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	public void gotoPasswordChange(){
		test.log(LogStatus.INFO, "clicking on password change");
		if(!isElementPresent(FBConstants.PASSWORD_CHANGE)){	
			reportFailure("Element not found "+ FBConstants.PASSWORD_CHANGE);
		}
			
		editPassword.click();
		test.log(LogStatus.INFO, "On password change Page");
		

	}

	public String doPasswordChange(String oPassword,String nPassword) {
		test.log(LogStatus.INFO, "Changing password");
		oldPassword.sendKeys(oPassword);
		newPassword.sendKeys(nPassword);
		confirmPassword.sendKeys(nPassword);
		saveChanges.click();
		if(!isElementPresent(FBConstants.PASSWORD_CHANGE))
			return "Unsuccessful";
		
		killSessionRadio.click();
		continuePasswdChange.click();
		test.log(LogStatus.INFO, "Changed Password Successfully");
		return "Success";
	}
}
