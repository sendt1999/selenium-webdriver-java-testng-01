package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_I {
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
	public void TC_01_Fixed_Popup_In_DOM_NgoaiNgu() {
		driver.get("https://ngoaingu24h.vn/");

		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");

		// Verify popup is undisplay
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		// Click vào button Login
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);

		// Verify popup is isdisplay
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("div.input>input#account-input")).sendKeys("Automation");
		driver.findElement(By.cssSelector("div.input>input#password-input")).sendKeys("Automation");
		driver.findElement(By.cssSelector("button.buttonLoading")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");

//		driver.findElement(By.cssSelector("div.modal-header>button.close")).click();
//		sleepInSecond(3);
//		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
//		
	}

	@Test
	public void TC_02_Fixed_Popup_In_DOM_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");

		By loginPopup = By.cssSelector("div#k-popup-account-login");

		// Undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);

		// Displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("sendt@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("0123456789");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(2);

		// Undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

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
