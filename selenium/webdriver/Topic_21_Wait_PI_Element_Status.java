package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_PI_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
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

		// Cho việc tìm element (findElement/ findElements) -> Chung chung
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Cho trạng thái của element -> Cụ thể
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Visible_Displayed() {
		driver.get("https://www.facebook.com/");

		// Chờ cho cái Email textbox được hiển thị trước khi senkeys vào nó
		// Chờ trong khoảng time là 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

		driver.findElement(By.cssSelector("input#email")).sendKeys("sen@gmail.com");
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Case_I() {
		// Điều kiện 2: Element không có (không nhìn thấy) trên UI những vẫn có trong cây HTML

		driver.get("https://www.facebook.com/");

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// Confirmation Email textbox is undisplayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("sen@gmail.com");

		// Confirmation Email textbox is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("sen@gmail.com");
	}

	@Test
	public void TC_02_Invisible_Undisplayed_Case_II() {
		// Điều kiện 3: Element không có/ không nhìn thấy trên UI và cũng không có trong cây HTML
		
		driver.get("https://www.facebook.com/");
		
		// Confirmation Email textbox is undisplayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

	}

	@Test
	public void TC_03_Persence_Case_I() {
		// Điều kiện 1: Element có trên UI và có trong cây HTML
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));

	}
	
	@Test
	public void TC_03_Persence_Case_II() {
		// Điều kiện 2: Element không có (không nhìn thấy) trên UI nhưng vẫn có trong cây HTML
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Confirmation Email textbox is presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

	}

	@Test
	public void TC_04_Stanleness() {
		// Apply cả: có trong HTML sau đó apply điều kiện 3
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		// B1: Element phải có trong HTML
		WebElement confirmationEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// Wait cho confirm email staleness -> chạy nhanh
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailTextbox));

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
