package webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Wait_PIX_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	Actions action;
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
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
	}

	// @Test
	public void TC_01_Orang_HRM_API() {
		driver.get("https://api.orangehrm.com/");
		
		// Wait cho icon loading/ ajax loading/ progress bar loading/ inprogress loading biến mất
		// Vì khi nó biến mất thì cái trang nó sẽ load hết dữ liệu về thành công
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	// @Test
	public void TC_02_Admin_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
	    
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
	}

	@Test
	public void TC_02_1_Admin_NopCommerce() {
		
		// Cách khác
		driver.get("https://admin-demo.nopcommerce.com");
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		
		// Click chuyển trang: Từ Login vào Dashboard
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
		
		// Click chuyển trang: Từ Dashboard về Login
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		
		Assert.assertTrue(waitForAjaxBusyLoadingInvisible());
	    
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
	}
	
	@Test
	public void TC_03_Blog_Test_project() {
		driver.get("https://blog.testproject.io/");
		
		// Hover chuột vàob1 element bất kỳ tại page này để cho page ready
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		// Wait cho page heading visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> posArticles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : posArticles) {
			Assert.assertTrue(article.getText().contains(keyword));
		}
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean waitForAjaxBusyLoadingInvisible() {
		return  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
		
	}
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
