package com.inerBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver lDriver;
	
	public LoginPage(WebDriver rDriver){
		lDriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(name="uid")
	WebElement textUserName;
	
	@FindBy(name="password")
	WebElement textPassword;
	
	@FindBy(name="btnLogin")
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[text()='Log out']")
	WebElement logOut;
	
	public void setUserName(String uname) {
		textUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		textPassword.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}
	
	public void clicklogOut() {
		logOut.click();
	}
	
}
