package com.inerBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inerBanking.testCases.BaseClass;

public class Reporting extends BaseClass implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentSparkReporterConfig config;
	public ExtentReports extentReports;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		
		extentReports = new ExtentReports();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //dynamic timestamp file name
		String repName = "Test-Report-" + timeStamp + ".html";
		File file = new File(repName);
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extent-Reports/" + file); //specific location
//		htmlReporter.loadConfig(System.getProperty("user.dir" + "/extent-config.xml"));
		
		config = sparkReporter.config();
		
		try {
			sparkReporter.loadXMLConfig("./extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReports.attachReporter(sparkReporter);
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		logger = extentReports.createTest(tr.getName()); //create new entry in report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); //send result of test to report
	}
	

	
	public void onTestFailure(ITestResult tr) {
		logger = extentReports.createTest(tr.getName()); //create new entry in report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); //send result of test to report
		
			try {
				System.out.println("I'm here!");
				logger.addScreenCaptureFromPath(takeScreenshot(tr.getName(), driver), tr.getName()); //attach to report on failures
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public static String takeScreenshot(String screenshotName, WebDriver driver) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File sourceScFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destScFile = new File("./Screenshots/"+screenshotName+"-"+timeStamp+".png");
		FileUtils.copyFile(sourceScFile, destScFile);
		return destScFile.getAbsolutePath();
}
	
	public void onTestSkipped(ITestResult tr) {
		logger = extentReports.createTest(tr.getName()); //create new entry in report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); //send result of test to report
	}
	
	public void onFinish(ITestContext testContext) {
		extentReports.flush();
	}

}
