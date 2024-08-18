package uxlObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkOutObject extends homePage{
	
	WebDriver checkOutDriver;
	WebDriverWait wait;
	
	@FindBy(name = "proceedToRetailCheckout")
	WebElement btn_ProceedToPayment;

	public checkOutObject(WebDriver constructureDriver) {
		super(constructureDriver);
		this.checkOutDriver = constructureDriver;
		PageFactory.initElements(checkOutDriver, this);
		wait = new WebDriverWait(checkOutDriver, Duration.ofSeconds(10));
	}
	
	public void proceedToPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_ProceedToPayment));
		HighlightElement(btn_ProceedToPayment);
		btn_ProceedToPayment.click();
	}

}
