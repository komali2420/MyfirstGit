package com.qa.linkedin.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.linkedin.base.Testbase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedInPage;
import com.qa.linkedin.pages.LinkedinLoginPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.TestUtil;



public class SearchDDTTest extends Testbase{
	LinkedinHomePage hmpg=null;
	LinkedinLoginPage lpg=null;
	LinkedinLoggedInPage lggpg=null;
	SearchResultsPage srpg=null;
	
	
  public SearchDDTTest() throws IOException {
		super();
		
	}

@Test(dataProvider = "dp")
  public void searchTest( String s) throws InterruptedException, IOException {
	Assert.assertTrue(lggpg.verifyProfileCard());
	lggpg.searchpeople(s);
	int cnt=srpg.getResultsCount();
	System.out.println("results count for "+s+" is....>"+cnt);
	driver.navigate().back();
	driver.navigate().refresh();
  }

  @DataProvider
  public Object[][] dp() throws  IOException {
    Object[][] data=TestUtil.getTestData(TestUtil.TESTDATA_SHEET_PATH1,"Sheet1");
    return data; 
    
  }
  @BeforeClass
  public void beforeclass() throws  IOException {
	  initWebdriver();
	  hmpg=new LinkedinHomePage();
	  lpg=new LinkedinLoginPage();
	  lggpg=new LinkedinLoggedInPage();
	  srpg=new SearchResultsPage();
	  hmpg.clickOnSignInLink();
	  lpg.login(prop.getProperty("username"),prop.getProperty("pwd"));
  }


  @AfterClass
  public void afterClass() {
	  lggpg.logOut();
	  driver.close();
  }

}
