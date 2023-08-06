package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");	
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
	}

	@Test
	public void TC_03_navigate() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

		// Back lại
		driver.navigate().back();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// Forward
		driver.navigate().forward();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		// Verify page HTML có chứa 1 chuỗi mong muốn
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
