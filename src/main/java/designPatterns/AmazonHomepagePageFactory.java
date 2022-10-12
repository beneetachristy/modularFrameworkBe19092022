package designPatterns;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;

public class AmazonHomepagePageFactory {
	@CacheLookup 
	//Static element that don't change their location
	@FindBy(id="twotabsearchtextbox")
	//PageFactory annotation
	private WebElement searchBox;
	
	@CacheLookup
	@FindBy(id="searchDropdownBox")
	private WebElement searchCategory;
	
	@CacheLookup
	@FindBy(xpath ="//input[@value='Go']")
	private WebElement searchButton;
	
	@FindBy(xpath ="//span[contains(text(), 'results for')]")
	private WebElement searchResult;
	
	private CommonElement elementControl;
	
	private DropdownControl dropdownControl;
	
	
	
public AmazonHomepagePageFactory(WebDriver driver) {
PageFactory.initElements(driver, this);
elementControl = new CommonElement();
dropdownControl = new DropdownControl();
}

public void searchProduct(String product, String category) throws Exception{
	elementControl.setText(searchBox, product);	
	dropdownControl.selectViaVisibleText(searchCategory, category);
	elementControl.clickElement(searchButton);
	System.out.println(elementControl.getText(searchResult));
}
}
