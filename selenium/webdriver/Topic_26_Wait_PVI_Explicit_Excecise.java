package webdriver;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class Topic_26_Wait_PVI_Explicit_Excecise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
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
		
		// Trạng thái: visible/ invisible/ presence/ click/ select///
		explicitWait = new WebDriverWait(driver, 30);
		
		// findElement/ findElements		
//		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		  driver.manage().window().maximize();
	
	}

	@Test
	public void TC_01_Telerick() {
		System.out.println("0.1 - Start: " + getDateTimeNow());
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Wait text visible
		System.out.println("0.2 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")));
		
		System.out.println("1 - Start: " + getDateTimeNow());
		WebElement todayText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		
		System.out.println("2 - Start: " + getDateTimeNow());
		Assert.assertEquals(todayText.getText(), "No Selected Dates to display.");
		
		// Wait cho ngày cần click là Clickable
		System.out.println("3 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='17']/parent::td")));
		
		// Click vào ngày hiện tại
		System.out.println("4- Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//a[text()='17']/parent::td")).click();
		
		// Wait loading icon biến mất
		System.out.println("5 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div:not([style=\"display:none;\"])>div.raDiv")));
		
		// Cái ngày đưuọc chọn là Selected
		System.out.println("6 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='17']/parent::td[@class='rcSelected']")));
		
		System.out.println("7 - Start: " + getDateTimeNow());
		todayText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(todayText.getText(), "Friday, November 17, 2023");
		System.out.println("7 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_02_Upload_File() {
		driver.get("https://gofile.io/welcome");
		
		// Wait cho loading icon ở trang main biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));
		
		// Wait cho button Upload Files hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-secondary")));
		driver.findElement(By.cssSelector("button.btn-secondary")).click();
		
		// Wait cho loading icon ở trang uploadFile biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent>div>div>div.spinner-border")));
				
		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(dalatPhotoPath + "\n" + hagiangPhotoPath + "\n" + haiphongPhotoPath);
		
		// Wait cho loading icon ở trang Upload biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo>div>div.spinner-border")));
		
		// Wait cho tất cả các progress bar biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		// Wait cho success message hiển thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[text()='Your files have been successfully uploaded']")));
		
		// Wait + click vào link
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a[@class='ajaxLink']"))).click();
		
		// Wait table chứa các file được upload visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesContent>div>div.contentCard")));
		
		// Verify Dowload button
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + dalatPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + hagiangPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + haiphongPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Download']")).isDisplayed());
		
		// Verify Play button
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + dalatPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + hagiangPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + haiphongPhoto + "']/parent::a/parent::div//following-sibling::div//span[text()='Play']")).isDisplayed());
        		
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
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
