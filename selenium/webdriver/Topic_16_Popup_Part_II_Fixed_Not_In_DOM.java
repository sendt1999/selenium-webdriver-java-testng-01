package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_II_Fixed_Not_In_DOM {
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

		// driver = new FirefoxDriver();
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotification.enabled", false);
		driver = new FirefoxDriver(options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	 @Test
	public void TC_01_Fixed_Popup_Not_In_DOM_TiKi() {
		driver.get("https://tiki.vn/");

		// By: Nó chưa có đi tìm Element
		By loginPopup = By.cssSelector("div.styles__Root-sc-2hr4xa-0");
		// By loginPopup = By.cssSelector("div.ReactModal__Content");

		// Verify nó chưa hiển thị khi chưa click vào Login button
		// Không có trong DOM dùng findElements số nhiều
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

		// Click cho bật Login popup lên
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(3);

		// Verify nó hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		// Verify error message displayed
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		
		// Close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);

		// Verify nó không còn hiển thị
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

	}

	 @Test
	public void TC_02_Fixed_Popup_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		// Verify Create Account popup không hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);

		// Click Create New Account button
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);

		// Verify Create Account popup hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);
		
		driver.findElement(By.name("lastname")).sendKeys("Automation");
		driver.findElement(By.name("firstname")).sendKeys("Test");
		driver.findElement(By.name("reg_email__")).sendKeys("sendt@gmail.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("Sen13456789@");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("8");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1999");
		
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		sleepInSecond(2);

		// CLick close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		sleepInSecond(2);

		// Verify Create Account popup không còn hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);

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
