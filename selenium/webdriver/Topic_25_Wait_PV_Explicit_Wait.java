package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_PV_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	List<WebElement> elements;

	@BeforeClass

	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_Wait_For_Attribute_Contain_Tobe_Value() {
		driver.get("http://live.techpanda.org/index.php");

		explicitWait = new WebDriverWait(driver, 30);

		// Wait cho Search textbox chứa giá trị là 1 đoạn placeholder text

		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder",
				"Search entire store"));
		explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder",
				"Search entire store here..."));

		explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder",
				"Search entire store here..."));

		// Verify luôn chứ không có wait
		Assert.assertEquals(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder"),
				"Search entire store here...");

	}

	@Test
	public void TC_02_Wait_For_Element_Clickable() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 10);

		// Wait cho Start button được realy trước khi click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
		driver.findElement(By.cssSelector("div#start>button")).click();

		driver.get("https://login.mailchimp.com/signup/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
		driver.findElement(By.cssSelector("button#create-account-enabled")).click();

	}

	@Test
	public void TC_03_Wait_For_Element_Selected() {

		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Dùng vòng lặp để duyệt qua và check vào tất cả các checkbox này
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}
		
		// Verify tất cả các checkbox được chọn thành công
		for (WebElement checkbox : allCheckboxes ) {
			explicitWait.until(ExpectedConditions.elementToBeSelected(checkbox));
			Assert.assertTrue(checkbox.isDisplayed());
		}
		}
 		
	
	@Test
	public void TC_04_Wait_For_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		explicitWait = new WebDriverWait(driver, 10);
		
		// Switch frame
		driver.switchTo().frame("login_page");
		
		// Thao tác với UserID
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Sentest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		// Switch về defaul
		driver.switchTo().defaultContent();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
