package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
//		1. Click vào 1 thẻ bất kỳ để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
		
//		2. Chờ cho tất cả các item được load ra thành công
		// Locator phải lấy để đại diện cho tất cả các item
		// Lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role=option]")));
		
		// Đưa hết tất cả item trong dropdown và 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role=option]"));
		
//		3. Tìm item xem đúng cái đang cần hay không
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);

//		4. Kiểm tra cái text của item đúng với cái mình mong muốn
			if (itemText.equals("Fast")) {
//		5. Click vào item đó	
				tempItem.click();
				// Thoát khỏi vòng lặp không xét cho các case còn lại nữa
				break;
			}
			
		}
	}
//		3.1 Nếu nó nằm trong khoảng nhìn thấy của user không cần phải scroll xuống
//		3.2 Nếu nó không nằm trong khoảng nhìn thấy của user thì cần scroll xuống đến item đó


	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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
