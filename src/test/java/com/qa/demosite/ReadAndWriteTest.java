package com.qa.demosite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteTest {
	Constants page = new Constants();
	WebDriver driver;
	Actions action;
	ExtentReports report;
	ExtentTest test;

	@Before
	public void startTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(page.addUserPage);
		action = new Actions(driver);
		report = new ExtentReports(
				"C:\\Users\\Admin\\eclipse-workspace\\demosite\\src\\test\\java\\com\\qa\\demosite\\report.html");
		test = report.startTest("Testing testing 1 2 3");
	}

	@Test
	public void DDTTest() throws IOException, InterruptedException {

		test.log(LogStatus.INFO, "cracking on with this juicy code");
		assertEquals("http://thedemosite.co.uk/addauser.php", driver.getCurrentUrl());
		test.log(LogStatus.PASS, "Test success, baby!");

		FileInputStream file = null;
		file = new FileInputStream(page.speadsheet);

		XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		loginPageClass createUser = null;
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFCell username = sheet.getRow(i).getCell(0);
			XSSFCell password = sheet.getRow(i).getCell(1);
			driver.get("http://thedemosite.co.uk/addauser.php");
			driver.findElement(By.name("username")).sendKeys(username.getStringCellValue());
			driver.findElement(By.name("password")).sendKeys(password.getStringCellValue());
			driver.findElement(By.name("FormsButton2")).click();
			driver.findElement(By.xpath("/html/body/div[1]/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
			driver.findElement(By.name("username")).sendKeys(username.getStringCellValue());
			driver.findElement(By.name("password")).sendKeys(password.getStringCellValue());
			driver.findElement(By.name("FormsButton2")).click();
			assertEquals("**Successful Login**", driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
		}
	}

	@After
	public void endTest() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
