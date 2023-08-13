package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "04517-402-96-720";
	String commentinput = "This is gennerated data \nof real people";

	@BeforeClass

	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']")).click();
		sleepInSecond(5);

		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(10);

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("FC");
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.sendKeys(employeeID);
		sleepInSecond(3);

		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input"))
				.sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input"))
				.sendKeys("Sendt123@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input"))
				.sendKeys("Sendt123@");

		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		// driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(8);

		Assert.assertEquals(driver.findElement(By.name("firstName")), "Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")), "FC");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input\""))
						.getAttribute("value"),
				employeeID);

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
				.sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
				.sendKeys(commentinput);
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		// //div[@class='oxd-form-actions']/button[text()=' Save ']
		sleepInSecond(8);

		driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']/button[2]")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				passportNumber);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/textarea"))
						.getAttribute("value"),
				commentinput);

		driver.findElement(By.xpath("//li[@class='--active oxd-userdropdown']")).click();
		driver.findElement(By.xpath("//li[@class='--active oxd-userdropdown']//li[4]")).click();
		sleepInSecond(3);

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
