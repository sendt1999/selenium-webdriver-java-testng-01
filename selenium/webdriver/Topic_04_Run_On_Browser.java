package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@Test
	public void TC_01_Run_Chrome() {
		// Chrome
	    if (osName.contains("Windows")) {
	    	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else { // mac 
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		
	    driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
	    driver.quit();
	    
	}

	@Test
	public void TC_02_Run_Firefox() {
		// Firefox
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
	    driver.quit();
	    
	}

	@Test
	public void TC_03_Run_Edge() {
		// Edge
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		} else { // mac 
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		}
		
		driver = new EdgeDriver(); 
        
		driver.get("https://demo.nopcommerce.com/");
	    driver.quit();
		
	}

}
