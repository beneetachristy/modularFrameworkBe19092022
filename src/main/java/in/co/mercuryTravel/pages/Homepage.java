package in.co.mercuryTravel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.utils.WaitUtils;

public class Homepage extends BasePage {
	@FindBy(linkText= "International Holidays")
	private WebElement internationHolidayLink;
	
	@FindBy(linkText= "Indian Holidays")
	private WebElement indianHolidayLink;
	
	@FindBy(linkText= "Luxury Rails")
	private WebElement LUXURTHolidayLink;
	
	@FindBy(linkText= "Customer Login")
	private WebElement customerLogin;
	
	@FindBy(linkText= "User Login")
	private WebElement userLogin;
	
	@FindBy(linkText= "Register")
	private WebElement register;
	
	@FindBy(id= "sign_user_email")
	private WebElement signUserEmailId;
	
	@FindBy(id= "sign_user_password")
	private WebElement signUserPassword;
	
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement userButton;
	
	//@FindBy(xpath = "//button[@class='close'])[2]")
	//private WebElement closeInitialModalButton;
	
	@FindBy(partialLinkText  = "Welcome,")
	private WebElement welcomeText;
	
	
public Homepage(WebDriver driver) {
	super(driver);
	PageFactory.initElements(driver, this);
}

//public void closeInitialModal() throws Exception {
//elementControl.clickElement(closeInitialModalButton);	
//}
	public void userLogin(String username, String password) throws Exception{
		mouseOperation.moveToElement(customerLogin);
		mouseOperation.moveToElementAndClick(userLogin);
		WaitUtils.waitForSeconds(3);
		elementControl.setText(signUserEmailId, username);
		WaitUtils.waitForSeconds(3);
		elementControl.setText(signUserPassword, password);
		
		elementControl.clickElement(userButton);
	}
	
	public String getWelcomeMessage() throws Exception {
		return elementControl.getText(welcomeText);
	}
}
