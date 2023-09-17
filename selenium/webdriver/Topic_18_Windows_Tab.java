package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Windows_Tab {
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
		// driver.manage().window().maximize();
		// driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Github_With_Tow_Window_Tab() {
		// Driver đang taaij trang Github
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Trả về ID của driver đang đứng tại đó -> Số ít -> Chỉ 1 ID
		String githubID = driver.getWindowHandle();
		System.out.println("Github ID = " + githubID);
		System.out.println("Page title - Github = " + driver.getTitle());

		// Click vào Google link -> Theo Business nó sẽ mở ra trang Google
		// Driver vẫn đang ở trang Github
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		System.out.println("Page title - Github = " + driver.getTitle());

		switchToWindowByID(githubID);

		// Driver đang tại trang Google rồi
		System.out.println("Page title - Github = " + driver.getTitle());

		// Quay lại trang Github
		// Trả về driver của trang đang đứng tại đó
		String googleID = driver.getWindowHandle();
		System.out.println("Google ID = " + googleID);

		switchToWindowByID(googleID);
		System.out.println("Page title - Github = " + driver.getTitle());

	}

	@Test
	public void TC_02_Github_Greater_Tow_Window_Tab() {
		// Driver đang tại trang Github
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Trả về ID của driver đang đứng tại đó -> Số ít -> Chỉ 1 ID
		String githubID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		// Chuyển qua trang Google
		switchToWindowByTitle("Google");
		System.out.println("Page title - Google = " + driver.getTitle());

		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Automation FC");
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.ENTER);
		sleepInSecond(3);

		// Về lại trang Github
		switchToWindowByTitle("Selenium Webdriver");
		System.out.println("Page title - Github = " + driver.getTitle());

//		driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
//		sleepInSecond(5);

//		// Chuyển qua trang Facebook
//		switchToWindowByTitle("Facebook - log in or sign up");
//		System.out.println("Page title - Facebook = " + driver.getTitle());
//
//		driver.findElement(By.id("email")).sendKeys("test@gmail.com");
//		driver.findElement(By.id("pass")).sendKeys("123456789");
//		sleepInSecond(3);
//
//		// Về lại trang Github
//		switchToWindowByTitle("Selenium Webdriver");
//		System.out.println("Page title - Github = " + driver.getTitle());
//
//		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
//		sleepInSecond(3);

		// Chuyển qua trang Tiki
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("Page title - Tiki = " + driver.getTitle());
		
		closeAllWindowWithoutParentID(githubID);
	}

	@Test
	public void TC_03_TechPanda() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		System.out.println("Page title = " + driver.getTitle());
		
		// Sau khi click close thì về mặt business nó đã close window này rồi
		// Nhưng driver vẫn đang đứng ở trang Products Comparison List
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Mobile");
		System.out.println("Page title = " + driver.getTitle());
		

	}

	// Dùng cho case duy nhất 2 tab/ window
	public void switchToWindowByID(String pageID) {
		// Lấy ra tất cả các ID của các tab/ window đang có
		Set<String> allIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng item trong Set (allIDs)
		for (String id : allIDs) {

			// Trong quá trình duyệt từng item sẽ kiểm tra
			// Nết item nào khác với id truyền vào
			if (!id.equals(pageID)) {
				// Thì switch vào
				driver.switchTo().window(id);

				// Sleep 1 giây
				sleepInSecond(1);
				break;
			}
		}
	}

	// Dùng cho cả từ 2 tab/ window trở lên
	public void switchToWindowByTitle(String pageTitle) {
		// Lấy ra tất cả các ID của các tab/ window đang có
		Set<String> allIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng item
		for (String id : allIDs) {
			// Switch vào từng ID trước
			driver.switchTo().window(id);
			sleepInSecond(1);

			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				// Thoát khỏi vòng lặp không duyệt item tiếp theo nữa
				break;
			}
		}
	}

	public void closeAllWindowWithoutParentID(String parentID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}

		driver.switchTo().window(parentID);
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
