package com.qa.linkedin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.linkedin.base.Testbase;

public class TestUtil extends Testbase {

	public TestUtil() throws IOException {
		super();
		
	}
public static  final long IMPLICIT_WAIT=30;
public static final long EXPLICIT_WAIT=30;


public static String TESTDATA_SHEET_PATH1 =System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\data\\searchdatamay22.xlsx";
static Workbook book;
static Sheet sheet;

public static Object[][] getTestData(String fpath,String sheetName) throws InvalidFormatException, IOException {

	//THIS IS THE CODE HOW TO READ DATA FROM EXCEL SHEET
//Specify the path of file
File srcFile=new File(fpath);

//load file
FileInputStream fis=new FileInputStream(srcFile);
//Load workbook
XSSFWorkbook wb=new XSSFWorkbook(fis);

//Load sheet- Here we are loading first sheetonly
XSSFSheet sh1= wb.getSheet(sheetName);
	//two d array declaration
Object[][] data=new Object[sh1.getLastRowNum()][sh1.getRow(0).getLastCellNum()];
	// System.out.println(sheet.getLastRowNum() + "--------" +
	// sheet.getRow(0).getLastCellNum());
	for(int i=0; i<sh1.getLastRowNum(); i++) {
		for(int k=0; k < sh1.getRow(0).getLastCellNum(); k++) {
			data[i][k]=sh1.getRow(i+1).getCell(k).toString();
			// System.out.println(data[i][k]);
		}
	}
	return data;
}
	//TILL HERE THE CODE FOR READING DATA FROM THE EXCEL SHEET

public static void takeScreenshotAtEndOfTest() throws IOException {
	//take a screenshot of sign in page
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			//copy to project location
	FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\src\\Screenshots\\"+"screenshot_"+timeStamp()+".png"));
			
}

public static String timeStamp(){
	return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(0));
}

public static String getScreenshot() {
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshots/" +"screenshot_"+ timeStamp() + ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		System.out.println("Capture Failed " + e.getMessage());
	}
	return path;
}


}
