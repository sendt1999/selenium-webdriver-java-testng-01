package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Frame_Iframe {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");

		Assert.assertTrue(driver.findElement(By.cssSelector("div.face-content iframe")).isDisplayed());

		// Switch vào Facebook iframe
//		driver.switchTo().frame(0);  // Theo index
//		driver.switchTo().frame("cs_chat_iframe");  // Theo id/ name
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content iframe"))); // Theo element

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='166K followers']")).getText(), "166K followers");

		// Quay về trang trước đó (Parent)
		driver.switchTo().defaultContent();

		// Switch vào Chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		sleepInSecond(3);

		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")));
		sleepInSecond(2);

		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Sen Test");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("013456789");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Automation Testing");

		driver.switchTo().defaultContent();

		String keyword = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(10);

		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}

	}

	// @Test
	public void TC_02_() {
		// Trang 1 chứa iframe 2
		driver.switchTo().frame("2");

		// iframe 2 chứa iframe 3
		driver.switchTo().frame("3");

		// Quay từ 3 về 2 ( không hỗ trợ từ 3 về 1)
		driver.switchTo().parentFrame();
		
		// 2 về 1
		driver.switchTo().defaultContent();
	}

	@Test
	public void TC_03_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// Switch frame
		driver.switchTo().frame("");
		
		// Thao tác với UserID
		driver.findElement(By.name("https://netbanking.hdfcbank.com/netbanking/")).sendKeys("123456");
		driver.findElement(By.cssSelector("div.lablefield.blank")).click();
		sleepInSecond(5);
		
		// Switch về defaul
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());

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
