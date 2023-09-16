package com.inerBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.inerBanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public String userName = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		logger = Logger.getLogger("inerBanking");
		PropertyConfigurator.configure("Log4j.properties");		
		
		
		if(br.equals("chrome")) {
			ChromeOptions opt = new ChromeOptions();
//			opt.addExtensions(new File("./Extensions/AdBlock.crx"));
			opt.addArguments("--disable-popup-blocking");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
			opt.merge(capabilities);
			System.setProperty("webdriver.chrome.driver", readConfig.getchromepath());
			driver = new ChromeDriver(opt);	
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getgeckopath());
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		logger.info("Logged into application successfully");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	

	 public String randomString() {
		 return RandomStringUtils.randomAlphabetic(5);
	 }
	 
	 public String randomNumeric() {
		 return RandomStringUtils.randomNumeric(5);
	 }
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
