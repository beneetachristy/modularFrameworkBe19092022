package designPatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;

public class AmazonHomepage {
	private WebElement searchBox;
	
	private WebElement searchCategory;
	
	private WebElement searchButton;
	
	private CommonElement elementControl;
	
	private DropdownControl dropdownControl;
	
	private WebElement searchResult;
	
public AmazonHomepage(WebDriver driver) {
searchBox = driver.findElement(By.id("twotabsearchtextbox"));
searchCategory = driver.findElement(By.id("searchDropdownBox"));
searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
searchResult = driver.findElement(By.xpath("//span[contains(text(), 'results for')]"));
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
