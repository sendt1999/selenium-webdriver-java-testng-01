package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part_III {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Scroll đến element đó
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);

		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.container p#demo")).getText(),
				"Hello Automation Guys!");

	}

	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);

		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible"))
						.isDisplayed());

		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);

		driver.switchTo().alert().accept();
		sleepInSecond(3);

		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

	}
    
	@Test
	public void TC_03_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);

		// Verify Text
		Assert.assertEquals(bigCircle.getText(), "You did great!");

		// Verify Background Color
		String bigCircleRGB = bigCircle.getCssValue("background-color");
		System.out.println(bigCircleRGB);
		
		String bigCircleHexa = Color.fromString(bigCircleRGB).asHex();
		System.out.println(bigCircleHexa);
		
		bigCircleHexa = bigCircleHexa.toUpperCase();
		System.out.println(bigCircleHexa);
		
		Assert.assertEquals(bigCircleHexa, "#03A9F4");
		
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
