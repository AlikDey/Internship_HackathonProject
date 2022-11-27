package com.test.Main;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Lists;
import com.main.Basic.DriverSetup;
import com.main.Basic.MainDriver;
import com.main.Basic.TransformPage;

public class Main {
	
	public static WebDriver driver=null;
	ExtentReport report = new ExtentReport();
	
	@BeforeSuite
	public void ManageDriver() throws Exception {
		
		DriverSetup obj = new DriverSetup();
		report.createTest("Invoke Browser");
		report.actionInfo("Launching driver");
		driver = obj.getWebDriver();
		report.actionPass("Diver launched Successfully");
	}
	
	@Test(priority=0)
	public void searchWebCourse() {
		report.createTest("Searching Web Development Course Details");
		MainDriver md = new MainDriver(driver,report);
		md.searchCourse("Web Development Courses");
		report.actionPass("Web Development Courses are searched");
	}
	
	@Test(priority=1)
	public void findWebCourse() {
		report.createTest("Sorting desired Web Development Courses");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.findWebCourse();
			report.actionPass("Desired courses found");
		} catch (Exception e) {
			report.actionFail("Searching courses failed due to: "+e.getMessage());
		}
	}
	
	@Test(priority=2)
	public void findDetails() {
		report.createTest("Fetching Web Development Course Details");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.getWebCourseDetails();
			report.actionPass("Course details recorder successfully");
		} catch (Exception e) {
			report.actionFail("Error encountered due to: "+e.getMessage());
		}
	}
	
	@Test(priority=3)
	public void navigateToHome() {
		TransformPage tp = new TransformPage(driver,report);
		tp.navigateToHomePage();
	}
	
	@Test(priority=4)
	public void searchLangLearn() {
		report.createTest("Search language learning");
		MainDriver md = new MainDriver(driver,report);
		md.searchCourse("Language Learning");
		report.actionPass("Language learning courses are searched");
	}
	
	@Test(priority=6)
	public void sortByLanguage() {
		report.createTest("language based language learning");
		MainDriver md = new MainDriver(driver,report);
		try {
			md.sortLang();
			report.actionPass("All languages are explored");
		} catch (Exception e) {
			report.actionFail("Error encountered: "+e.getMessage());
		}
	}
	
	@Test(priority=5)
	public void langLearn() throws InterruptedException {
		report.createTest("Level based language learning");
		MainDriver mn = new MainDriver(driver,report);
		mn.langLearn();
		report.actionPass("All levels are explored");
	}
	
	@Test(priority=7)
	public void navigateToHomeAgain() {
		TransformPage tp = new TransformPage(driver,report);
		tp.navigateToHomePage();
	}
	
	@Test(priority=8)
	public void navigatePage() {
		report.createTest("Navigate to Form");
		TransformPage tp = new TransformPage(driver,report);
		tp.navigateToForm();
		report.actionPass("Time to fill the form");
		
	}
	
	@Test(priority=9)
	public void fillForm() {
		report.createTest("Filling Ready to Transform form");
		TransformPage tp = new TransformPage(driver,report);
		try {
			tp.fillForm();
			report.actionPass("Form submitted");
		} catch (IOException e) {
			report.actionFail("Error encountered: "+e.getMessage());
		}
	}
	
	@Test(priority=10)
	public void fetchMessage() {
		report.actionInfo("Fetching Message");
		TransformPage tp = new TransformPage(driver,report);
		tp.getMessage();
		report.actionPass("Message printed successfully");
	}
	
	@AfterSuite
	public void closeDriver() {
		report.completeTest();
		driver.quit();
	}

	public void invokeTest() {

		TestNG test = new TestNG();
		List<String> suite = Lists.newArrayList();
		suite.add("testng.xml");
		test.setTestSuites(suite);
		test.run();

	}

	public static void main(String[] args) {
		Main mn = new Main();
		mn.invokeTest();

	}

}
