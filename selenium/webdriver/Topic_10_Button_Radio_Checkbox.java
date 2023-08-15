package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
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
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButton = By.cssSelector("button.fhs-btn-register");
		
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(5);
		
		// Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonBackgroundColor = Color.fromString(loginButtonBackground);
		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
		
		System.out.println(loginButtonBackground);
			
	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),' Cancer ')]/preceding-sibling::input")).click();
		
		// Click chọn 1 radio 
		driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
		
		// Verify các checkbox/ radio đã được chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),' Cancer ')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());
		
		// Checkbox có thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),' Cancer ')]/preceding-sibling::input")).click();
		
		// Verify các checkbox đã được bỏ chọn rồi
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),' Cancer ')]/preceding-sibling::input")).isSelected());
	
		// Radio không thể tự bỏ chọn được
		driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
		
		// Verify radio vẫn được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());
		
	}

	@Test
	public void TC_03_Default_Checkbox_Radio_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxs = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Nếu như gặp 1 checkbox có tên X mới click
		for (WebElement checkbox : allCheckboxs) {
			if ( checkbox.getAttribute("value").equals("Arthritis")) {
				checkbox.click();
			}
		}
		
	}
	
	@Test
	public void TC_04_Default_Checkbox_Radio_Multiple() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		// Chọn nó
		if (!driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input")).isSelected());
		
		// Bỏ chọn
		if (driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
	}
	
	/*
	 * public void randomNumber() { Random rand = new Random(); rand.nextInt(99999);
	 * }
	 */
	
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
