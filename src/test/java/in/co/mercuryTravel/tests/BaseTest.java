package in.co.mercuryTravel.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
import commonLibs.utils.ConfigFileUtils;
import commonLibs.utils.DateUtils;
import in.co.mercuryTravel.pages.Homepage;
//import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;

public class BaseTest {
	CommonDriver cmnDriver;
	String browserType;
	String baseUrl;
	Homepage homepage;
	private WebDriver driver;

	static String configFileName;
	static Properties configProperties;

	static String currentWorkingDirectory;
	static String executionStartDate;

	int pageloadtimeout;
	int elementDetectionTimeout;

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;
	String reportFilename;

	String screenshotFilename;
	ScreenshotControl screenshotControl;

	static {
		try {
			currentWorkingDirectory = System.getProperty("user.dir");
			executionStartDate = DateUtils.getCurrentDateAndTime();

			configFileName = String.format("%s/config/config.properties", currentWorkingDirectory);

			configProperties = ConfigFileUtils.readProperties(configFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeSuite
	public void preSetup() {
		initializeReports();
		
	}

	

	@BeforeClass
	public void setup() throws Exception {
		invokeBrowser();
		getDriverInstance();
		initializeApplicationPages();
		initializeScreeshotVariable();
		//closeInitialModalOnHomepage();
	}



	@AfterClass
	public void cleanUp() throws Exception {
		closeAllBrowserInstance();
		
	}
	

	



	@AfterSuite
	public void postcleanUp() {
		extent.flush();
	}
	

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		String testcaseName = result.getName();
		String screenshotFilename = String.format("%s/screenshot/%s-%s.jpeg", currentWorkingDirectory, testcaseName,
				executionStartDate);
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test case pass - " + testcaseName);

		} else if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test case fail - " + testcaseName);
			screenshotControl.captureAndSaveScreenshot(screenshotFilename);
			extentTest.addScreenCaptureFromPath(screenshotFilename);
		} else {
			extentTest.log(Status.SKIP, "Test case skipped - " + testcaseName);
		}

	}
	
	private void initializeReports() {
		reportFilename = String.format("%s/reports/MercurtTravelTestReport-%s.html", currentWorkingDirectory,
				executionStartDate);
		htmlReporter = new ExtentHtmlReporter(reportFilename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	private void invokeBrowser() throws Exception {
		extentTest = extent.createTest("Setup - Set up the pre-requisit to run automated test cases");

		browserType = configProperties.getProperty("browserType");
		extentTest.log(Status.INFO, "Browser invoked is " + browserType);
		cmnDriver = new CommonDriver(browserType);

		pageloadtimeout = Integer.parseInt(configProperties.getProperty("pageloadTimeout"));
		extentTest.log(Status.INFO, "Page load Timeout set  is " + pageloadtimeout);

		elementDetectionTimeout = Integer.parseInt(configProperties.getProperty("elementDetectionTimeout"));
		extentTest.log(Status.INFO, "Implicit wait set  is " + elementDetectionTimeout);

		cmnDriver.setPageloadTimeout(pageloadtimeout);
		cmnDriver.setElementDetectionTimeout(elementDetectionTimeout);

		baseUrl = configProperties.getProperty("baseUrl");
		extentTest.log(Status.INFO, "Base URL where the browser navigates to -  " + baseUrl);
		cmnDriver.navigateToFirstUrl(baseUrl);

		
		
		
	}
	
	private void getDriverInstance() {
		driver = cmnDriver.getDriver();
	}
	
	private void initializeApplicationPages() {
		extentTest.log(Status.INFO, "Initializing all pages");
		homepage = new Homepage(driver);
		
	}
	
	//private void closeInitialModalOnHomepage() {

		// homepage.closeInitialModal();
		
	//}



	private void initializeScreeshotVariable() {
		screenshotControl = new ScreenshotControl(driver);
		
	}
	
	private void closeAllBrowserInstance() throws Exception {
		cmnDriver.closeAllBrowsers();
		extentTest = extent.createTest("Clean Up - Clean process");
		extentTest.log(Status.INFO, "Closing all browser instances");
		
	}
}
