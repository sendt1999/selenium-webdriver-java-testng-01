package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Wait_PIII_Implicit_Wait {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstname, lastName, password, fullName;

	@BeforeClass
	
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac 
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		rand = new Random();
		
		driver = new FirefoxDriver();
		
		emailAddress = "Automation" + rand.nextInt(9999) + "@gmail.com";
		firstname = "Automation";
		lastName = "FC";
		fullName = firstname + " " + lastName;
		password = "12345678";
	}

	 @Test
	public void TC_01_Timeout_Less_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
			
	}

	 @Test
	public void TC_02_Timeout_Equal_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
			
	}
	
	 @Test
	public void TC_03_Timeout_More_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
			
	}
	
	 @Test
	public void Login_05_Register_a_new_account() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "Thank you for registering with Main Website Store.");

		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
