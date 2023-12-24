package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_PVIII_Fluent<T> {
	WebDriver driver;
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;
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
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_GetText() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
		
		// Tổng thời gian chờ là bao lâu
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15));
		
		// Thời gian tìm lại là bao lâu (polling)
		fluentWaitDriver.pollingEvery(Duration.ofMillis(100));
		
		// Nếu trong quá trình đi tìm element mà không thấy thì sẽ throw ngoại lệ
		// Ignore exception này trong code
		fluentWaitDriver.ignoring(NoSuchElementException.class);
		
		// Điều kiện của fluent wait
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
			}
		});
	}
		
		/* Cách viết tương tự khác
		// Điều kiện của fluent wait
		String helloWorldText = fluentWaitDriver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText();
	}
		});
		Assert.assertEquals(helloWorldText, "Hello World!");
        */
	
	@Test
	public void TC_01_GetText_I() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
	    
		// Setting time + exception
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		// Condition
		String helloWorldText = fluentWaitDriver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText();
	}
		});
		Assert.assertEquals(helloWorldText, "Hello World!");
		
	}
		

	@Test
	public void TC_02_Equal() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
	    
		// Setting time + exception
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		// Condition
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
			}
		});
	}
	
	@Test
	public void TC_03_isDisplayed() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		fluentWaitDriver = new FluentWait<WebDriver>(driver);
	    
		// Setting time + exception
		fluentWaitDriver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		// Condition
		fluentWaitDriver.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']//h4[text()='Hello World!']")).isDisplayed();
			}
		});
	}
	
	@Test
	public void TC_04_Element() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentWaitElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("div#javascript_countdown_time")));
		
		// Setting time + exception
		fluentWaitElement.withTimeout(Duration.ofSeconds(5))
		.pollingEvery(Duration.ofMillis(1000))
		.ignoring(NoSuchElementException.class);
				
				
		boolean status = fluentWaitElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement element) {
				   String text = element.getText();
				   System.out.println(text);
						return text.endsWith("00");
					}
				});
		Assert.assertTrue(status);
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
