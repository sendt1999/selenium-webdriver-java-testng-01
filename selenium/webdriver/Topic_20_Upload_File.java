package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload_File {
	WebDriver driver;
	// Lấy ra đường dẫ tương đối
	String projectPath = System.getProperty("user.dir");
	// Lấy ra tên của hệ điều hành
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	String dalatPhoto = "Da Lat.jpg";
	String hagiangPhoto = "Ha Giang.jpg";
	String haiphongPhoto = "Hai Phong.jpg";

	String dalatPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + dalatPhoto;
	String hagiangPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + hagiangPhoto;
	String haiphongPhotoPath = projectPath + File.separator + "uploadFiles" + File.separator + haiphongPhoto;

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
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	 @Test
	public void TC_01_One_File_Fer_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFile = By.xpath("//input[@type='file']");

		driver.findElement(uploadFile).sendKeys(dalatPhotoPath);
		sleepInSecond(3);
		driver.findElement(uploadFile).sendKeys(hagiangPhotoPath);
		sleepInSecond(3);
		driver.findElement(uploadFile).sendKeys(haiphongPhotoPath);
		sleepInSecond(3);

		// Verify các file được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatPhoto + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hagiangPhoto + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + haiphongPhoto + "']")).isDisplayed());

		// Click upload cho từng file
		List<WebElement> startButtons = driver.findElements(By.cssSelector("tbody.files button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}

		// Verify các file được upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dalatPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hagiangPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + haiphongPhoto + "']")).isDisplayed());

		// Verify các hình này uplaod lên là hình thực
		Assert.assertTrue(isImageLoaded("//a[@title='" + dalatPhoto + "']/img"));
		Assert.assertTrue(isImageLoaded("//a[@title='" + hagiangPhoto + "']/img"));
		Assert.assertTrue(isImageLoaded("//a[@title='" + haiphongPhoto + "']/img"));
	}

	@Test
	public void TC_02_Multiple_File_Fer_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFile = By.xpath("//input[@type='file']");
		
		// Load cùng lúc lên 3 file
		driver.findElement(uploadFile).sendKeys(dalatPhotoPath + "\n" + hagiangPhotoPath + "\n" + haiphongPhotoPath);
		sleepInSecond(3);

		// Verify các file được load lên thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatPhoto + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hagiangPhoto + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + haiphongPhoto + "']")).isDisplayed());

		// Click upload cho từng file
		List<WebElement> startButtons = driver.findElements(By.cssSelector("tbody.files button.start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(3);
		}

		// Verify các file được upload lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dalatPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hagiangPhoto + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + haiphongPhoto + "']")).isDisplayed());

		// Verify các hình này uplaod lên là hình thực
		Assert.assertTrue(isImageLoaded("//a[@title='" + dalatPhoto + "']/img"));
		Assert.assertTrue(isImageLoaded("//a[@title='" + hagiangPhoto + "']/img"));
		Assert.assertTrue(isImageLoaded("//a[@title='" + haiphongPhoto + "']/img"));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	private WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));

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
