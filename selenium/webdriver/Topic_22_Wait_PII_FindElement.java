package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_PII_FindElement {
	WebDriver driver;
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
		driver.get("https://www.facebook.com/reg");

		// Implicit Wait là 10 giây
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_FindElement() {
		// 3 trường hợp
		// Case 1: Nếu tìm nhưng chỉ có 1 element được tìm thấy
		driver.findElement(By.cssSelector("input[name='firstname']"));

		// Case 2: Tìm thấy nhiều hơn 1 element
		// Nó sẽ luôn lấy element đầu tiên để sử dụng
		// Vì nó vào và tìm thấy element ngay nên không cần phải chờ hết timeout là 10s
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Sendt");

		// Case 3: Không tìm thấy element nào hết
		// Nó sẽ không tìm thấy
		// Tiếp tục tìm đi tìm lại
		// Mỗi nửa giây tìm lại 1 lần
		// Nếu tìm lại mà thấy thì y như case 1-2 ở trên ( không cần chờ hết timeout còn lại)
		// Nếu tìm lại mà không thấy và hết timeout thì đánh fail testcase này
		// Throw ra 1 exception: NoSuchElement ( Không có element nào hết)
		driver.findElement(By.xpath("//driver[text()='What your name?"));

	}

	@Test
	public void TC_02_FindElements() {
		// 3 trường hợp
		// Case 1: Nếu tìm nhưng chỉ có 1 element được tìm thấy
		elements = driver.findElements(By.cssSelector("input[name='firstname']"));
		// Returns the munber of elements in this list
		System.out.println("Case 1: Tìm thấy 1 element = " + elements.size());

		// Case 2: Tìm thấy nhiều hơn 1 element
		// Nó sẽ lấy ra hết tất cả các element được tìm thấy
		elements = driver.findElements(By.cssSelector("input[type='text']"));
		System.out.println("Case 1: Tìm thấy nhiều hơn 1 element = " + elements.size());

		// Case 3: Không tìm thấy element nào hết
		// Nó sẽ không tìm thấy
		// Tiếp tục tìm đi tìm lại
		// Mỗi nửa giây tìm lại 1 lần
		// Nếu tìm lại mà thấy thì y như case 1-2 ở trên ( không cần chờ hết timeout còn lại)
		// Nếu tìm lại mà không thấy và hết timeout thì đánh fail testcase
		// Không show ra 1 exception nào hết
		// Trả về 1 list = 0 (empty)
		driver.findElement(By.xpath("//driver[text()='What your name?"));
		System.out.println("Case 1: Tìm thấy nhiều hơn 1 element = " + elements.size());
		Assert.assertEquals(elements.size(), 0);
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
