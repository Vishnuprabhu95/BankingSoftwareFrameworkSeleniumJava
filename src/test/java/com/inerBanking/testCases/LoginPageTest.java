package com.inerBanking.testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inerBanking.pageObjects.LoginPage;

public class LoginPageTest extends BaseClass{

	@Test
	public void loginTest() {		
		
		LoginPage loginPage = new LoginPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"uid\"]")));
		
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubmit();
		
		String expectedTitle = "GTPL Bank Managers HomePage";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}	
	
	@Test
	public void loginTest1() {
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		logger.info("Logged into application successfully");
		
		LoginPage loginPage = new LoginPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"uid\"]")));
		
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubmit();
		
		String expectedTitle = "GTPL Bank Manager HomePage";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		loginPage.clicklogOut();
		
	}	
}
