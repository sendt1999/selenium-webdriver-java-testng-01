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

public class Topic_06_Web_Element_PIII {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
		emailAddress = "Automation" + rand.nextInt(9999) + "@gmail.com";
		firstname = "Automation";
		lastName = "FC";
		fullName = firstname + " " + lastName;
		password = "12345678";
	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");

	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("email")).sendKeys("123243@12414");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Password_Less_Than_6_chars() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("email")).sendKeys("sendt@gmil.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void Login_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("email")).sendKeys("sendt@gmil.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");

	}
	
	@Test
	public void Login_05_Register_a_new_account() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
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
	
	@Test
	public void Login_06_Login_Valid_Info() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id='send2']")).click();
		sleepInSecond(3);
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformationText);
		Assert.assertTrue(contactInformationText.contains(fullName));
		Assert.assertTrue(contactInformationText.contains(emailAddress));
		
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
