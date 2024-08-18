package uxlTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterTest;

public class testBase {

	public static WebDriver uxlDriver;

	@SuppressWarnings("unused")
	@BeforeTest(alwaysRun = true)
	public void setUp(String Explorer, @Optional("https://www.amazon.eg/") String URL) throws Throwable {
		if (Explorer != null || Explorer != "") {
			WebDriverManager.chromedriver().setup();
			uxlDriver = new ChromeDriver();
			uxlDriver.manage().window().maximize();
		} else {
			WebDriverManager.firefoxdriver().setup();
			uxlDriver = new FirefoxDriver();
			uxlDriver.manage().window().maximize();
		}
		Thread.sleep(2000);
		uxlDriver.manage().deleteAllCookies();
		
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		JOptionPane.showMessageDialog(null, "[Info] - Execution completed, thank you.");
		//uxlDriver.quit();
	}
	
	

}
