package in.co.mercuryTravel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class MercuryTravelTestcases extends BaseTest {
	@Test
	public void verifyUserLoginWithCorrectCredentials() throws Exception{
		extentTest = extent.createTest("TC-001 - Verify UserLogin with correct credentials");
		
		String username=configProperties.getProperty("userEmailId");
		extentTest.log(Status.INFO, "User Email Id - "+username);
		
		String password= configProperties.getProperty("userPassword");
		extentTest.log(Status.INFO, "User Password - "+password);
		
		homepage.userLogin(username, password);
		String expectedWelcomeText = configProperties.getProperty("expectedWelcomeText");
		String actualWelcomeText = homepage.getWelcomeMessage();
		Assert.assertEquals(actualWelcomeText, expectedWelcomeText);
		extentTest.log(Status.INFO, "User Login Successfull");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
