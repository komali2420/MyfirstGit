package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.linkedin.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {
	 
	public static WebDriver driver=null;
   public static WebDriverWait wait=null;
	public static Properties prop=null;
	
  public Testbase() throws IOException {
	  //create an object for Properties class
	  prop=new Properties();
	  
	  //read the config Properties file
	  try {
	  FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\config\\config.properties");
	 //load all the properties
	  prop.load(fis);
	  } catch(FileNotFoundException e) {
		  e.printStackTrace();
	  }
	  

  }
   public static void initWebdriver()  {
	   //fetch the browser name
	   String browsername=prop.getProperty("browser");
	   if(browsername.equalsIgnoreCase("firefox")) {
		   //this line will download gecko driver..no need to write driver.get nothing
		   WebDriverManager.firefoxdriver().setup();
		   driver=new FirefoxDriver();
	   } else if (browsername.equalsIgnoreCase("chrome")) {
		   WebDriverManager.chromedriver().setup();
		   driver=new ChromeDriver();
	   } else if(browsername.equalsIgnoreCase("edge")) {
		   WebDriverManager.edgedriver().setup();
		   //driver=new EdgeDriver(); 
	   } else if(browsername.equalsIgnoreCase("ie")) {
		   WebDriverManager.iedriver().setup();
		   driver=new InternetExplorerDriver(); 
	   }
		   //add implicit 
		   driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		   
		   //maximize the window
		   driver.manage().window().maximize();
		   //open the url
		   driver.get(prop.getProperty("url"));
		   //driver.navigate().to("http://linkedin.com");
		   //create an object for WebDriverWait class
		   wait=new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
		    
	   
	   
	   
   }
}
