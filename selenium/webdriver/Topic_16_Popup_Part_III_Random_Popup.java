package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part_III_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	// String emailAddress = String.valueOf(new Random().nextInt(99999));
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";

	@BeforeClass

	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else { // mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		// driver = new FirefoxDriver();
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotification.enabled", false);
		driver = new FirefoxDriver(options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	// Yêu cầu:
	// Random popup nên nó có thể hiển thị 1 cách ngẫu nhiên hoặc không hiển thị
	// Nếu như nó hiển thị thì mình cần thao tác lên popup => Đóng nó đi để qua step
	// tiếp theo
	// Khi mà đóng nó lại thì có thể refresh trang nó hiện lên lại/ hoặc là không
	// Nếu như nó không hiển thị thì qua step tiếp theo luôn
	

	@Test
	public void TC_01_Random_Popup_In_DOM_Java_Code_Geeks() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);

		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

		// Vì nó luôn có trong DOM nên có thể dùng hàm isDisplayed() để kiểm tra được
		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhập email vào
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			sleepInSecond(2);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(7);

			// Verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),
					"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText()
					.contains("Your sign-up request was successful. We will contact you shortly. Please "));

			// Đóng popup đi => qua step tiếp theo
			// Sau 5 giây sẽ tựu đóng popup
			sleepInSecond(15);
		}

		String articleName = "Agile Testing Explained";

		// Qua step này
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
		driver.findElement(By.cssSelector("button#search-submit>span.tie-search-icon")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.post-details h2>a")).getText(), articleName);

	}

	@Test
	public void TC_02_Fixed_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(40);

		By Popup = By.cssSelector("div.tve_p_lb_inner");

		// Hàm isDisplayed() chỉ check cho element có trong DOM
		// Không có trong DOM thì không check được -> Fail ngay ddonanj element rồi
		// Vì nó luôn có trong DOM nên có thể dùng hàm isDisplayed() để kiểm tra được
		if (driver.findElement(Popup).isDisplayed()) {
			// Close popup này đi hoặc click vào link đẻ join các Group Zalo
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(2);

		}
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");

	}
    
	@Test
	public void TC_03_Fixed_Popup_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);

		By Popup = By.cssSelector("div.popup-content");

		// findElement => sẽ bị Fail khi không tìm thấy element => Nên ra một ngoại lệ: NoSuchElementException
		// findElements => không bị Fail khi không tìm thấy element => Trả về 1 list rỗng (Empty)
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// isDisplay()
		// Không có trong DOM thì khi nó vào tìm element: FinElements
		if (driver.findElements(Popup).size() > 0 && driver.findElements(Popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Sen");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("013456789");
			driver.findElement(By.cssSelector("button.close")).click();
			sleepInSecond(2);

		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(), courseName);

	}
	public int getRandomNumber() {
		return new Random().nextInt(99999);
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
