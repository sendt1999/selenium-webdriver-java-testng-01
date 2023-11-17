package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Wait_PVII_Mix_Implicit_Explicit {
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

	}

	@Test
	public void TC_01_Element_Found() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://www.facebook.com/");

		// Implicit
		System.out.println("1 - Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='login']"));
		System.out.println("1 - End: " + getDateTimeNow());

		// Explicit
		System.out.println("2 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
		System.out.println("2 - End: " + getDateTimeNow());

	}

	@Test
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// Case 3: Không tìm thấy element nào hết
		// Nó sẽ không tìm thấy
		// Tiếp tục tìm đi tìm lại
		// Mỗi nửa giây tìm lại 1 lần
		// Nếu tìm lại mà thấy thì y như case 1-2 ở trên ( không cần chờ hết timeout còn
		// lại)
		// Nếu tìm lại mà không thấy và hết timeout thì đánh fail testcase này
		// Throw ra 1 exception: NoSuchElement ( Không có element nào hết)

		// Implicit
		System.out.println("1 - Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//button[@name='selenium']"));
		System.out.println("1 - End: " + getDateTimeNow());

	}

	@Test
	public void TC_02_1_Element_Not_Found_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 0);
		driver.get("https://www.facebook.com/");

		// Case 3: Không tìm thấy element nào hết
		// Nó sẽ không tìm thấy
		// Tiếp tục tìm đi tìm lại
		// Mỗi nửa giây tìm lại 1 lần
		// Nếu tìm lại mà thấy thì y như case 1-2 ở trên ( không cần chờ hết timeout còn
		// lại)
		// Nếu tìm lại mà không thấy và hết timeout thì đánh fail testcase này
		// Throw ra 1 exception: NoSuchElement ( Không có element nào hết)

		// Implicit
		
//		  System.out.println("1 - Start: " + getDateTimeNow());
//		  driver.findElement(By.xpath("//button[@name='selenium']"));
//		  System.out.println("1 - End: " + getDateTimeNow());
		 
		// Implicit + Explicit
		System.out.println("2 - Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_And_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");

		// Implicit + Explicit
		System.out.println("2 - Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		// Nếu như không set implicit thì mặc định = 0s
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");

		
		System.out.println("2 - Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}
	
	@Test
	public void TC_05_Element_Not_Found_Implicit_And_Explicit() {
		// Nếu như không set implicit thì mặc định = 0s
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");

		// Implicit + Explicit
		System.out.println("2 - Start: " + getDateTimeNow());
		try {
			// 1 - Nếu như element được tìm thấy thì sẽ chạy tiếp đoạn wait visible
			// 2 - Nếu như element không được tìm thấy thì sẽ không bào giờ chạy => Fail ngay đoạn findElement rồi 
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='selenium']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
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
