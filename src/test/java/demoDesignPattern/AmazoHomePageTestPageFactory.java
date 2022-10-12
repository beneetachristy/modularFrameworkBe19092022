package demoDesignPattern;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import designPatterns.AmazonHomepage;
import designPatterns.AmazonHomepagePageFactory;

public class AmazoHomePageTestPageFactory {
	CommonDriver cmnDriver;
	AmazonHomepagePageFactory homepage;
	String url = "https://www.amazon.in/";
	WebDriver driver;
	
	@BeforeClass
	public void invokeBrowser() throws Exception{
		
		cmnDriver = new CommonDriver("chrome");
		cmnDriver.setPageloadTimeout(60);
		cmnDriver.setElementDetectionTimeout(10);
		cmnDriver.navigateToFirstUrl(url);
		driver = cmnDriver.getDriver();
		homepage = new AmazonHomepagePageFactory(driver);
	}
	
	@Test
	public void searchProduct() throws Exception {
		String product = "iPhone";
		String category = "Electronics";
		homepage.searchProduct(product, category);
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		cmnDriver.closeAllBrowsers();
	}
	
}
