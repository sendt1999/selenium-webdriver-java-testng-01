package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
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

		// Luôn khởi tạo sau biến driver này
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);

		/* CASE 1 */
		// Thẻ input bị che nên không thao tác được
		// Thẻ input lại dùng để verify được => vì hàm isSelected() nó chỉ work với thẻ
		// input

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		sleepInSecond(3);

		// Verify đã chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());

	}

	@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);

		/* CASE 2 */
		// Thẻ khác với input để click (span/ div/ label...) => đang hiển thị là được
		// Thẻ này lại không dùng để verify được => vì hàm isSelected() nó chỉ work với
		// thẻ input

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(5);

		// Verify đã chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());

		// Thẻ span/ div/ label luôn luôn trả về false

	}

	@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);

		/* CASE 3 */
		// Thẻ khác với input để click (span/ div/ label...) => đang hiển thị là được
		// Thẻ input lại dùng để verify được => vì hàm isSelected() nó chỉ work với thẻ
		// input

		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(3);

		// Verify đã chọn thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input"))
						.isSelected());

		// Ở trường hợp viết base, demo thì được
		// Nếu apply vào 1 framework/ dự án thực tế thì không nên
		// Vì 1 element phải define tời nhiều locator (dễ bị hiểu nhầm/ mất time để
		// maintain/ không tập trung)

	}

	@Test
	public void TC_04_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);

		/* CASE 4 */
		// Thẻ input bị ẩn nhưng vẫn dùng để click
		// Hàm click() của element nó sẽ không thao tác vào element bị ẩn được

		// Nên dùng một hàm click() của Javascript để click (click vào element bị ẩn
		// được)
		// Selenium nó cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản
		// test được -> JavascriptExecutor

		// Thẻ input lại dùng để verify được => vì hàm isSelected() nó chỉ work với thẻ
		// input

		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");

		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);

		// Verify đã chọn thành công
		Assert.assertTrue(driver.findElement(radioButton).isSelected());

	}

	@Test
	public void TC_05_() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);

		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");

		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(3);

		// Verify đã chọn thành công
		// Cách 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		
		// Cách 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
		
		// Hiếm gặp case này
		// các application của Google/ Facebook/ tech lớn thì thường không nên làm automation test cho nó

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
