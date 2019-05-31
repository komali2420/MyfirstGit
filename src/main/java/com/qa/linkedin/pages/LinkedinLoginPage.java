package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.linkedin.base.Testbase;

public class LinkedinLoginPage extends Testbase{

	public LinkedinLoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);		
	}
	@FindBy(id="username")
	WebElement email_editbox;
	@FindBy(name="session_password")
	WebElement password_editbox;
	@FindBy(xpath="//*[@type='submit' and @aria-label='Sign in']")
	WebElement signin_btn;
	
	public String getLoginPageTitle() {
		return driver.getTitle();
		
	}
	
	 public LinkedinLoggedInPage login(String username,String pwd) throws IOException {
		 email_editbox.clear();
		 email_editbox.sendKeys(username);
		 password_editbox.clear();
		 password_editbox.sendKeys(pwd);
		 signin_btn.click();
		 return new LinkedinLoggedInPage();
	 }

}
