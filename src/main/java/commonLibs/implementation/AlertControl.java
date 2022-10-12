package commonLibs.implementation;


import org.openqa.selenium.Alert;

import org.openqa.selenium.WebDriver;

import commonLibs.contracts.IAlert;
import commonLibs.utils.WaitUtils;

public class AlertControl implements IAlert {
	private WebDriver driver;

	public AlertControl(WebDriver driver) {
		this.driver = driver;
	}

	private Alert getAlert() {
		return driver.switchTo().alert();
	}

	@Override
	public void acceptAlert() throws Exception {
		getAlert().accept();
	}

	@Override
	public void rejectAlert() throws Exception {
		getAlert().dismiss();

	}

	@Override
	public String getMessageFromAlert() throws Exception {

		return getAlert().getText();
	}

	
	
		
		

	@Override
	public boolean checkAlertPresent(int timeoutInseconds) throws Exception {
		try {
			WaitUtils.waitTillAlertIsPresent(driver, timeoutInseconds);
			return true;
		} catch (Exception e) {
			return false;

		}

	}
}

	