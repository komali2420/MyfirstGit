package com.qa.linkedin.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.linkedin.base.Testbase;


public class LinkedinLoggedInPage extends Testbase {

	public LinkedinLoggedInPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	WebElement Profile_rail_card;
	@FindBy(id="nav-settings__dropdown")
	WebElement profile_image;
	@FindBy(xpath="//*[contains(@data-control-name,'nav.settings_signout')]")
	WebElement signout_link;
	@FindBy(xpath="//*[contains(@data-control-name,'nav.search_button')]")
	WebElement search_tourch_icon;
	@FindBy(xpath="//*[@placeholder='Search' and @role='combobox']")
	WebElement search_editbox;
	
	public boolean verifyProfileCard() {
		wait.until(ExpectedConditions.visibilityOf(Profile_rail_card));
      return Profile_rail_card.isDisplayed();
	}
	
	public SearchResultsPage searchpeople(String peopleKeyword) throws InterruptedException, IOException {
		search_editbox.clear();
		search_editbox.sendKeys(peopleKeyword);
		//search_tourch_icon.sendKeys(Keys.ENTER);
		search_tourch_icon.click();
		Thread.sleep(3000);
		return new SearchResultsPage();
	}
	public void logOut() {
		profile_image.click();
		wait.until(ExpectedConditions.visibilityOf(signout_link));
		signout_link.click();
				
	}

}
