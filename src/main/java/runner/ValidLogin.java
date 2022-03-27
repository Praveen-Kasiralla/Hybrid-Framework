package runner;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import pageobject.HomePage;
import utility.BaseClass;
import utility.ConfigReader;

public class ValidLogin extends BaseClass {

	public HomePage home;
	public HashMap<String, String> searchData;

	@Test(priority = 0)
	public void setUp() {
		logs.createLogger(ValidLogin.class);
		home = new HomePage(driver, webDriver, exReport);
	}

	@Test(priority = 1)
	public void openNoDroker() throws InterruptedException, IOException {
		exReport.createNewTestReport("Open NoBroker");
		logs.enterInfoLog("Open NoBroker");
		exReport.enterInfoLog("Opening NoBroker.in");
		webDriver.openPage(driver, ConfigReader.getUrl());
		if (webDriver.getCurrentUrl(driver).equals(ConfigReader.getUrl())) {
			exReport.enterPassLogWithSnap("NoBroker as opened");
			logs.enterInfoLog("NoBroker as opened");
		} else {
			exReport.enterFailLogWithSnap("NoBroker as opened");
		}
		searchData = excel.getExcelData(ConfigReader.getLoginData(), 1);
	}
	
	@Test(priority = 2)
	public void clickLogin() throws IOException {
		exReport.createNewTestReport("Click on Login");
		home.clickLogin();
	}
	
	@Test(priority = 3)
	public void enterNumber() throws IOException {
		exReport.createNewTestReport("Enter Number");
		home.enterNumberAndSelectPassword(searchData.get("Phone Number"));
	}
	@Test(priority = 4)
	public void enterPassAndLogin() throws IOException {
		exReport.createNewTestReport("Enter Valid Password");
		home.enterValidPassword(searchData.get("Password"));
	}

}
