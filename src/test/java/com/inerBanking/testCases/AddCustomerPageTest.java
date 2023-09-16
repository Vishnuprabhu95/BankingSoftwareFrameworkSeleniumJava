package com.inerBanking.testCases;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.inerBanking.pageObjects.AddCustomerPage;
import com.inerBanking.pageObjects.LoginPage;

import net.bytebuddy.utility.RandomString;

public class AddCustomerPageTest extends BaseClass{

	@Test
	public void addNewCustomer() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"uid\"]")));
		
		loginPage.setUserName(userName);
		loginPage.setPassword(password);
		loginPage.clickSubmit();
		
		Thread.sleep(3000);
	
		
		AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
		addCustomerPage.clickAddNewCustomer();
		
		Thread.sleep(3000);
		WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
	    driver.switchTo().frame(frame1);
	    WebElement frame2 = driver.findElement(By.id("ad_iframe"));
	    driver.switchTo().frame(frame2);
	    driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
	    driver.switchTo().defaultContent();
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("const elements = document.getElementById('dismiss-button'); elements[0].remove()");
//		WebElement adButton = driver.findElement(By.id("dismiss-button"));
//		js.executeScript("arguments[0].click();", adButton);

		Thread.sleep(1000);
		
		String randomStr = randomString();
		
		addCustomerPage.custName("Vishnu"+randomStr);
		addCustomerPage.custgender("male");
		addCustomerPage.custdob("10", "15", "1995");
		Thread.sleep(3000);
		addCustomerPage.custaddress("India");
		addCustomerPage.custCity("CBE");
		addCustomerPage.custState("TN");
		addCustomerPage.custpinno("641004");
		addCustomerPage.custtelephoneno("9677829892");
		
		String remail = randomStr+"@gmail.com";
		addCustomerPage.custemailid(remail);
//		addCustomerPage.custpassword("abcde");
		addCustomerPage.custSubmit();
		
		Thread.sleep(3000);
		
		boolean res = driver.getPageSource().contains("Connection failed: Access denied for user 'root'@'localhost' (using password: NO)");
		
		if(res==true) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	

}
