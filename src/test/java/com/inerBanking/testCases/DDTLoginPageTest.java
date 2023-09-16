package com.inerBanking.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inerBanking.pageObjects.LoginPage;
import com.inerBanking.utilities.XLUtils;

public class DDTLoginPageTest extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"uid\"]")));
		loginPage.setUserName(user);
		logger.info(user + "Username added");
		loginPage.setPassword(pwd);
		logger.info(pwd + "Password added");
		loginPage.clickSubmit();
		logger.info("Submitted");

		Thread.sleep(5000);

		String expectedTitle = "GTPL Bank Manager HomePage";
		String actualTitle = driver.getTitle();


		if (actualTitle.equals(expectedTitle)) {
			Assert.assertEquals(actualTitle, expectedTitle);
			Assert.assertTrue(true);
			logger.info("Login passed");
			loginPage.clicklogOut();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		} else {
			System.out.println("Not loop");
			try {
				driver.switchTo().alert().accept();;
				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
				logger.info("Login failed");
			} catch (UnhandledAlertException  e) {
				Assert.assertTrue(false);
				logger.info("Login failed");
			}
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		System.out.println("Started");
		String path = "./src/test/java/com/inerBanking/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		System.out.println("Startedrownum" + rownum);
		int colcount = XLUtils.getCellCount(path, "Sheet1", rownum);
		System.out.println("Startedcolcount" + colcount);
		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				System.out.println("row:" + (i - 1) + "col:" + j + "Value:" + logindata[i - 1][j]);
			}
		}

		return logindata;

	}

}
