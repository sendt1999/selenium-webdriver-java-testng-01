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
		
//		driver.get("https://automationfc.github.io/dynamic-loading/");
//		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
//		driver.findElement(By.cssSelector("button#create-account-enabled")).click();

	}

	@Test
	public void TC_03_Wait_For_Element_Selected_I() {

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
	public void TC_03_Wait_For_Element_Selected_II() {

		explicitWait = new WebDriverWait(driver, 10);
		
		driver.get("https://automationfc.github.io/multiple-fields/");
        
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("input.form-checkbox"), 29));
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
	
	@Test
	public void TC_05_Wait_For_GetText() {
		driver.get("http://live.techpanda.org/");
		explicitWait = new WebDriverWait(driver, 10);

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='send2']")));
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='advice-required-entry-email']"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		
		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='advice-required-entry-pass']"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");

	}
	
	@Test
	public void TC_06_Url_Title() {
		driver.get("http://live.techpanda.org/");
		
		explicitWait = new WebDriverWait(driver, 10);
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		explicitWait.until(ExpectedConditions.urlContains("/customer/account/login/"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		explicitWait.until(ExpectedConditions.urlContains("/customer/account/create/"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");	
	
		
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		explicitWait.until(ExpectedConditions.titleIs("Customer Login"));
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		// Click Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		explicitWait.until(ExpectedConditions.titleIs("Create New Customer Account"));
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");	
	}
	
	@Test
	public void TC_07_Timeout_Less_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
			
	}

	 @Test
	public void TC_08_Timeout_Equal_5_Seconds() {
		 driver.get("https://automationfc.github.io/dynamic-loading/");
			explicitWait = new WebDriverWait(driver, 5);
			
			driver.findElement(By.cssSelector("div#start>button")).click();
			
			explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
			
			Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
				
		}
	
	 @Test
	public void TC_09_Timeout_More_Than_5_Seconds() {
		 driver.get("https://automationfc.github.io/dynamic-loading/");
			explicitWait = new WebDriverWait(driver, 100);
			
			driver.findElement(By.cssSelector("div#start>button")).click();
			
			explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
			
			Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
				
		}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
